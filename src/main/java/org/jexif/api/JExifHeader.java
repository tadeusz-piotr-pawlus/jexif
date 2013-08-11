package org.jexif.api;

import java.io.Serializable;
import java.nio.ByteOrder;

/**
 * Class represents Header read from file containing Exif data.
 *
 * @author Tadeusz Piotr Pawlus
 */
public class JExifHeader implements Serializable {
    private final ByteOrder byteOrder;
    private final short magicNumber;
    private final int offsetOfIFD;

    public JExifHeader(ByteOrder byteOrder, short magicNumber, int offsetOfIFD) {
        this.byteOrder = byteOrder;
        this.magicNumber = magicNumber;
        this.offsetOfIFD = offsetOfIFD;
    }

    public ByteOrder getByteOrder() {
        return byteOrder;
    }

    public short getMagicNumber() {
        return magicNumber;
    }

    public int getOffsetOfIFD() {
        return offsetOfIFD;
    }
}
