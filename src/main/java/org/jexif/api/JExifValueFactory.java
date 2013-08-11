package org.jexif.api;

import org.jexif.api.type.JExifType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JExifValueFactory {

    private final static Logger logger = LoggerFactory.getLogger("org.jexif");

    public <T extends JExifType> JExifValue<T> createValue(byte[] bytes, Class<T> clazz) throws JExifFactoryException {
        return null;
    }
}
