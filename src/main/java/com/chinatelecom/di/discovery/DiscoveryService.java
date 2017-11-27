package com.chinatelecom.di.discovery;

import com.chinatelecom.di.cluster.block.ClusterBlock;
import com.chinatelecom.di.cluster.node.DiscoveryNode;
import com.chinatelecom.di.common.component.AbstractLifecycleComponent;

import javax.inject.Inject;

/**
 * Created by song on 2017/11/21.
 */
public class DiscoveryService  extends AbstractLifecycleComponent<DiscoveryService> {

   // private final DiscoverySettings discoverySettings;


    @Override
    protected void doStart() {

    }

    public ClusterBlock getNoMasterBlock(){
        //return discoverySetting.getNoMasterBlock();
        return null;
    }
}
