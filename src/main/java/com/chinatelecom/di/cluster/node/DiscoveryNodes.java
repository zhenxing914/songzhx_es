package com.chinatelecom.di.cluster.node;

import com.chinatelecom.di.common.collect.ImmutableOpenMap;

/**
 * Created by song on 2017/10/18.
 *
 * this class holds all discoveryNode in the cluster and provides  convenience methods to
 * access,nodify merge  /diff discovery nodes
 */
public class DiscoveryNodes {

    public static final DiscoveryNodes EMPTY_NODES=null;

   public static Builder builder(){
        return new Builder();
    }


    public static class Builder {

        private final ImmutableOpenMap.Builder<String,DiscoveryNode> nodes;

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
    }


}
