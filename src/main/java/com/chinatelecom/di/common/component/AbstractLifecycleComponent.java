package com.chinatelecom.di.common.component;

import com.chinatelecom.di.common.settings.Settings;

/**
 * Created by song on 2017/10/18.
 */
public abstract  class AbstractLifecycleComponent<T> extends  AbstractComponent implements LifecycleComponent<T>{



    protected AbstractLifecycleComponent(Settings settings){
        super(settings);
    }

    @Override
    public T start(){
        doStart();
        return (T)this;
    }

    protected abstract void doStart();

}
