package com.chinatelecom.di.common.io.stream;

import com.chinatelecom.di.Version;
import org.apache.lucene.util.CharsRefBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by song on 2017/11/7.
 */
public abstract class StreamInput  extends InputStream{



    private Version version=Version.CURRENT;

    private  final CharsRefBuilder spare =new CharsRefBuilder();


    public String readString() throws IOException {
        final int charCount=readVint();
        spare.clear();
        spare.grow(charCount);
        int c;
        while(spare.length()<charCount){
            c=readByte() & 0xff;
            switch (c>>4){
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    spare.append((char)c);
                    break;
                case 12:
                case 13:
                    spare.append((char)((c& 0x1F)<<6 |readByte() & 0x3F));
                    break;
                case 14:
                    spare.append((char)((c& 0x1F)<<12 |(readByte() & 0x3F)<<6  | (readByte() & 0x3F) <<0 ));
            }
        }
        return  spare.toString();
    }



    public  int readVint() throws IOException{
        byte b=readByte();
        int i=b & 0x7F;
        if((b&0x80)==00){
            return i;
        }
        b=readByte();
        i|=(b&0x7f)<<7;
        if((b&0x80)==0){
            return i;
        }
        b=readByte();
        i|=(b&0x7f)<<14;
        if((b&0x80)==0){
            return i;
        }
        b=readByte();
        i|=(b&0x7f)<<21;
        if((b&0x80)==0){
            return i;
        }

        b=readByte();
        assert (b & 0x80) ==0;
        return i | ((b  & 0x7F)<<28);
    }

    /**
     *  Reads and returns a single byte
     *
     */
    protected abstract byte readByte();
}
