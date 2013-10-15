package org.jexif.reader;

import org.jexif.api.common.JExifValue;
import org.jexif.api.common.type.JExifType;
import org.jexif.api.reader.JExifReaderFactoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;

public class JExifValueFactory {
    private final static Logger logger = LoggerFactory.getLogger(JExifValueFactory.class);

    public JExifValue createValue(JExifType type, ByteBuffer bb) throws JExifReaderFactoryException {
        int count = bb.getInt();
        int val = bb.getInt();
        if (type.getBytesNumber() * count > 4) {
            //value is offset pointing to 'real' value
        } else {
            //value is here
        }
        return null;
    }
}
