package com.chinatelecom.di.cluster;

import com.chinatelecom.di.common.io.stream.StreamInput;
import com.chinatelecom.di.common.io.stream.Writeable;

import java.io.IOException;

/**
 * Created by song on 2017/11/7.
 */
public interface Diffable<T> extends Writeable<T> {

    Diff<T> diff(T previousState);


    Diff<T> readDiffFrom(StreamInput in) throws IOException;


}
