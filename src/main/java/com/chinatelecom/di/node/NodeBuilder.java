package com.chinatelecom.di.node;

import com.chinatelecom.di.Version;

/**
 * Created by song on 2017/10/16.
 */
public class NodeBuilder {

    public Node build(){
        Version version=new Version(234,true, org.apache.lucene.util.Version.LUCENE_5_5_5);
        return new Node(version);
    }

}
