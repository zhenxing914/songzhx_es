package com.chinatelecom.di.ThreadPool;

import com.chinatelecom.di.common.component.AbstractLifecycleComponent;
import com.chinatelecom.di.common.settings.Settings;
import com.google.inject.Inject;

/**
 * Created by song on 2017/11/24.
 */
public class ThreadPool  extends AbstractLifecycleComponent<ThreadPool>{


    public ThreadPool(Settings settings) {
        super(settings);
    }

    @Override
    protected void doStart() {

    }
}
