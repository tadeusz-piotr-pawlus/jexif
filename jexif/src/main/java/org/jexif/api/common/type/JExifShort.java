package org.jexif.api.common.type;

import java.nio.ByteBuffer;
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
        ByteBuffer data = ByteBuffer.wrap(value);
        data.order(bo);
        return "" + data.getShort();
    }
}
