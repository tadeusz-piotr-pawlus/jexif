package org.jexif.header.raw;

import org.jexif.entry.raw.RawJExifEntry;

import java.nio.ByteBuffer;

public class RawImageFileDirectory {

    private short numberOfInteroperability;
    private RawJExifEntry[] data;
    private short nextIFDOffset;

    public RawImageFileDirectory(ByteBuffer img) {
        numberOfInteroperability = img.getShort();
        data = new RawJExifEntry[numberOfInteroperability];
        for (int i = 0; i < numberOfInteroperability; i++) {
            data[i] = new RawJExifEntry(img);
        }
        nextIFDOffset = img.getShort();
    }

    public short getNumberOfInteroperability() {
        return numberOfInteroperability;
    }

    public RawJExifEntry[] getData() {
        return data;
    }

    public short getNextIFDOffset() {
        return nextIFDOffset;
    }
}
