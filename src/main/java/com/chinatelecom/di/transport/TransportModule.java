package com.chinatelecom.di.transport;

import com.chinatelecom.di.cluster.node.DiscoveryNode;
import com.chinatelecom.di.common.settings.Settings;
import com.chinatelecom.di.discovery.Discovery;
import com.chinatelecom.di.transport.local.LocalTransport;
import com.chinatelecom.di.transport.netty.NettyTransport;
import com.google.common.collect.Maps;
import com.google.inject.AbstractModule;

import java.util.Map;

/**
 * Created by song on 2017/11/23.
 */
public class TransportModule extends AbstractModule {
    private final static String TRANSPORT_TYPE_KEY="transport.type";
    private final static String TRANSPORT_SERVICE_TYPE_KEY="transport.service.type";


    private final static String LOCAL_TRANSPORT="local";
    private final static String NETTY_TRANSPORT="netty";


    private final  Settings settings;

    private final Map<String,Class<? extends Transport>> transports= Maps.newHashMap();

    public TransportModule(Settings settings)
    {
       this.settings=settings;

       addTransport(LOCAL_TRANSPORT,LocalTransport.class);
       addTransport(NETTY_TRANSPORT,NettyTransport.class);
    }

    private void addTransport(String name, Class<? extends Transport> clazz) {
        transports.put(name,clazz);
    }

    @Override
    protected void configure() {

        bind(TransportService.class).asEagerSingleton();

        String defaultType= DiscoveryNode.localNode(settings)? LOCAL_TRANSPORT:NETTY_TRANSPORT;
        String typeName= settings.get(TRANSPORT_TYPE_KEY,defaultType);
        Class<? extends Transport> clazz=transports.get(typeName);
        if(clazz==null){
            throw new IllegalArgumentException("Unknow Transport ["+typeName+"]");
        }
        bind(Transport.class).to(clazz);
    }
}
