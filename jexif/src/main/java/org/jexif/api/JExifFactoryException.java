package org.jexif.api;

public class JExifFactoryException extends JExifException {
    public JExifFactoryException() {
    }

    public JExifFactoryException(String message) {
        super(message);
    }

    public JExifFactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public JExifFactoryException(Throwable cause) {
        super(cause);
    }

    public JExifFactoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
