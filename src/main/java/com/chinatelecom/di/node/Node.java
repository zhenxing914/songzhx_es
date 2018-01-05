package com.chinatelecom.di.node;

import com.chinatelecom.di.ThreadPool.ThreadPool;
import com.chinatelecom.di.ThreadPool.ThreadPoolModule;
import com.chinatelecom.di.Version;
import com.chinatelecom.di.cluster.ClusterModule;
import com.chinatelecom.di.cluster.ClusterNameModule;
import com.chinatelecom.di.cluster.routing.RoutingService;
import com.chinatelecom.di.cluster.service.ClusterService;
import com.chinatelecom.di.common.ModuleBuilder;
import com.chinatelecom.di.common.settings.Settings;
import com.chinatelecom.di.common.settings.SettingsModule;
import com.chinatelecom.di.discovery.Discovery;
import com.chinatelecom.di.discovery.DiscoveryModule;
import com.chinatelecom.di.discovery.DiscoveryService;
import com.chinatelecom.di.transport.TransportModule;
import com.chinatelecom.di.transport.TransportService;
import com.google.inject.Injector;


/**
 * Created by song on 2017/10/16.
 */

public class Node {



    private final Injector injector;



    Settings settings=new Settings();

    final ThreadPool threadPool=new ThreadPool(this.settings);

    public Node(Version version)
    {
        ModuleBuilder module=new ModuleBuilder();
        module.add(new Version.Module(version));
        module.add(new SettingsModule(this.settings));
        module.add(new NodeModule());
        module.add(new ClusterModule());
        module.add(new ThreadPoolModule(threadPool));
        module.add(new DiscoveryModule(this.settings));
        module.add(new ClusterNameModule(this.settings));
        module.add(new TransportModule(this.settings));
        injector=module.createInjector();
    }

    public void start(){

        System.out.println("node start.");

        injector.getInstance(Discovery.class).setRoutingService(injector.getInstance(RoutingService.class));


        TransportService transportService=injector.getInstance(TransportService.class).start();
        transportService.start();
        injector.getInstance(ClusterService.class).start();
    }
}
