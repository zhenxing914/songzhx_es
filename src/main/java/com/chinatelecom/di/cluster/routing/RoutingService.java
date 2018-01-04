package com.chinatelecom.di.cluster.routing;

import com.chinatelecom.di.cluster.ClusterChangeEvent;
import com.chinatelecom.di.cluster.ClusterStateListener;
import com.chinatelecom.di.common.component.AbstractLifecycleComponent;
import com.chinatelecom.di.common.settings.Settings;

/**
 * Created by song on 2018/1/4.
 */
public class RoutingService extends AbstractLifecycleComponent<RoutingService> implements ClusterStateListener{

    protected RoutingService(Settings settings) {
        super(settings);
    }

    @Override
    public void clusterChanged(ClusterChangeEvent event) {

    }

    @Override
    protected void doStart() {

    }
}
