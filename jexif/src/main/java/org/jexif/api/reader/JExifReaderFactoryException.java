package org.jexif.api.reader;

public class JExifReaderFactoryException extends JExifReaderException {
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
