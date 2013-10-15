package org.jexif.api.common.type;

public class JExifAscii extends AbstractJExifType {

    private static final String NAME = "ASCII";
    private static final short ID = 2;
    private static final short BYTES_NO = 1;
    public static final JExifAscii instance = new JExifAscii();

    private JExifAscii() {
        super(ID, NAME, BYTES_NO);
    }
}
