package com.chinatelecom.di.cluster;

import com.chinatelecom.di.cluster.block.ClusterBlock;
import com.chinatelecom.di.cluster.block.ClusterBlocks;
import com.chinatelecom.di.cluster.metadata.MetaData;
import com.chinatelecom.di.cluster.node.DiscoveryNodes;
import com.chinatelecom.di.cluster.routing.RoutingNodes;
import com.chinatelecom.di.cluster.routing.RoutingTable;
import com.chinatelecom.di.common.Strings;
import com.chinatelecom.di.common.collect.ImmutableOpenMap;
import com.chinatelecom.di.common.io.stream.StreamInput;
import com.chinatelecom.di.common.io.stream.StreamOutPut;
import com.chinatelecom.di.common.xcontent.ToXContent;

import java.io.IOException;


/**
 * Created by song on 2017/11/7.
 */


public class ClusterState implements Diffable<ClusterState>{



    public static enum ClusterStateStatus{
        UNKNOWN((byte)0),
        RECEIVED((byte)1),
        BEING_APPLIED((byte)2),
        APPLIED((byte)3);

        private final byte id;

        ClusterStateStatus(byte id) {
            this.id=id;
        }

        public byte id(){
            return this.id;
        }
    }

    public interface Custom extends Diffable<Custom>,ToXContent {
        String type();
    }


    public static final String UNKNOWN_UUID="_na_";

    public static final long UNKNOW_VERSION=-1;

    private final long version;

    private final String stateUUID;

    private final RoutingTable routingTable;

    private final DiscoveryNodes nodes;

    private final MetaData metaData;

    private final ClusterBlocks  blocks;

    private final ImmutableOpenMap<String, Custom> customs;

    private  final ClusterName clusterName;

    private final boolean wasReadFromDiff;

    private volatile RoutingNodes routingNodes;

    private volatile ClusterStateStatus status;

    public static Builder builder(ClusterName clusterName){
        return  new Builder(clusterName);
    }


    public static Builder builder(ClusterState clusterState){
        return new Builder(clusterState);
    }

    public ClusterState(long version, String stateUUID,ClusterState state){
        this(state.clusterName,version,stateUUID,state.metaData,state.routingTable,state.nodes,state.blocks,state.customs(),false);

    }



    public ClusterState(ClusterName clusterName, long version, String stateUUID,MetaData metaData,
                        RoutingTable routingTable ,DiscoveryNodes nodes, ClusterBlocks clusterBlocks,
                        ImmutableOpenMap<String,Custom> customs,boolean wasReadFromDiff){
            this.version=version;
            this.stateUUID=stateUUID;
            this.clusterName=clusterName;
            this.metaData=metaData;
            this.routingTable=routingTable;
            this.nodes=nodes;
            this.blocks=clusterBlocks;
            this.customs=customs;
            this.status=ClusterStateStatus.UNKNOWN;
            this.wasReadFromDiff=wasReadFromDiff;


    }

    public ImmutableOpenMap<String ,Custom> customs(){
        return this.customs;
    }

    public static class Builder{

        private final ClusterName clusterName;
        private long version =0;
        private String uuid=UNKNOWN_UUID;
        private MetaData metaData=MetaData.EMPTY_META_DATA;
        private RoutingTable routingTable=RoutingTable.EMPTY_ROUTING_TABLE;
        private DiscoveryNodes nodes=DiscoveryNodes.EMPTY_NODES;
        private ClusterBlocks blocks=ClusterBlocks.EMPTY_CLUSTER_BLOCK;
        private  final ImmutableOpenMap.Builder<String,Custom> customs;
        private boolean fromDiff;

        public Builder(ClusterState state){
            this.clusterName=state.clusterName;
            this.version=state.version;
            this.uuid=state.stateUUID;
            this.routingTable=state.routingTable;
            this.metaData=state.metaData;
            this.blocks=state.blocks;
            this.customs=ImmutableOpenMap.builder(state.customs);
            this.fromDiff=false;
        }


        public Builder(ClusterName clusterName){
            customs=ImmutableOpenMap.builder();
            this.clusterName=clusterName;
        }

        public ClusterState build(){
            if(UNKNOWN_UUID.equals(uuid)){
                uuid= Strings.randomBase64UUID();

            }
            return new ClusterState(clusterName,version,uuid,metaData,routingTable,nodes,blocks,customs.build(),fromDiff);
        }


        public Builder blocks(ClusterBlocks.Builder blockBuilder){
            return blocks(blockBuilder.build());

        }


        public Builder blocks(ClusterBlocks blocks)
        {
            this.blocks=blocks;
            return this;
        }
    }



    @Override
    public ClusterState readFrom(StreamInput in) throws IOException {
        return null;
    }

    @Override
    public void writeTo(StreamOutPut out) throws IOException {

    }

    @Override
    public Diff<ClusterState> diff(ClusterState previousState) {
        return null;
    }

    @Override
    public Diff<ClusterState> readDiffFrom(StreamInput in) throws IOException {
        return null;
    }
}
