package com.chinatelecom.di.node;

import com.chinatelecom.di.node.settings.NodeSettingsService;
import com.google.inject.AbstractModule;

/**
 * Created by song on 2017/11/24.
 */
public class NodeModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(NodeSettingsService.class).asEagerSingleton();

    }
}
