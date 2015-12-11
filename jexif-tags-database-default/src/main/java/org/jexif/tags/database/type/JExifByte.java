package org.jexif.tags.database.type;

import java.nio.ByteOrder;

public class JExifByte extends AbstractJExifType {

    private static final String NAME = "BYTE";
    private static final short ID = 1;
    private static final short BYTES_NO = 1;
    public static final JExifByte instance = new JExifByte();

    private JExifByte() {
        super(ID, NAME, BYTES_NO);
    }

    @Override
    public String convert(byte[] value, ByteOrder bo) {
        return "byte value should be here";
    }
}
