package org.jexif.api.type;

public class JExifSRational extends AbstractJExifType {

    private static final String NAME = "SRATIONAL";
    private static final short ID = 10;
    public static final JExifSRational instance = new JExifSRational();

    private JExifSRational() {
        super(ID, NAME);
    }
}
