package com.chinatelecom.di.cluster.routing.allocation.allocator;

import com.chinatelecom.di.cluster.routing.allocation.decider.AllocationDecider;
import com.chinatelecom.di.common.settings.Settings;

/**
 * Created by song on 2018/1/5.
 */
public class FilterAllocationDecider extends  AllocationDecider {
    public FilterAllocationDecider(Settings settings) {
        super(settings);
    }
}
