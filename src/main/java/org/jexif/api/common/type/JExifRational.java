package org.jexif.api.common.type;

public class JExifRational extends AbstractJExifType {

    private static final String NAME = "RATIONAL";
    private static final short ID = 5;
    public static final JExifRational instance = new JExifRational();

    private JExifRational() {
        super(ID, NAME);
    }
}
