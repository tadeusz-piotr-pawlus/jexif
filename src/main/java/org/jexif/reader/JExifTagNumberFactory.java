package org.jexif.reader;

import org.jexif.api.common.JExifTagNumber;
import org.jexif.api.reader.JExifReaderFactoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;

public class JExifTagNumberFactory {
    private final static Logger logger = LoggerFactory.getLogger(JExifTagNumberFactory.class);

    public JExifTagNumber createNumber(ByteBuffer bb) throws JExifReaderFactoryException {
        short no = bb.getShort();
        return new JExifTagNumber(no, 10);
    }
}
