package org.jexif.api;

public class JExifProviderException extends JExifFactoryException {
    public JExifProviderException() {
    }

    public JExifProviderException(String message) {
        super(message);
    }

    public JExifProviderException(String message, Throwable cause) {
        super(message, cause);
    }

    public JExifProviderException(Throwable cause) {
        super(cause);
    }

    public JExifProviderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
