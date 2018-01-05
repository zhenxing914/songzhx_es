package com.chinatelecom.di.cluster.routing.allocation.decider;

import com.chinatelecom.di.common.settings.Settings;
import com.google.inject.Inject;

import java.util.Set;

/**
 * Created by song on 2018/1/5.
 */
public class AllocationDeciders extends AllocationDecider {

    private final AllocationDecider[] allocationDeciders;

    public AllocationDeciders(Settings settings,AllocationDecider[] allocationDeciders){
        super(settings);
        this.allocationDeciders=allocationDeciders;
    }

    @Inject
    public AllocationDeciders(Settings settings,Set<AllocationDecider> allocations){

        this(settings,allocations.toArray(new AllocationDecider[allocations.size()]));

    }


}
