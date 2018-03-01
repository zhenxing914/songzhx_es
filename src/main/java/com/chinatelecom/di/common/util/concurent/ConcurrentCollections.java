package com.chinatelecom.di.common.util.concurent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by song on 2018/2/12.
 */
public class ConcurrentCollections {

    public static <K,V>ConcurrentMap<K,V> newConcurrentMap(){
        return new ConcurrentHashMap<>();
    }
}
