package com.chinatelecom.di.transport;

import com.google.inject.AbstractModule;
import com.sun.jdi.connect.spi.TransportService;

/**
 * Created by song on 2017/11/23.
 */
public class TransportModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(TransportS)
    }
}
