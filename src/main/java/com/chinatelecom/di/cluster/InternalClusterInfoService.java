package com.chinatelecom.di.cluster;

import com.chinatelecom.di.common.component.AbstractComponent;
import com.chinatelecom.di.common.settings.Settings;
import com.chinatelecom.di.node.settings.NodeSettingsService;
import com.google.inject.Inject;

/**
 * Created by song on 2018/1/23.
 */
public class InternalClusterInfoService extends AbstractComponent implements ClusterInfoService ,ClusterStateListener{

    /**
     * 未完成  需要添加 TransportNodeStatsAction
     * TransportIndicesStatsAction
     * ClusterService
     * ThreadPool
     * @param settings
     * @param nodeSettingsService
     */
    @Inject
    public InternalClusterInfoService(Settings settings, NodeSettingsService nodeSettingsService) {
        super(settings);
    }

    @Override
    public void clusterChanged(ClusterChangeEvent event) {

    }
}
