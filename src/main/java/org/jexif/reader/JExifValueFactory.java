package org.jexif.reader;

import org.jexif.api.common.JExifTag;
import org.jexif.api.common.JExifValue;
import org.jexif.api.reader.JExifReaderFactoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;

public class JExifValueFactory {
    private final static Logger logger = LoggerFactory.getLogger(JExifValueFactory.class);

    public JExifValue createValue(JExifTag tag, ByteBuffer bb) throws JExifReaderFactoryException {
        byte[] value = getBytes(tag, bb);
        return new JExifValue(tag,  tag.getType().convert(value, bb.order()));
    }

    private byte[] getBytes(JExifTag tag, ByteBuffer bb) {
        byte[] value;
        int count = bb.getInt();
        int bytesNo = tag.getType().getBytesNumber() * count;
        if (bytesNo > 4) {
            //value is offset pointing to 'real' value
            int offset = bb.getInt();
            int mementoPosition = bb.position();
            bb.position(offset);
            value = new byte[bytesNo];
            bb.get(value);
            bb.position(mementoPosition);
        } else {
            //value is here
            value = new byte[4];
            bb.get(value);
        }
        return value;
    }
}
