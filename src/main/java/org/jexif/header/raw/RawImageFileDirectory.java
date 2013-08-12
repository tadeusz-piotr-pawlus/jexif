package org.jexif.header.raw;

import java.nio.ByteBuffer;

public class RawImageFileDirectory {

    public static final int EXIF_ROW_LENGTH = 16;
    private short numberOfInteroperability;
    private byte[] data;
    private short nextIFDOffset;

    public RawImageFileDirectory(ByteBuffer img) {
        numberOfInteroperability = img.getShort();
        data = new byte[EXIF_ROW_LENGTH * numberOfInteroperability];
        img.get(data);
        nextIFDOffset = img.getShort();
    }

    public short getNumberOfInteroperability() {
        return numberOfInteroperability;
    }

    public byte[] getData() {
        return data;
    }

    public short getNextIFDOffset() {
        return nextIFDOffset;
    }
}
