package org.jexif.api.common.type;

import java.nio.ByteOrder;

public class JExifRational extends AbstractJExifType {

    private static final String NAME = "RATIONAL";
    private static final short ID = 5;
    private static final short BYTES_NO = 8;
    public static final JExifRational instance = new JExifRational();

    private JExifRational() {
        super(ID, NAME, BYTES_NO);
    }

    @Override
    public String convert(byte[] value, ByteOrder bo) {
        return new String("Rational value should be here");
    }
}
