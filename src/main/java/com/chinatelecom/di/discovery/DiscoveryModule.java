package com.chinatelecom.di.discovery;

import com.chinatelecom.di.common.settings.Settings;
import com.chinatelecom.di.discovery.local.LocalDiscovery;
import com.chinatelecom.di.discovery.zen.ZenDiscovery;
import com.google.inject.AbstractModule;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by song on 2017/10/16.
 */
public class DiscoveryModule extends  AbstractModule {

    public static final  String  DISCOVERY_TYPE_KEY="discovery.type";


    private final Settings settings;
    private final Map<String,Class<? extends Discovery>> discoveryTypes= new HashMap<String,Class<? extends Discovery>>();


    public DiscoveryModule(Settings settings){

        this.settings=settings;

        addDiscoveryType("local",LocalDiscovery.class);
        addDiscoveryType("zen",ZenDiscovery.class);
//        addElectMasterService("zen",ElectMasterService.class);
//        //always add the unicast hosts ,or things get angry!
//        addZenPing(UnicastZenPing.class);

    }

    public void configure() {



        // String defaultType= DiscoveryNode.localNode(settings) ? "local" :"zen";
        String defaultType="local";
        String discoveryType=settings.get(DISCOVERY_TYPE_KEY,defaultType);

        Class<? extends Discovery> discoveryClass=discoveryTypes.get(discoveryType);


        bind(Discovery.class).to(discoveryClass).asEagerSingleton();

        bind(DiscoveryService.class).asEagerSingleton();



    }


    /**
     *  Add a custom Discovery Type
     */

    public void addDiscoveryType(String type,Class<? extends Discovery> clazz){

        if(discoveryTypes.containsKey(type)){
            throw new IllegalArgumentException("discovery type ["+type +"] is already registered");
        }
        discoveryTypes.put(type,clazz);
    }
}
