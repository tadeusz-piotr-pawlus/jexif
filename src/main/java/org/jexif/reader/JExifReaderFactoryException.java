package org.jexif.reader;

import org.jexif.api.JExifFactoryException;

public class JExifReaderFactoryException extends JExifFactoryException {
    public JExifReaderFactoryException() {
    }

    public JExifReaderFactoryException(String message) {
        super(message);
    }

    public JExifReaderFactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public JExifReaderFactoryException(Throwable cause) {
        super(cause);
    }

    public JExifReaderFactoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
