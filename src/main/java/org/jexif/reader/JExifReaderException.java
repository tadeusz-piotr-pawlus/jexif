package org.jexif.reader;

import org.jexif.api.JExifException;

public class JExifReaderException extends JExifException {
    public JExifReaderException() {
    }

    public JExifReaderException(String message) {
        super(message);
    }

    public JExifReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public JExifReaderException(Throwable cause) {
        super(cause);
    }

    public JExifReaderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
