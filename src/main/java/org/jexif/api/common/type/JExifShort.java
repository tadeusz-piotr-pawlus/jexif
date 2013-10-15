package org.jexif.api.common.type;

public class JExifShort extends AbstractJExifType {

    private static final String NAME = "SHORT";
    private static final short ID = 3;
    private static final short BYTES_NO = 2;
    public static final JExifShort instance = new JExifShort();

    private JExifShort() {
        super(ID, NAME, BYTES_NO);
    }
}
