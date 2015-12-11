package org.jexif.reader;

import org.jexif.api.reader.JExifReaderFactoryException;
import org.jexif.tags.database.spi.JExifTagNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JExifTagNumberFactory {
    private final static Logger logger = LoggerFactory.getLogger(JExifTagNumberFactory.class);

    public JExifTagNumber createNumber(short no) throws JExifReaderFactoryException {
        return new JExifTagNumber(no);
    }
}
