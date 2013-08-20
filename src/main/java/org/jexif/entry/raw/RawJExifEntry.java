package org.jexif.entry.raw;

import java.nio.ByteBuffer;

public class RawJExifEntry {

    private final byte[] tagNumber;
    private final byte[] type;
    private final byte[] count;
    private final byte[] valueOffset;

    private RawJExifEntry() {
        tagNumber = new byte[2];
        type = new byte[2];
        count = new byte[4];
        valueOffset = new byte[4];
    }

    public RawJExifEntry(ByteBuffer img) {
        this();
        img.get(tagNumber);
        img.get(type);
        img.get(count);
        img.get(valueOffset);
    }

    public byte[] getTagNumber() {
        return tagNumber;
    }

    public byte[] getType() {
        return type;
    }

    public byte[] getCount() {
        return count;
    }

    public byte[] getValueOffset() {
        return valueOffset;
    }
}
