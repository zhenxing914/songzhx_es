package com.chinatelecom.di.common.settings;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by song on 2017/11/23.
 */
public class Settings {


    public String  get(String setting,String defaultValue) {

        switch (setting)
        {
            case "discovery.type":
                return "local";

            case "discovery.zen.no_master_block" :
                return "write";

            case "transport.type":
                return "netty";

            default :
                return "elasticsearch";


        }
    }


    public String get(String name){
        return null;
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
