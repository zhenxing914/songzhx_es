package com.chinatelecom.di.transport;

import com.chinatelecom.di.common.io.stream.StreamInput;
import com.chinatelecom.di.common.io.stream.StreamOutPut;
import com.chinatelecom.di.common.io.stream.Streamable;

import java.io.IOException;

/**
 * Created by song on 2018/2/11.
 */
public class BoundTransportAddress implements Streamable {

    private TransportAddress[] boundAddresses;

    private TransportAddress publishAddress;


    public BoundTransportAddress(TransportAddress[] boundAddresses, TransportAddress publishAddress) {

        this.boundAddresses=boundAddresses;
        this.publishAddress=publishAddress;

    }

    @Override
    public void readFrom(StreamInput in) throws IOException {

    }

    @Override
    public void writeTo(StreamOutPut out) throws IOException {

    }
}
