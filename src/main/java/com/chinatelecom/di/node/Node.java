package com.chinatelecom.di.node;

import com.chinatelecom.di.Version;
import com.chinatelecom.di.cluster.ClusterModule;
import com.chinatelecom.di.cluster.service.ClusterService;
import com.chinatelecom.di.common.ModuleBuilder;
import com.chinatelecom.di.discovery.DiscoveryModule;
import com.google.inject.Injector;

/**
 * Created by song on 2017/10/16.
 */

public class Node {


    private final Injector injector;



    public Node(Version version)
    {
        ModuleBuilder module=new ModuleBuilder();
        module.add(new DiscoveryModule());
        module.add(new ClusterModule());
        injector=module.createInjector();
    }

    public void start(){

        System.out.println("node start.");

        injector.getInstance(ClusterService.class).start();
    }
}
