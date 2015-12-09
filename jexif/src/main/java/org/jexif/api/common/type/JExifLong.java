package org.jexif.api.common.type;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class JExifLong extends AbstractJExifType {

    private static final String NAME = "LONG";
    private static final short ID = 4;
    private static final short BYTES_NO = 4;
    public static final JExifLong instance = new JExifLong();

    private JExifLong() {
        super(ID, NAME, BYTES_NO);
    }

    @Override
    public String convert(byte[] value, ByteOrder bo) {
        ByteBuffer data = ByteBuffer.wrap(value);
        data.order(bo);
        return "" + data.getInt();
    }
}
