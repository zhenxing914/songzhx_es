package com.chinatelecom.di.common.settings;

import com.chinatelecom.di.cluster.node.DiscoveryNodes;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by song on 2017/11/23.
 */
public class Settings {
    public String  get(String setting,String defaultValue) {
        return "elasticsearch";
    }


    public static Builder settingBuilder(){
       return new Builder();
    }

    public static class Builder{
        private final Map<String,String > map=new LinkedHashMap<String,String>();


        public Builder put(Settings settings){
            //map.putAll(settings);
            return this;
        }
    }
}
