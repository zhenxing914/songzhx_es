package com.chinatelecom.di.cluster.routing.allocation.decider;

import com.chinatelecom.di.common.settings.Settings;

/**
 * Created by song on 2018/1/5.
 */
public class SameShardAllocationDecider extends AllocationDecider {
    public SameShardAllocationDecider(Settings settings) {
        super(settings);
    }
}
