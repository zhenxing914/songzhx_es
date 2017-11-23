package com.chinatelecom.di.discovery;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Module;

/**
 * Created by song on 2017/10/16.
 */
public class DiscoveryModule extends  AbstractModule {
    public void configure() {

        bind(DiscoveryService.class).asEagerSingleton();
    }
}
