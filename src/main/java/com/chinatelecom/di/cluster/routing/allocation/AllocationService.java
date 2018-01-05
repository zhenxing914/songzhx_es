package com.chinatelecom.di.cluster.routing.allocation;

import com.chinatelecom.di.cluster.ClusterInfoService;
import com.chinatelecom.di.cluster.routing.allocation.decider.AllocationDeciders;
import com.chinatelecom.di.common.component.AbstractComponent;
import com.chinatelecom.di.common.settings.Settings;
import com.google.inject.Inject;

/**
 * Created by song on 2018/1/5.
 */
public class AllocationService  extends AbstractComponent{
    private  final AllocationDeciders allocationDeciders;
    private final ClusterInfoService clusterInfoService;
    private final ShardsAllocators shardsAllocators;

    @Inject
    public AllocationService(Settings settings, AllocationDeciders allocationDeciders, ShardsAllocators shardsAllocators, ClusterInfoService clusterInfoService) {
        super(settings);
        this.allocationDeciders=allocationDeciders;
        this.shardsAllocators=shardsAllocators;
        this.clusterInfoService=clusterInfoService;
    }

}
