package org.jexif.entry.converter;

import org.jexif.api.JExifTagNumber;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class JExifRawTagNumberConverter extends AbstractConverter {


    public JExifRawTagNumberConverter(ByteOrder bo) {
        super(bo);
    }

    public JExifTagNumber convert(byte[] tagNumber) {
        int no = ByteBuffer.wrap(tagNumber).order(getByteOrder()).getShort();
        return new JExifTagNumber((short) no, 10);
    }
}
