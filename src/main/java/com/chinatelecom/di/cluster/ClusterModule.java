package com.chinatelecom.di.cluster;

import com.chinatelecom.di.cluster.routing.OperateRouting;
import com.chinatelecom.di.cluster.service.ClusterService;
import com.chinatelecom.di.cluster.service.InternalClusterService;
import com.chinatelecom.di.discovery.DiscoveryNodeService;

import com.google.inject.AbstractModule;
/**
 * Created by song on 2017/10/16.
 */
public class ClusterModule extends AbstractModule {

    protected void configure() {

        bind(DiscoveryNodeService.class).asEagerSingleton();
        bind(ClusterService.class).to(InternalClusterService.class).asEagerSingleton();
        bind(OperateRouting.class).asEagerSingleton();

    }
}
