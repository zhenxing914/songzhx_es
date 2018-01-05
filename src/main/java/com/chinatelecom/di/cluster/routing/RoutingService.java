package com.chinatelecom.di.cluster.routing;

import com.chinatelecom.di.ThreadPool.ThreadPool;
import com.chinatelecom.di.cluster.ClusterChangeEvent;
import com.chinatelecom.di.cluster.ClusterInfoService;
import com.chinatelecom.di.cluster.ClusterStateListener;
import com.chinatelecom.di.cluster.routing.allocation.AllocationService;
import com.chinatelecom.di.cluster.service.ClusterService;
import com.chinatelecom.di.common.component.AbstractLifecycleComponent;
import com.chinatelecom.di.common.settings.Settings;
import com.google.inject.Inject;

/**
 * Created by song on 2018/1/4.
 */
public class RoutingService extends AbstractLifecycleComponent<RoutingService> implements ClusterStateListener{

    final ThreadPool threadPool;
    private final ClusterService clusterService;
    private final AllocationService allocationService;


    @Inject
    protected RoutingService(Settings settings, ThreadPool threadPool, ClusterService clusterService,AllocationService allocationService) {
        super(settings);
        this.threadPool=threadPool;
        this.allocationService=allocationService;
        this.clusterService=clusterService;
    }

    @Override
    public void clusterChanged(ClusterChangeEvent event) {

    }

    @Override
    protected void doStart() {

    }
}
