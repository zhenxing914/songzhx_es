package com.chinatelecom.di.ThreadPool;

import com.google.inject.AbstractModule;

/**
 * Created by song on 2017/11/24.
 */
public class ThreadPoolModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ThreadPool.class).asEagerSingleton();
    }
}
