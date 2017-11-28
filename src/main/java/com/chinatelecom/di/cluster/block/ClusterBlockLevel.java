package com.chinatelecom.di.cluster.block;

import java.util.EnumSet;

/**
 * Created by song on 2017/11/28.
 */
public enum ClusterBlockLevel {

    READ(0),
    WRITE(1),
    METADATA_READ(2),
    METADATA_WRITE(3);


    public static final EnumSet<ClusterBlockLevel> ALL=EnumSet.of(READ,WRITE,METADATA_READ,METADATA_WRITE);

    private  final  int id;

    ClusterBlockLevel(int id){
        this.id=id;
    }

}
