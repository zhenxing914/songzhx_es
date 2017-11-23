package com.chinatelecom.di.cluster.routing;

import com.chinatelecom.di.cluster.Diff;
import com.chinatelecom.di.cluster.Diffable;
import com.chinatelecom.di.common.io.stream.StreamInput;
import com.chinatelecom.di.common.io.stream.StreamOutPut;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by song on 2017/11/21.
 */

public class RoutingTable  implements  Iterable<IndexRoutingTable>,Diffable<RoutingTable>{

    public static final RoutingTable EMPTY_ROUTING_TABLE=null;

    @Override
    public RoutingTable readFrom(StreamInput in) throws IOException {
        return null;
    }

    @Override
    public void writeTo(StreamOutPut out) throws IOException {

    }

    @Override
    public Diff<RoutingTable> diff(RoutingTable previousState) {
        return null;
    }

    @Override
    public Diff<RoutingTable> readDiffFrom(StreamInput in) throws IOException {
        return null;
    }

    @Override
    public Iterator<IndexRoutingTable> iterator() {
        return null;
    }

}
