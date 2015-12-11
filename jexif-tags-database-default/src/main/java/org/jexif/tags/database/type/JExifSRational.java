package org.jexif.tags.database.type;

import java.nio.ByteOrder;

public class JExifSRational extends AbstractJExifType {

    private static final String NAME = "SRATIONAL";
    private static final short ID = 10;
    private static final short BYTES_NO = 8;
    public static final JExifSRational instance = new JExifSRational();

    private JExifSRational() {
        super(ID, NAME, BYTES_NO);
    }

    @Override
    public String convert(byte[] value, ByteOrder bo) {
        return "SRational value should be here";
    }
}
