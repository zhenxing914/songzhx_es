package com.chinatelecom.di.common.component;

import com.chinatelecom.di.common.settings.Settings;

/**
 * Created by song on 2017/10/18.
 */
public class AbstractComponent {
    protected final Settings settings;

    public AbstractComponent(Settings settings){
        this.settings=settings;
    }
}
