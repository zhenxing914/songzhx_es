package com.chinatelecom.di.cluster.routing.allocation;

import com.chinatelecom.di.cluster.routing.allocation.allocator.ShardsAllocator;
import com.chinatelecom.di.common.component.AbstractComponent;
import com.chinatelecom.di.common.settings.Settings;
import com.google.inject.Inject;

/**
 * Created by song on 2018/1/5.
 */
public class BalancedShardsAllocator extends AbstractComponent implements ShardsAllocator{


    @Inject
    public BalancedShardsAllocator(Settings settings) {
        super(settings);
    }


}
