package com.chinatelecom.di.transport;

import com.chinatelecom.di.common.io.stream.StreamInput;
import com.chinatelecom.di.common.io.stream.StreamOutPut;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by song on 2018/2/12.
 */
public final class InetSocketTransportAddress implements TransportAddress {

    public InetSocketTransportAddress(InetSocketAddress boundAddress) {
    }

    @Override
    public TransportAddress readFrom(StreamInput in) throws IOException {
        return null;
    }

    @Override
    public void writeTo(StreamOutPut out) throws IOException {

    }

    @Override
    public String getHost() {
        return null;
    }
}
