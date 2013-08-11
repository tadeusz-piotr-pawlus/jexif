package org.jexif.tags;

import org.jexif.api.JExifException;

public class JExifDatabaseException extends JExifException {
    public JExifDatabaseException() {
    }

    public JExifDatabaseException(String message) {
        super(message);
    }

    public JExifDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public JExifDatabaseException(Throwable cause) {
        super(cause);
    }

    public JExifDatabaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
