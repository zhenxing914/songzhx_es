package com.chinatelecom.di.cluster.service;

import com.chinatelecom.di.ThreadPool.ThreadPool;
import com.chinatelecom.di.Version;
import com.chinatelecom.di.cluster.ClusterName;
import com.chinatelecom.di.cluster.ClusterState;
import com.chinatelecom.di.cluster.node.DiscoveryNode;
import com.chinatelecom.di.cluster.node.DiscoveryNodes;
import com.chinatelecom.di.cluster.routing.OperateRouting;
import com.chinatelecom.di.common.component.AbstractLifecycleComponent;
import com.chinatelecom.di.common.settings.Settings;
import com.chinatelecom.di.discovery.DiscoveryNodeService;
import com.chinatelecom.di.discovery.DiscoveryService;
import com.chinatelecom.di.node.settings.NodeSettingsService;
import com.chinatelecom.di.transport.TransportService;


import javax.inject.Inject;

/**
 * Created by song on 2017/10/16.
 */
public class InternalClusterService extends AbstractLifecycleComponent<ClusterService> implements ClusterService {

    private volatile ClusterState clusterState;


    @Inject
    InternalClusterService(Settings settings, DiscoveryService discoveryService, OperateRouting operateRouting,
                           TransportService transportService, NodeSettingsService nodeSettingsService,
                           ThreadPool threadPool, ClusterName clusterName, DiscoveryNodeService discoveryNodeService,
                           Version version)
    {

        //will be replaced on doStart
        this.clusterState= ClusterState.builder(clusterName).build();
    }


    @Override
    protected void doStart() {
        System.out.println("InternalClusterService doStart .");


        //this.clusterState=ClusterState.builder(clusterState).blocks(initalBlocks).build();
        DiscoveryNode localNode = new DiscoveryNode("node1", "0001", "localhost", "nodeAttribute", "version1");
        DiscoveryNodes.Builder nodeBuilder=DiscoveryNodes.builder().put(localNode).localNodeId(localNode.id());


        //this.clusterState=ClusterState.builder(clusterState).nodes(nodeBuilder).block(initalBlocks).build();



    }
}
