package com.chinatelecom.di.cluster;

import com.chinatelecom.di.cluster.routing.OperateRouting;
import com.chinatelecom.di.cluster.routing.RoutingService;
import com.chinatelecom.di.cluster.routing.allocation.AllocationService;
import com.chinatelecom.di.cluster.routing.allocation.BalancedShardsAllocator;
import com.chinatelecom.di.cluster.routing.allocation.allocator.FilterAllocationDecider;
import com.chinatelecom.di.cluster.routing.allocation.allocator.ShardsAllocator;
import com.chinatelecom.di.cluster.routing.allocation.decider.AllocationDecider;
import com.chinatelecom.di.cluster.routing.allocation.decider.AllocationDeciders;
import com.chinatelecom.di.cluster.routing.allocation.decider.SameShardAllocationDecider;
import com.chinatelecom.di.cluster.service.ClusterService;
import com.chinatelecom.di.cluster.service.InternalClusterService;
import com.chinatelecom.di.common.settings.Settings;
import com.chinatelecom.di.common.util.ExtensionPoint;
import com.chinatelecom.di.discovery.DiscoveryNodeService;

import com.chinatelecom.di.gateway.GatewayAllocator;
import com.google.inject.AbstractModule;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by song on 2017/10/16.
 */
public class ClusterModule extends AbstractModule {

    public static final String BLANCEED_ALLOCATOR="balanced";//default
    public static final String EVEN_SHARD_COUNT_ALLLOCATOR="even_shard";
    public static final String SHARDS_ALLOCATION_TYPE_KEY="cluster.routing.allocation.tyoe";
    public static final List<Class<? extends AllocationDecider>> DEFAULT_ALLOCATION_DECIDERS=
      Collections.unmodifiableList(
              Arrays.asList(
                      SameShardAllocationDecider.class,
                      FilterAllocationDecider.class
              )
      );

    private  final Settings settings;


    Class<? extends  ClusterInfoService> clusterInfoServiceImpl=InternalClusterInfoService.class;

    private final ExtensionPoint.SeletedType<ShardsAllocator> shardsAllocators=
            new ExtensionPoint.SeletedType<>("shards_allocator",ShardsAllocator.class);

    private final ExtensionPoint.ClassSet<AllocationDecider> allocationDeciders=
            new ExtensionPoint.ClassSet<AllocationDecider>
                    ("allcation_decider",AllocationDecider.class, AllocationDeciders.class);


    public ClusterModule(Settings settings){

        this.settings=settings;

        for(Class<? extends AllocationDecider > decider: ClusterModule.DEFAULT_ALLOCATION_DECIDERS){
           registerAllocationDecider(decider);
        }

        registerShardsAllocator(ClusterModule.BLANCEED_ALLOCATOR, BalancedShardsAllocator.class);
        registerShardsAllocator(ClusterModule.EVEN_SHARD_COUNT_ALLLOCATOR,BalancedShardsAllocator.class);
    }

    public void registerShardsAllocator(String name,Class<? extends ShardsAllocator> clazz ){
        shardsAllocators.registerExtension(name,clazz);
    }

    private void registerAllocationDecider(Class<? extends AllocationDecider> allocationDecider) {
        allocationDeciders.registerExtension(allocationDecider);

    }

    @Override
    protected void configure() {

        //bind ShardSAllocator
        String shardsAllocatorType= shardsAllocators.bindType(binder(),settings,ClusterModule.SHARDS_ALLOCATION_TYPE_KEY,ClusterModule.BLANCEED_ALLOCATOR);

        allocationDeciders.bind(binder());


        bind(ClusterInfoService.class).to(clusterInfoServiceImpl).asEagerSingleton();
        bind(GatewayAllocator.class).asEagerSingleton();
        bind(AllocationService.class).asEagerSingleton();
        bind(DiscoveryNodeService.class).asEagerSingleton();
        bind(ClusterService.class).to(InternalClusterService.class).asEagerSingleton();
        bind(OperateRouting.class).asEagerSingleton();
        bind(DiscoveryNodeService.class).asEagerSingleton();
        bind(RoutingService.class).asEagerSingleton();

    }
}
