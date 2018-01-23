package com.chinatelecom.di.cluster.routing.allocation.decider;

import com.chinatelecom.di.common.settings.Settings;
import com.google.inject.Inject;

/**
 * Created by song on 2018/1/5.
 */
public class SameShardAllocationDecider extends AllocationDecider {

    @Inject
    public SameShardAllocationDecider(Settings settings) {
        super(settings);
    }
}
