package com.chinatelecom.di.transport.netty;

import com.chinatelecom.di.common.component.AbstractLifecycleComponent;
import com.chinatelecom.di.common.network.NetworkService;
import com.chinatelecom.di.common.settings.Settings;
import com.chinatelecom.di.transport.BoundTransportAddress;
import com.chinatelecom.di.transport.InetSocketTransportAddress;
import com.chinatelecom.di.transport.Transport;
import com.chinatelecom.di.transport.TransportAddress;
import com.google.inject.Inject;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.channel.socket.nio.NioWorkerPool;
import org.jboss.netty.util.HashedWheelTimer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicReference;

import static com.chinatelecom.di.common.util.concurent.EsExecutors.daemonThreadFactory;
import static com.chinatelecom.di.common.settings.Settings.settingsBuilder;
import static com.chinatelecom.di.common.util.concurent.ConcurrentCollections.newConcurrentMap;
/**
 * Created by song on 2017/11/30.
 */
public class NettyTransport extends AbstractLifecycleComponent<Transport> implements Transport {

    public static final String TRANSPORT_CLIENT_WORKER_THREAD_NAME_PREFIX="transport_client_worker";
    public static final String TRANSPORT_CLIENT_BOSS_THREAD_NAME_PREFIX="transport_client_boss";
    private static final String WORKER_COUNT = "transport.netty.worker_count";
    private static final String HTTP_SERVER_BOSS_THREAD_NAME_PREFIX ="http_server_boss" ;
    private static final String HTTP_SERVER_WORKER_THREAD_NAME_PREFIX = "http_server_worker";


    protected final NetworkService networkService;


    protected volatile ClientBootstrap clientBootstrap;

    protected final int workerCount;


    protected final Map<String,List<Channel>> serverChannels = newConcurrentMap();
    protected final Map<String,ServerBootstrap> serverBootstraps=newConcurrentMap();

    protected volatile BoundTransportAddress boundAddress;

    @Inject
    protected NettyTransport(Settings settings,NetworkService networkService) {
        super(settings);

        this.networkService=networkService;
        this.workerCount=settings.getAsInt(WORKER_COUNT,2);

    }

    @Override
    protected void doStart()  {

        clientBootstrap=createClientBootstrap();

        Settings defaultSettings=null;

        Settings mergedSetting=settingsBuilder().put(defaultSettings).build();

        String name="default";
        createServerBootstrap(name,mergedSetting);
        try {
            bindServerBootstrap(name,mergedSetting);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createServerBootstrap(String name,Settings settings){

        final ThreadFactory bossFactory=daemonThreadFactory(this.settings,HTTP_SERVER_BOSS_THREAD_NAME_PREFIX,name);
        final ThreadFactory workerFactory=daemonThreadFactory(this.settings,HTTP_SERVER_WORKER_THREAD_NAME_PREFIX,name);


        ServerBootstrap serverBootstrap;
        serverBootstrap=new ServerBootstrap(new NioServerSocketChannelFactory(
                Executors.newCachedThreadPool(bossFactory),
                Executors.newCachedThreadPool(workerFactory),
                workerCount
        ));
        serverBootstraps.put(name,serverBootstrap);

    }


    private void bindServerBootstrap(final String name,final Settings settings) throws IOException {

        InetAddress hostAddresses[] =null ;
        String bindHosts[]=settings.getAsArray("bind_host",null);
        try {
            hostAddresses=networkService.resolveBindHostAddress(bindHosts);

        } catch (IOException e) {
            e.printStackTrace();
        }
        List<InetSocketAddress> boundAddresses=new ArrayList<>();
        for(InetAddress inetAddress:hostAddresses){
           boundAddresses.add(bindToPort(name,inetAddress,"9300-9400")) ;
        }

        final BoundTransportAddress boundTransportAddress=createBoundTransportAddress(name,settings,boundAddresses);
        this.boundAddress=boundTransportAddress;
    }



    private BoundTransportAddress createBoundTransportAddress(String name, Settings profileSettings, List<InetSocketAddress> boundAddresses) throws IOException {

        String[] boundAddressHostStrings = new String[boundAddresses.size()];
        TransportAddress[] transportAddresses = new TransportAddress[boundAddresses.size()];
        for(int i=0 ;i <boundAddresses.size();i++)
        {
            InetSocketAddress boundAddress = boundAddresses.get(i);
            boundAddressHostStrings[i]=boundAddress.getHostString();
            transportAddresses[i]=new InetSocketTransportAddress(boundAddress);
        }


        final String[] publishHosts;
        publishHosts=boundAddressHostStrings;

        final InetAddress publishInetAddress;

        publishInetAddress=networkService.resolvePublishHostAddresses(publishHosts);
        final int publishPort=resolvePublishPort(name,settings,profileSettings,boundAddresses,publishInetAddress);

        final TransportAddress publishAddress = new InetSocketTransportAddress(new InetSocketAddress(publishInetAddress,publishPort));
        return new BoundTransportAddress(transportAddresses,publishAddress);

    }

    static int resolvePublishPort(String profileName,Settings settings, Settings profileSettings, List<InetSocketAddress> boundAddresses,InetAddress publishInetAddress){
        int publishPort = 0;


        for(InetSocketAddress boundAddress : boundAddresses){
            InetAddress boundInetAddress = boundAddress.getAddress();
            if(boundInetAddress.equals(publishInetAddress)){
                publishPort=boundAddress.getPort();
                break;
            }
        }

        return publishPort;
    }

    private InetSocketAddress bindToPort(final String name, final  InetAddress hostAddresses, final String port) {

        PortsRange portsRange=new PortsRange(port);
        final AtomicReference<InetSocketAddress> boundSocket=new AtomicReference<>();
        boolean success =portsRange.iterate(new PortsRange.PortCallback(){
              @Override
              public boolean onPortNumber(int portNumber) {

                  Channel channel = serverBootstraps.get(name).bind(new InetSocketAddress(hostAddresses,portNumber));
                  synchronized (serverChannels){
                      List<Channel> list = serverChannels.get(name);
                      if(list == null){
                          list = new ArrayList<>();
                          serverChannels.put(name,list);
                      }
                      list.add(channel);
                      boundSocket.set((InetSocketAddress)channel.getLocalAddress());
                  }
                  return true;
              }
          }
        );

        if(!success){
            throw new IllegalArgumentException("Failed to bind to ["+ port+ "]");
        }

        return boundSocket.get();

    }





    private ClientBootstrap createClientBootstrap() {

        int bossCount=settings.getAsInt("transport.netty.boss_count",1);

        clientBootstrap=new ClientBootstrap(new NioClientSocketChannelFactory(
                Executors.newCachedThreadPool(daemonThreadFactory(settings,TRANSPORT_CLIENT_BOSS_THREAD_NAME_PREFIX)),
                bossCount,
                new NioWorkerPool(Executors.newCachedThreadPool(daemonThreadFactory(settings,TRANSPORT_CLIENT_WORKER_THREAD_NAME_PREFIX)),workerCount),
                new HashedWheelTimer(daemonThreadFactory(settings,"transport_client_timer"))));

        clientBootstrap.setPipelineFactory(configureClientChannelPipelineFacotory());


        return clientBootstrap;

    }

    private ChannelPipelineFactory configureClientChannelPipelineFacotory() {

        return new ClientChannelPipelineFactory(this);

    }

    protected  static class ClientChannelPipelineFactory implements ChannelPipelineFactory{

        protected  final NettyTransport nettyTransport;


        public ClientChannelPipelineFactory(NettyTransport nettyTransport){

            this.nettyTransport=nettyTransport;

        }

        @Override
        public ChannelPipeline getPipeline() throws Exception{
            ChannelPipeline channelPipeline= Channels.pipeline();
            SizeHeaderFrameDecoder sizeHeader=new SizeHeaderFrameDecoder();
            channelPipeline.addLast("size",sizeHeader);
            channelPipeline.addLast("dispatcher",new MessageChannelHander(nettyTransport,".client"));
            return null;

        }
    }


}
