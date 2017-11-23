package com.chinatelecom.di.common.io.stream;

import java.io.IOException;

/**
 * Created by song on 2017/11/8.
 */
public interface Streamable {

    void readFrom(StreamInput in)  throws IOException;

    void writeTo(StreamOutPut out) throws IOException;


}
