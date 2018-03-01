package com.chinatelecom.di.transport.netty;

import org.jboss.netty.channel.*;

/**
 * Created by song on 2018/2/11.
 */
public class MessageChannelHander extends SimpleChannelUpstreamHandler {
    protected  final NettyTransport nettyTransport;
    protected final String profileName;


    public MessageChannelHander(NettyTransport nettyTransport, String profileName) {
        this.nettyTransport=nettyTransport;
        this.profileName=profileName;
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        super.messageReceived(ctx, e);
    }

    @Override
    public void writeComplete(ChannelHandlerContext ctx, WriteCompletionEvent e) throws Exception {
        super.writeComplete(ctx, e);
    }
}
