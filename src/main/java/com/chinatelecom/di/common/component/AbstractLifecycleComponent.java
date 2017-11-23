package com.chinatelecom.di.common.component;

/**
 * Created by song on 2017/10/18.
 */
public abstract  class AbstractLifecycleComponent<T> extends  AbstractComponent implements LifecycleComponent<T>{

    @Override
    public T start(){
        doStart();

        return (T)this;
    }

    protected abstract void doStart();

}
