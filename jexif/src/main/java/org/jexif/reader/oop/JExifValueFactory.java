package org.jexif.reader.oop;

import org.jexif.api.JExifFactoryException;
import org.jexif.api.common.JExifValue;
import org.jexif.tags.database.spi.JExifType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JExifValueFactory {

    private final static Logger logger = LoggerFactory.getLogger(JExifValueFactory.class);

    public JExifValue createValue(byte[] bytes, JExifType type) throws JExifFactoryException {
        return null;
    }
}
