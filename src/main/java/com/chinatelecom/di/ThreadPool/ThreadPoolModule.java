package com.chinatelecom.di.ThreadPool;

import com.google.inject.AbstractModule;

/**
 * Created by song on 2017/11/24.
 */
public class ThreadPoolModule extends AbstractModule {

    private final ThreadPool threadPool;

    public ThreadPoolModule(ThreadPool threadPool){
        this.threadPool=threadPool;
    }
    @Override
    protected void configure() {
        bind(ThreadPool.class).toInstance(threadPool);
    }
}
