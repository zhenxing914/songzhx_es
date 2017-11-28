package com.chinatelecom.di.cluster.node;

import com.chinatelecom.di.Version;
import com.chinatelecom.di.common.collect.ImmutableOpenMap;
import com.google.common.collect.ImmutableMap;

/**
 * Created by song on 2017/10/18.
 *
 * this class holds all discoveryNode in the cluster and provides  convenience methods to
 * access,nodify merge  /diff discovery nodes
 */
public class DiscoveryNodes {

    public static final DiscoveryNodes EMPTY_NODES=null;

    private final ImmutableOpenMap<String,DiscoveryNode> nodes;
    private final ImmutableOpenMap<String,DiscoveryNode> dataNodes;
    private final ImmutableOpenMap<String,DiscoveryNode> masterNodes;


    private final String masterNodeId;
    private final String localNodeId;
    private final Version minNodeVersion;
    private final Version minNonClientNodeVersion;



    public static Builder builder(){
        return new Builder();
    }


    public DiscoveryNodes(ImmutableOpenMap<String,DiscoveryNode> nodes,ImmutableOpenMap<String,DiscoveryNode> dataNodes,
                           ImmutableOpenMap<String,DiscoveryNode> masterNodes,String masterNodeId,String localNodeId,
                           Version minNodeVersion, Version minNonClientNodeVersion)
    {
        this.nodes=nodes;
        this.dataNodes=dataNodes;
        this.masterNodes=masterNodes;
        this.masterNodeId=masterNodeId;
        this.localNodeId=localNodeId;
        this.minNodeVersion=minNodeVersion;
        this.minNonClientNodeVersion=minNonClientNodeVersion;

    }
    public static class Builder {

        private final ImmutableOpenMap.Builder<String,DiscoveryNode> nodes;

        private String masterNodeId;
        private String localNodeId;

        public Builder(){

            nodes=ImmutableOpenMap.builder();

        }

        public Builder put(DiscoveryNode node) {
            nodes.put(node.id(),node);
            return this;
        }

        public Builder localNodeId(String localNodeId) {
            this.localNodeId=localNodeId;
            return this;
        }

        public DiscoveryNodes build() {
            ImmutableOpenMap.Builder<String,DiscoveryNode> dataNodesBuilder=ImmutableOpenMap.builder();
            ImmutableOpenMap.Builder<String,DiscoveryNode> masterNodesBuilder=ImmutableOpenMap.builder();
            Version minNodeVersion= Version.CURRENT;
            Version minNonClientNodeVersion =Version.CURRENT;

            return new DiscoveryNodes(nodes.build(),dataNodesBuilder.build(),masterNodesBuilder.build(),masterNodeId, localNodeId,minNodeVersion,minNonClientNodeVersion);
        }
    }


}
