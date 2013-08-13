package org.jexif.header.raw;

import java.nio.ByteBuffer;

public class RawExifEntry {

    public static final int EXIF_ROW_LENGTH = 12;
    private final byte[] data;

    private RawExifEntry() {
        data = new byte[EXIF_ROW_LENGTH];
    }

    public RawExifEntry(ByteBuffer img) {
        this();
        img.get(data);
    }

    public byte[] getData() {
        return data;
    }
}
