package com.chinatelecom.di.discovery.zen;

import com.chinatelecom.di.cluster.routing.RoutingService;
import com.chinatelecom.di.common.component.AbstractLifecycleComponent;
import com.chinatelecom.di.common.settings.Settings;
import com.chinatelecom.di.discovery.Discovery;
import com.chinatelecom.di.discovery.zen.ping.PingContextProvider;

/**
 * Created by song on 2018/1/5.
 */
public class ZenDiscovery extends AbstractLifecycleComponent<Discovery> implements Discovery,PingContextProvider {
    public ZenDiscovery(Settings settings) {
        super(settings);
    }

    @Override
    public Discovery start() {
        return null;
    }

    @Override
    protected void doStart() {

    }

    @Override
    public void setRoutingService(RoutingService routingService) {

    }
}
