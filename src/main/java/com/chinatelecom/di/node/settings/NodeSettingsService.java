package com.chinatelecom.di.node.settings;

import com.chinatelecom.di.cluster.ClusterChangeEvent;
import com.chinatelecom.di.cluster.ClusterStateListener;
import com.chinatelecom.di.common.component.AbstractComponent;
import com.chinatelecom.di.common.settings.Settings;
import com.google.inject.Inject;

/**
 * Created by song on 2017/11/21.
 */
public class NodeSettingsService extends AbstractComponent implements ClusterStateListener {

    @Inject
    public NodeSettingsService(Settings settings) {
        super(settings);
    }

    @Override
    public void clusterChanged(ClusterChangeEvent event) {

    }
}
