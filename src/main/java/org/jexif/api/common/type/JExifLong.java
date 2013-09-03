package org.jexif.api.common.type;

public class JExifLong extends AbstractJExifType {

    private static final String NAME = "LONG";
    private static final short ID = 4;
    public static final JExifLong instance = new JExifLong();

    private JExifLong() {
        super(ID, NAME);
    }
}
