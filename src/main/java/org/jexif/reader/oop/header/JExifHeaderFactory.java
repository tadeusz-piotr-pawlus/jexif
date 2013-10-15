package org.jexif.reader.oop.header;

import org.jexif.api.common.JExifHeader;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class JExifHeaderFactory {

    private final static char LITTLE_ENDIAN = 0x49;
    private final static char BIG_ENDIAN = 0x4D;

    public JExifHeader buildHeader(byte[] data) throws JExifHeaderFactoryException {
        byte first = data[0];
        byte second = data[1];
        ByteOrder byteOrder = getByteOrder(first, second);
        short magicNumber = getMagicNumber(data, byteOrder);
        int offsetOfIFD = getOffsetOfFirstIFD(data, byteOrder);
        JExifHeader header = new JExifHeader(byteOrder, magicNumber, offsetOfIFD);
        if (header.isValid()) {
            throw new JExifHeaderFactoryException("Header is not valid!");
        }
        return header;
    }

    private int getOffsetOfFirstIFD(byte[] data, ByteOrder byteOrder) {
        ByteBuffer ifd = ByteBuffer.wrap(new byte[]{data[4], data[5], data[6], data[7]});
        ifd.order(byteOrder);
        return ifd.getInt();
    }

    private short getMagicNumber(byte[] data, ByteOrder byteOrder) {
        ByteBuffer _42 = ByteBuffer.wrap(new byte[]{data[2], data[3]});
        _42.order(byteOrder);
        return _42.getShort();
    }

    private ByteOrder getByteOrder(byte first, byte second) throws JExifHeaderFactoryException {
        if (first == LITTLE_ENDIAN && second == LITTLE_ENDIAN) {
            return ByteOrder.LITTLE_ENDIAN;
        } else if (first == BIG_ENDIAN && second == BIG_ENDIAN) {
            return ByteOrder.BIG_ENDIAN;
        } else {
            throw new JExifHeaderFactoryException(String.format("Unknown byte order: [%x, %x]", first, second));
        }
    }
}
