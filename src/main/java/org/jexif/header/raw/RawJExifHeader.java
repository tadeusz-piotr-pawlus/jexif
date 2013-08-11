package org.jexif.header.raw;

public class RawJExifHeader {

    private byte[] byteOrder;
    private byte[] _42;
    private byte[] offsetOfIFD;

    private RawJExifHeader() {
        byteOrder = new byte[2];
        _42 = new byte[2];
        offsetOfIFD = new byte[4];
    }

    public RawJExifHeader(byte[] bytes) {
        this();
        byteOrder = new byte[]{bytes[0], bytes[1]};
        _42 = new byte[]{bytes[2], bytes[3]};
        offsetOfIFD = new byte[]{bytes[4], bytes[5], bytes[6], bytes[7]};
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
