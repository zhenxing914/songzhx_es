package com.chinatelecom.di.cluster.routing.allocation;

import com.chinatelecom.di.cluster.routing.allocation.allocator.ShardsAllocator;
import com.chinatelecom.di.common.component.AbstractComponent;
import com.chinatelecom.di.common.settings.Settings;
import com.chinatelecom.di.gateway.GatewayAllocator;
import com.google.inject.Inject;

/**
 * Created by song on 2018/1/5.
 */
public class ShardsAllocators  extends AbstractComponent implements ShardsAllocator {


    private final GatewayAllocator gatewayAllocator;
    private final ShardsAllocator shardsAllocator;


   @Inject
    public ShardsAllocators(Settings settings, GatewayAllocator gatewayAllocator, ShardsAllocator shardsAllocator) {
       super(settings);
       this.gatewayAllocator=gatewayAllocator;
       this.shardsAllocator=shardsAllocator;

    }


}
