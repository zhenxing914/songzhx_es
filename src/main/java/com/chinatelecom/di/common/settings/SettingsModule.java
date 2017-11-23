package com.chinatelecom.di.common.settings;

import com.google.inject.AbstractModule;

/**
 * Created by song on 2017/11/23.
 */
public class SettingsModule  extends AbstractModule{

    private final Settings settings;

    public SettingsModule(Settings settings){
        this.settings=settings;
    }

    @Override
    protected void configure() {
        bind(Settings.class).toInstance(settings);
    }
}
