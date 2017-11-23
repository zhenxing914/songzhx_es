package com.chinatelecom.di.cluster;


import com.chinatelecom.di.common.io.stream.StreamInput;
import com.chinatelecom.di.common.io.stream.StreamOutPut;
import com.chinatelecom.di.common.io.stream.Streamable;

import java.io.IOException;

/**
 * Created by song on 2017/11/7.
 */
public class ClusterName  implements Streamable {

    public static final ClusterName DEFAULT=new ClusterName("elasticsearch".intern());

    private  String value;


    public ClusterName(String value){
        this.value=value;

    }


    @Override
    public void readFrom(StreamInput in) throws IOException {
        value= in.readString().intern();

    }

    @Override
    public void writeTo(StreamOutPut out) throws IOException {

    }
}
