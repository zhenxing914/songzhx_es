package com.chinatelecom.di.common.network;

import com.chinatelecom.di.common.component.AbstractComponent;
import com.chinatelecom.di.common.settings.Settings;
import com.google.inject.Inject;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by song on 2018/2/11.
 */
public class NetworkService extends AbstractComponent {

    @Inject
    public NetworkService(Settings settings) {
        super(settings);

    }

    public InetAddress[] resolveBindHostAddress(String[] bindHosts) throws IOException {
        if(bindHosts==null){

            bindHosts= new String[]{"192.168.1.108"};
        }
        InetAddress address[]=resolveInetAddress(bindHosts);
        return address;
    }

    private InetAddress[] resolveInetAddress(String[] hosts) throws IOException {
        HashSet<InetAddress> set=new HashSet<>();
        for(String host: hosts)
        {
            set.addAll(Arrays.asList(resolveInet(host)));
        }

        return set.toArray(new InetAddress[set.size()]);

    }

    private InetAddress[] resolveInet(String host) throws IOException {
        return InetAddress.getAllByName(host);
    }


    public InetAddress resolvePublishHostAddresses(String[] publishHosts) throws IOException {
        if(publishHosts==null){

            publishHosts= new String[]{"192.168.1.108"};
        }
        InetAddress address[]=resolveInetAddress(publishHosts);
        return address[0];

    }


}
