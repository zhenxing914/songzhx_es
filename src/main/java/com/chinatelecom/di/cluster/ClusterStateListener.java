package com.chinatelecom.di.cluster;

/**
 * Created by song on 2017/11/21.
 */
public interface ClusterStateListener {

    void clusterChanged(ClusterChangeEvent event);
}
