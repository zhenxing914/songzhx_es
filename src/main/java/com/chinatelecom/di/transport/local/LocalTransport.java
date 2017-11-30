package com.chinatelecom.di.transport.local;

import com.chinatelecom.di.common.component.AbstractLifecycleComponent;
import com.chinatelecom.di.common.settings.Settings;
import com.chinatelecom.di.transport.Transport;
import com.google.inject.Inject;

/**
 * Created by song on 2017/11/30.
 */
public class LocalTransport extends AbstractLifecycleComponent<Transport> implements  Transport{
    @Inject
    protected LocalTransport(Settings settings) {
        super(settings);
    }

    @Override
    protected void doStart() {

    }
}
