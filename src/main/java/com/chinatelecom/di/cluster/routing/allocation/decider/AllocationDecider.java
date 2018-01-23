package com.chinatelecom.di.cluster.routing.allocation.decider;

import com.chinatelecom.di.common.component.AbstractComponent;
import com.chinatelecom.di.common.settings.Settings;

/**
 * Created by song on 2018/1/5.
 */
public abstract  class AllocationDecider extends AbstractComponent{


    protected AllocationDecider(Settings settings) {
        super(settings);
    }
}
