package com.chinatelecom.di.discovery;

import com.chinatelecom.di.cluster.block.ClusterBlock;
import com.chinatelecom.di.common.component.AbstractLifecycleComponent;
import com.chinatelecom.di.common.settings.Settings;

import javax.inject.Inject;


/**
 * Created by song on 2017/11/21.
 */


public class DiscoveryService  extends AbstractLifecycleComponent<DiscoveryService> {

    private final DiscoverySettings discoverySettings;
    private final Discovery discovery;


    @Inject
    public DiscoveryService(Settings settings,DiscoverySettings discoverySettings,Discovery discovery){
        super(settings);

        this.discoverySettings=discoverySettings;
        this.discovery=discovery;
    }

    @Override
    protected void doStart() {

    }

    public ClusterBlock getNoMasterBlock(){

        return discoverySettings.getNoMasterBlock();

    }
}
