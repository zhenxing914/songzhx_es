package com.chinatelecom.di.common.io.stream;

import java.io.IOException;

/**
 * Created by song on 2017/11/7.
 */
public interface StreamableReader<T> {

    T readFrom(StreamInput in) throws IOException;
}
