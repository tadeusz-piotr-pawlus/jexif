package org.jexif.api.type;

public class JExifUndefined extends AbstractJExifType {

    private static final String NAME = "UNDEFINED";
    private static final short ID = 7;
    public static final JExifUndefined instance = new JExifUndefined();

    private JExifUndefined() {
        super(ID, NAME);
    }
}
