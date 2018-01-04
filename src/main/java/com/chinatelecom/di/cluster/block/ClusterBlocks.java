package com.chinatelecom.di.cluster.block;

import com.chinatelecom.di.common.collect.ImmutableOpenMap;
import com.google.common.collect.*;

import java.util.Map;
import java.util.Set;

/**
 * Created by song on 2017/11/21.
 */
public class ClusterBlocks {


    private final ImmutableSet<ClusterBlock> global;

    private final ImmutableMap<String , ImmutableSet<ClusterBlock>> indicesBlocks;

    public static final ClusterBlocks EMPTY_CLUSTER_BLOCK=new ClusterBlocks(ImmutableSet.<ClusterBlock>of(),
            ImmutableMap.<String,ImmutableSet<ClusterBlock>>of());


    public ClusterBlocks(ImmutableSet<ClusterBlock>  global, ImmutableMap<String, ImmutableSet<ClusterBlock>> indicesBlocks) {

        this.global=global;
        this.indicesBlocks=indicesBlocks;


    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {

        private Set<ClusterBlock> global= Sets.newHashSet();

        private Map<String,Set<ClusterBlock>> indices= Maps.newHashMap();


        public ClusterBlocks build() {

            ImmutableMap.Builder<String,ImmutableSet<ClusterBlock>>   indicesBuilder= ImmutableBiMap.builder();

            for(Map.Entry<String,Set<ClusterBlock>> entry:indices.entrySet()) {

                indicesBuilder.put(entry.getKey(),ImmutableSet.copyOf(entry.getValue()));

            }
            return new ClusterBlocks(ImmutableSet.copyOf(global),indicesBuilder.build());
        }

        public Builder addGlobalBlock(ClusterBlock clusterBlock){

            global.add(clusterBlock);

            return this;

        }
    }
}
