package org.jexif.reader;

import org.jexif.api.common.JExifValue;
import org.jexif.api.reader.JExifReaderFactoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;

public class JExifValueFactory {
    private final static Logger logger = LoggerFactory.getLogger(JExifValueFactory.class);

    public JExifValue createValue(ByteBuffer bb) throws JExifReaderFactoryException {
        short no = bb.getShort();
        return null;
    }
}
