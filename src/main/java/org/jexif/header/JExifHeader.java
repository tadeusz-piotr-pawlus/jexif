package org.jexif.header;

import org.jexif.header.raw.RawJExifHeader;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class JExifHeader {

    private ByteOrder byteOrder;
    private boolean valid;
    private int offsetOfIFD;

    private final static char LITTLE_ENDIAN = 0x49;
    private final static char BIG_ENDIAN = 0x4D;

    public JExifHeader(RawJExifHeader header) throws JExifHeaderException {
        byte first = header.getByteOrder()[0];
        byte second = header.getByteOrder()[1];
        if (first == LITTLE_ENDIAN && second == LITTLE_ENDIAN) {
            byteOrder = ByteOrder.LITTLE_ENDIAN;
        } else if (first == BIG_ENDIAN && second == BIG_ENDIAN) {
            byteOrder = ByteOrder.BIG_ENDIAN;
        } else {
            throw new JExifHeaderException(String.format("Unknown byte order: [%x, %x]", first, second));
        }

        ByteBuffer _42 = ByteBuffer.wrap(header.get_42());
        _42.order(getByteOrder());
        valid = 42 == _42.getShort();

        ByteBuffer ifd = ByteBuffer.wrap(header.getOffsetOfIFD());
        ifd.order(getByteOrder());
        offsetOfIFD = ifd.getInt();
    }

    public ByteOrder getByteOrder() {
        return byteOrder;
    }

    public boolean isValid() {
        return valid;
    }

    public int getOffsetOfIFD() {
        return offsetOfIFD;
    }
}
