package org.jexif.reader.oop.header;

import org.jexif.api.reader.JExifReaderFactoryException;

public class JExifHeaderFactoryException extends JExifReaderFactoryException {
    public JExifHeaderFactoryException() {
    }

    public JExifHeaderFactoryException(String message) {
        super(message);
    }

    public JExifHeaderFactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public JExifHeaderFactoryException(Throwable cause) {
        super(cause);
    }

    public JExifHeaderFactoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
