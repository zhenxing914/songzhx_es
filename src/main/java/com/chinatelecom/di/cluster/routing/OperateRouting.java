package com.chinatelecom.di.cluster.routing;

import com.chinatelecom.di.common.component.AbstractComponent;
import com.chinatelecom.di.common.settings.Settings;
import com.google.inject.Inject;

/**
 * Created by song on 2017/11/21.
 */
public class OperateRouting  extends AbstractComponent{
    @Inject
    public OperateRouting(Settings settings) {
        super(settings);
    }
}
