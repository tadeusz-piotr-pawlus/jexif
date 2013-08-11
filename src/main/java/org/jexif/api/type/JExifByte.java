package org.jexif.api.type;

public class JExifByte extends AbstractJExifType {

    private static final String NAME = "BYTE";
    private static final short ID = 1;
    public static final JExifByte instance = new JExifByte();

    private JExifByte() {
        super(ID, NAME);
    }
}
