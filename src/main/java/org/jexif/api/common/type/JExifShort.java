package org.jexif.api.common.type;

import java.nio.ByteOrder;

public class JExifShort extends AbstractJExifType {

    private static final String NAME = "SHORT";
    private static final short ID = 3;
    private static final short BYTES_NO = 2;
    public static final JExifShort instance = new JExifShort();

    private JExifShort() {
        super(ID, NAME, BYTES_NO);
    }

    @Override
    public String convert(byte[] value, ByteOrder bo) {
        byte hi = value[0];
        byte lo = value[1];
        if(ByteOrder.BIG_ENDIAN == bo){
            hi = value[0];
            lo = value[1];
        }else{
            hi = value[1];
            lo = value[0];
        }
        short val=(short)( ((hi&0xFF)<<8) | (lo&0xFF) );
        return "" + val;
    }
}
