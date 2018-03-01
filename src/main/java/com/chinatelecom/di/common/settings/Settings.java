package com.chinatelecom.di.common.settings;


import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by song on 2017/11/23.
 */
public class Settings {


    public Settings(Map<String, String> stringStringMap) {

    }

    public Settings(){

    }

    public String  get(String setting, String defaultValue) {

        switch (setting)
        {
            case "discovery.type":
                return "local";

            case "discovery.zen.no_master_block" :
                return "write";

            case "transport.type":
                return "netty";

            default :
                return defaultValue;


        }
    }


    public String get(String name){
        return null;
    }

    public static Builder settingsBuilder(){
       return new Builder();
    }

    public int getAsInt(String settings, int defaultValue) {

        return defaultValue;

    }

    public String[] getAsArray(String bind_host, String[] defaultArray) {
        return   defaultArray;
    }


    public static class Builder{

        private final Map<String,String > map=new LinkedHashMap<String,String>();


        public Builder put(Settings settings){
            //map.putAll(settings);
            return this;
        }

        public Settings build(){
            return new Settings(Collections.unmodifiableMap(map));
        }

    }
}
