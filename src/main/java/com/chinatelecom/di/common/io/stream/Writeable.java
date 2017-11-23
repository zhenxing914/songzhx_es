package com.chinatelecom.di.common.io.stream;

import org.omg.CORBA.portable.Streamable;

import java.io.IOException;

/**
 * Created by song on 2017/11/7.
 */
public interface Writeable<T> extends StreamableReader<T> {

    void writeTo(StreamOutPut out) throws IOException;
}
