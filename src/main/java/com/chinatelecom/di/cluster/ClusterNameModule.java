package com.chinatelecom.di.cluster;

import com.chinatelecom.di.common.settings.Settings;
import com.google.inject.AbstractModule;

/**
 * Created by song on 2017/11/24.
 */
public class ClusterNameModule extends AbstractModule {

    private  final Settings settings;


    public ClusterNameModule(Settings settings){
        this.settings=settings;
    }
    @Override
    protected void configure() {
        bind(ClusterName.class).toInstance(ClusterName.clusterNameFromSetting(settings));
    }
}
