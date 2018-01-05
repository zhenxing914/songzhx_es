package com.chinatelecom.di.transport;

/**
 * Created by song on 2018/1/4.
 */
public interface TransportServiceAdapter {


    void received(long size);

    void sent(long size);


}
