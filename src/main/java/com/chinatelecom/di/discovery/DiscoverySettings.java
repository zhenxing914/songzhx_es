package com.chinatelecom.di.discovery;

import com.chinatelecom.di.cluster.block.ClusterBlock;
import com.chinatelecom.di.cluster.block.ClusterBlockLevel;
import com.chinatelecom.di.common.component.AbstractComponent;
import com.chinatelecom.di.common.settings.Settings;
import com.chinatelecom.di.node.settings.NodeSettingsService;
import com.chinatelecom.di.rest.RestStatus;

import javax.inject.Inject;

/**
 * Created by song on 2017/11/28.
 */
public class DiscoverySettings extends AbstractComponent {



    private volatile ClusterBlock noMasterBlock;

    private static  final String NO_MASTER_BLOCK="discovery.zen.no_master_block";

    private static final String DEFAULT_NO_MASTER_BLOCK="write";

    private static final int NO_MASTER_BLOCK_ID = 2 ;

    public final static ClusterBlock NO_MASTER_BLOCK_ALL=new ClusterBlock(NO_MASTER_BLOCK_ID,"no master",true,true,
            RestStatus.SERVICE_UNAVAILABLE, ClusterBlockLevel.ALL);

    @Inject
    public DiscoverySettings(Settings settings, NodeSettingsService nodeSettingsService){

        noMasterBlock=parseNoMasterBlock(settings.get(NO_MASTER_BLOCK,DEFAULT_NO_MASTER_BLOCK));
    }

    private ClusterBlock parseNoMasterBlock(String value) {
        switch(value){
            case "all" :
                return NO_MASTER_BLOCK_ALL;
//            case "write" :
//                return NO_MASTER_BLOCK_WRITES;
            default:
                throw new IllegalArgumentException("invaild master block ["+ value+"]");
        }
    }


    public ClusterBlock getNoMasterBlock() {

        return noMasterBlock;
    }
}
