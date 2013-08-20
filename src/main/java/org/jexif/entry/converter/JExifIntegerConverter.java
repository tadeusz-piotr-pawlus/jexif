package org.jexif.entry.converter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class JExifIntegerConverter extends AbstractConverter {

    public JExifIntegerConverter(ByteOrder bo) {
        super(bo);
    }

    public short convert(byte[] count) {
        return ByteBuffer.wrap(count).order(getByteOrder()).getShort();
    }
}
