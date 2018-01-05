package com.chinatelecom.di.cluster;

import com.chinatelecom.di.cluster.routing.OperateRouting;
import com.chinatelecom.di.cluster.routing.RoutingService;
import com.chinatelecom.di.cluster.routing.allocation.AllocationService;
import com.chinatelecom.di.cluster.routing.allocation.allocator.FilterAllocationDecider;
import com.chinatelecom.di.cluster.routing.allocation.decider.AllocationDecider;
import com.chinatelecom.di.cluster.routing.allocation.decider.AllocationDeciders;
import com.chinatelecom.di.cluster.routing.allocation.decider.SameShardAllocationDecider;
import com.chinatelecom.di.cluster.service.ClusterService;
import com.chinatelecom.di.cluster.service.InternalClusterService;
import com.chinatelecom.di.common.settings.Settings;
import com.chinatelecom.di.discovery.DiscoveryNodeService;

import com.chinatelecom.di.gateway.GatewayAllocator;
import com.google.inject.AbstractModule;

import java.security.cert.Extension;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by song on 2017/10/16.
 */
public class ClusterModule extends AbstractModule {

    private  final Settings settings;
    private final ExtensionPoint.ClassSet<AllocationDecider> allocationDeciders=
            new ExtensionPoint.ClassSet<>("allcation_decider",AllocationDecider.class, AllocationDeciders.class);

    public static final List<Class<? extends AllocationDecider>> DEFAULT_ALLOCATION_DECIDERS=
            Collections.unmodifiableList(Arrays.asList(
                    SameShardAllocationDecider.class,
                    FilterAllocationDecider.class
            ));

    public ClusterModule(Settings settings){

        this.settings=settings;

        for(Class<? extends AllocationDecider > decider: ClusterModule.DEFAULT_ALLOCATION_DECIDERS){
            registerAllocationDecider(decider);
        }
    }

    private void registerAllocationDecider(Class<? extends AllocationDecider> allocationDecider) {
        allocationDeciders.registerExtension(allocationDecider);

    }

    protected void configure() {

        bind(GatewayAllocator.class).asEagerSingleton();
        bind(AllocationService.class).asEagerSingleton();
        bind(DiscoveryNodeService.class).asEagerSingleton();
        bind(ClusterService.class).to(InternalClusterService.class).asEagerSingleton();
        bind(OperateRouting.class).asEagerSingleton();
        bind(DiscoveryNodeService.class).asEagerSingleton();
        bind(RoutingService.class).asEagerSingleton();

    }
}
