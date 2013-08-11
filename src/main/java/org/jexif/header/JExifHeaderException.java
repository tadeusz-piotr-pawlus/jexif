package org.jexif.header;

import org.jexif.api.JExifException;

public class JExifHeaderException extends JExifException {
    public JExifHeaderException() {
    }

    public JExifHeaderException(String message) {
        super(message);
    }

    public JExifHeaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public JExifHeaderException(Throwable cause) {
        super(cause);
    }

    public JExifHeaderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
