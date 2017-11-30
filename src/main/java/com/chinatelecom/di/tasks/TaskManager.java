package com.chinatelecom.di.tasks;

import com.chinatelecom.di.cluster.ClusterChangeEvent;
import com.chinatelecom.di.cluster.ClusterStateListener;
import com.chinatelecom.di.common.component.AbstractComponent;
import com.chinatelecom.di.common.settings.Settings;

/**
 * Created by song on 2017/11/30.
 */

public class TaskManager extends AbstractComponent implements ClusterStateListener {

    public TaskManager(Settings settings) {
        super(settings);
    }

    @Override
    public void clusterChanged(ClusterChangeEvent event) {

    }
}
