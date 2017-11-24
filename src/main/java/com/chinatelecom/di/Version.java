package com.chinatelecom.di;

import com.google.inject.AbstractModule;

/**
 * Created by song on 2017/10/16.
 */
public class Version {


    public static final int V_2_3_4_ID=2030499;

    public static final Version V_2_3_4=new Version(V_2_3_4_ID,true,org.apache.lucene.util.Version.LUCENE_5_5_5);

    public static final Version CURRENT = V_2_3_4;


    public  final int id;

    public  final byte major;

    public final byte minor;

    public final byte revision;

    public final byte build;

    public final Boolean snapshot;

    public final org.apache.lucene.util.Version luceneVersion;


    public Version(int id, boolean snapshot, org.apache.lucene.util.Version luceneVersion) {

        this.id=id;
        this.major=(byte)((id/1000000)%100);
        this.minor=(byte)((id/10000)%100);
        this.revision=(byte)((id/100)%100);
        this.build=(byte)(id%100);
        this.snapshot=snapshot;
        this.luceneVersion=luceneVersion;
    }

public static  class Module extends AbstractModule{

    public  final  Version version;

    public Module(Version version){
        this.version=version;
    }

    @Override
    protected void configure() {
        bind(Version.class).toInstance(version);
    }
}

}
