package org.jexif.api.common.type;

public class JExifUndefined extends AbstractJExifType {

    private static final String NAME = "UNDEFINED";
    private static final short ID = 7;
    private static final short BYTES_NO = 1;
    public static final JExifUndefined instance = new JExifUndefined();

    private JExifUndefined() {
        super(ID, NAME, BYTES_NO);
    }
}
