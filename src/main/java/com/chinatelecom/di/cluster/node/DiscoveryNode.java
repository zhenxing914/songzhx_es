package com.chinatelecom.di.cluster.node;

/**
 * Created by song on 2017/10/18.
 */
public class DiscoveryNode {

    private String nodeId;
    private String nodeName;


    /**
     *
     * @param nodeName      the node name
     * @param nodeId        the nodes unique id
     * @param address       the nodes transport address
     * @param attributes    node attributes
     * @param version       the version of the node
     */
    public DiscoveryNode(String nodeName, String nodeId, String address, String attributes, String version) {
        this.nodeId=nodeId.intern();
        this.nodeName=nodeName.intern();
    }


    public String  id()
    {
        return nodeId;
    }
}
