package com.chinatelecom.di.cluster;

import com.chinatelecom.di.common.io.stream.StreamOutPut;

import java.io.IOException;

/**
 * Created by song on 2017/11/7.
 */
public interface Diff<T> {

    T apply(T part);



    void writeTo(StreamOutPut out ) throws IOException;
}
