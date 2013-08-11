package org.jexif.api;

public class JExifException extends Exception {
    public JExifException() {
    }

    public JExifException(String message) {
        super(message);
    }

    public JExifException(String message, Throwable cause) {
        super(message, cause);
    }

    public JExifException(Throwable cause) {
        super(cause);
    }

    public JExifException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
