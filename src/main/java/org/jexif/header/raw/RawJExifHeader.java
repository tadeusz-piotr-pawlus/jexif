package org.jexif.header.raw;

import java.nio.ByteBuffer;

public class RawJExifHeader {

    private byte[] byteOrder;
    private byte[] _42;
    private byte[] offsetOfIFD;

    private RawJExifHeader() {
        byteOrder = new byte[2];
        _42 = new byte[2];
        offsetOfIFD = new byte[4];
    }

    public RawJExifHeader(ByteBuffer bytes) {
        this();
        bytes.get(byteOrder);
        bytes.get(_42);
        bytes.get(offsetOfIFD);
    }

    public byte[] getByteOrder() {
        return byteOrder;
    }

    public byte[] get_42() {
        return _42;
    }

    public byte[] getOffsetOfIFD() {
        return offsetOfIFD;
    }
}
