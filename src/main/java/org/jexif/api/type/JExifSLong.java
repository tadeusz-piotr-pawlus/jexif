package org.jexif.api.type;

public class JExifSLong extends AbstractJExifType {

    private static final String NAME = "SLONG";
    private static final short ID = 9;
    public static final JExifSLong instance = new JExifSLong();

    private JExifSLong() {
        super(ID, NAME);
    }
}
