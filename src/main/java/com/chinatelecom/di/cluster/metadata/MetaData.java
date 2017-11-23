package com.chinatelecom.di.cluster.metadata;

import com.chinatelecom.di.cluster.Diff;
import com.chinatelecom.di.cluster.Diffable;
import com.chinatelecom.di.common.collect.ImmutableOpenMap;
import com.chinatelecom.di.common.io.stream.StreamInput;
import com.chinatelecom.di.common.io.stream.StreamOutPut;
import com.chinatelecom.di.common.xcontent.ToXContent;
import com.chinatelecom.di.common.xcontent.XcontentParser;

import java.io.IOException;
import java.util.EnumSet;

/**
 * Created by song on 2017/11/7.
 */
public class MetaData implements Diffable<MetaData>{

    public enum XContentContext{

        /*  Custom metadata should be returns as part of API calls   */
        API,

        /* */
        GATEWAY,

        /* */
        SNAPSHOT
    }



    public static final MetaData EMPTY_META_DATA=builder().build();


    public static Builder builder(){
        return new Builder();
    }

    @Override
    public MetaData readFrom(StreamInput in) throws IOException {
        return null;
    }

    @Override
    public void writeTo(StreamOutPut out) throws IOException {

    }

    @Override
    public Diff<MetaData> diff(MetaData previousState) {
        return null;
    }

    @Override
    public Diff<MetaData> readDiffFrom(StreamInput in) throws IOException {
        return null;
    }

    public interface  Custom extends Diffable<Custom>,ToXContent{
        String type();

        Custom fromXContent(XcontentParser parser) throws IOException;

        EnumSet<XContentContext> context();
    }

    public static class Builder{

        private String clusterUUID;
        private long version;

        private final ImmutableOpenMap.Builder<String,IndexMetaData> indices;
        private final ImmutableOpenMap.Builder<String,IndexTemplateMetaData> templates;
        private final ImmutableOpenMap.Builder<String,Custom> customs;


        public Builder(){
            clusterUUID="_na_";
            indices=ImmutableOpenMap.builder();
            templates=ImmutableOpenMap.builder();
            customs=ImmutableOpenMap.builder();

        }

        public MetaData build(){
            return null;
        }


    }
}
