package com.chinatelecom.di.cluster.service;

import com.chinatelecom.di.cluster.ClusterStateListener;
import com.chinatelecom.di.common.component.LifecycleComponent;

/**
 * Created by song on 2017/10/16.
 */
public interface ClusterService extends LifecycleComponent<ClusterService> {

    /**
     *    Adds s listener for updated cluster states
     */

    void add(ClusterStateListener listener);

}
