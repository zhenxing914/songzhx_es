package com.chinatelecom.di.node.settings;

import com.chinatelecom.di.cluster.ClusterChangeEvent;
import com.chinatelecom.di.cluster.ClusterStateListener;
import com.chinatelecom.di.common.component.AbstractComponent;

/**
 * Created by song on 2017/11/21.
 */
public class NodeSettingsService extends AbstractComponent implements ClusterStateListener {
    @Override
    public void clusterChanged(ClusterChangeEvent event) {

    }
}
