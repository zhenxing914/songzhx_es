package com.chinatelecom.di.cluster.service;

import com.chinatelecom.di.ThreadPool.ThreadPool;
import com.chinatelecom.di.Version;
import com.chinatelecom.di.cluster.ClusterChangeEvent;
import com.chinatelecom.di.cluster.ClusterName;
import com.chinatelecom.di.cluster.ClusterState;
import com.chinatelecom.di.cluster.ClusterStateListener;
import com.chinatelecom.di.cluster.block.ClusterBlocks;
import com.chinatelecom.di.cluster.node.DiscoveryNode;
import com.chinatelecom.di.cluster.node.DiscoveryNodes;
import com.chinatelecom.di.cluster.routing.OperateRouting;
import com.chinatelecom.di.common.component.AbstractLifecycleComponent;
import com.chinatelecom.di.common.settings.Settings;
import com.chinatelecom.di.discovery.DiscoveryNodeService;
import com.chinatelecom.di.discovery.DiscoveryService;
import com.chinatelecom.di.node.settings.NodeSettingsService;
import com.chinatelecom.di.tasks.TaskManager;
import com.chinatelecom.di.transport.TransportService;


import javax.inject.Inject;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by song on 2017/10/16.
 */

public class InternalClusterService extends AbstractLifecycleComponent<ClusterService> implements ClusterService {
    /**
     * Those 3 state listeners are changing infrequently CopyOnWriteArrayList is just fine
     */
    private final Collection<ClusterStateListener> priorityClusterStateListeners=new CopyOnWriteArrayList<>();
    private final Collection<ClusterStateListener> clusterStateListeners=new CopyOnWriteArrayList<>();
    private final Collection<ClusterStateListener> LastClusterStateListeners=new CopyOnWriteArrayList<>();


    private final LocalNodeMasterListeners localNodeMasterListeners;

    private volatile ClusterState clusterState;

    private final ClusterBlocks.Builder initialBlocks;

    private final TaskManager taskManager;


    @Inject
    InternalClusterService(Settings settings, DiscoveryService discoveryService, OperateRouting operateRouting,
                           TransportService transportService, NodeSettingsService nodeSettingsService,
                           ThreadPool threadPool, ClusterName clusterName, DiscoveryNodeService discoveryNodeService,
                           Version version)
    {
        super(settings);
        //will be replaced on doStart
        this.clusterState= ClusterState.builder(clusterName).build();

        localNodeMasterListeners=new LocalNodeMasterListeners(threadPool);

        initialBlocks= ClusterBlocks.builder().addGlobalBlock(discoveryService.getNoMasterBlock());

        taskManager=transportService.getTaskManger();
    }


    @Override
    protected void doStart() {
        System.out.println("InternalClusterService doStart .");

        add(localNodeMasterListeners);
        add(taskManager);

        this.clusterState=ClusterState.builder(clusterState).blocks(initialBlocks).build();

        DiscoveryNode localNode = new DiscoveryNode("node1", "0001", "localhost",
                "nodeAttribute", "version1");
        DiscoveryNodes.Builder nodeBuilder=DiscoveryNodes.builder().put(localNode).localNodeId(localNode.id());

        this.clusterState=ClusterState.builder(clusterState).nodes(nodeBuilder).blocks(initialBlocks).build();

    }


    @Override
    public void add(ClusterStateListener listener){
        clusterStateListeners.add(listener);
    }


    public class LocalNodeMasterListeners  implements ClusterStateListener{

        private final ThreadPool threadPool;

        public LocalNodeMasterListeners(ThreadPool threadPool) {
            this.threadPool=threadPool;
        }

        @Override
        public void clusterChanged(ClusterChangeEvent event) {

        }
    }
}
