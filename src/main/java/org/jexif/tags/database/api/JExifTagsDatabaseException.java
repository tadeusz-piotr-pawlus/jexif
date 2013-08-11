package org.jexif.tags.database.api;

import org.jexif.api.JExifException;

public class JExifTagsDatabaseException extends JExifException {
    public JExifTagsDatabaseException() {
    }

    public JExifTagsDatabaseException(String message) {
        super(message);
    }

    public JExifTagsDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public JExifTagsDatabaseException(Throwable cause) {
        super(cause);
    }

    public JExifTagsDatabaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
