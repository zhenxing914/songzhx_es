package com.chinatelecom.di.gateway;

import com.chinatelecom.di.common.component.AbstractComponent;
import com.chinatelecom.di.common.settings.Settings;
import com.google.inject.Inject;

/**
 * Created by song on 2018/1/5.
 */
public class GatewayAllocator  extends AbstractComponent{

    @Inject
    public GatewayAllocator(Settings settings) {
        super(settings);
    }
}
