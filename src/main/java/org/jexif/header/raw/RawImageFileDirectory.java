package org.jexif.header.raw;

import java.nio.ByteBuffer;

public class RawImageFileDirectory {

    private short numberOfInteroperability;
    private RawExifEntry[] data;
    private short nextIFDOffset;

    public RawImageFileDirectory(ByteBuffer img) {
        numberOfInteroperability = img.getShort();
        data = new RawExifEntry[numberOfInteroperability];
        for (int i = 0; i < numberOfInteroperability; i++) {
            data[i] = new RawExifEntry(img);
        }
        nextIFDOffset = img.getShort();
    }

    public short getNumberOfInteroperability() {
        return numberOfInteroperability;
    }

    public RawExifEntry[] getData() {
        return data;
    }

    public short getNextIFDOffset() {
        return nextIFDOffset;
    }
}
