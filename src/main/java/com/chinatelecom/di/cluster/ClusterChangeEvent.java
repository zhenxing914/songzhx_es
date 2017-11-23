package com.chinatelecom.di.cluster;

import com.chinatelecom.di.cluster.node.DiscoveryNodes;


/**
 * Created by song on 2017/11/21.
 */
public class ClusterChangeEvent {

    public final String source ;

    public final ClusterState previousState;

    public final ClusterState state;

    //public final DiscoveryNodes.Delta nodesDelta;

    public ClusterChangeEvent(String source,ClusterState state,ClusterState previousState){

        //Objects.requireNonNull(source,"source must not be null");

        this.source=source;
        this.previousState=previousState;
        this.state=state;
        //this.nodesDelta=state.nodes().delta(previousState.nodes);
    }
}
