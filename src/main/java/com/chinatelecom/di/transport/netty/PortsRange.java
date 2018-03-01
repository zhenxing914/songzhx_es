package com.chinatelecom.di.transport.netty;

import java.util.StringTokenizer;

/**
 * Created by song on 2018/2/11.
 */
public class PortsRange {
    private final String portRange;

    public PortsRange(String portRange ){
        this.portRange=portRange;
    }


    public boolean iterate(PortCallback callback){
        StringTokenizer st=new StringTokenizer(portRange,",");
        boolean success = false;
        while(st.hasMoreTokens() && ! success){
            String portToken = st.nextToken().trim();
            int index = portToken.indexOf("-");
            if(index== -1 ){
                int portNumber = Integer.parseInt(portToken.trim());
                success = callback.onPortNumber(portNumber);
                if(success){
                    break;
                }
            }
            else {
                int startPort = Integer.parseInt(portToken.substring(0,index).trim());
                int endPort = Integer.parseInt(portToken.substring(index+1).trim());
                if(endPort<startPort){
                    throw new IllegalArgumentException("Start prot ["+startPort+"] must be greater than end port "+ endPort +"]" );
                }
                for(int i = startPort;i <= endPort;i++){
                    success =callback.onPortNumber(i);
                    if(success){
                        break;
                    }
                }
            }
        }
        return success;
    }


    public static interface PortCallback{

        boolean onPortNumber(int portNumber);
    }
}
