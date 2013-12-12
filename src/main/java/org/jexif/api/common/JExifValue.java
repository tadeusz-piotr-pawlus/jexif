package org.jexif.api.common;

public class JExifValue {

    private final JExifTag tag;
    private final byte[] value;

    public JExifValue(JExifTag tag, byte[] value) {
        this.tag = tag;
        this.value = value;
    }

    public byte[] getValue() {
        return value;
    }

    public JExifTag getTag() {
        return tag;
    }
}
