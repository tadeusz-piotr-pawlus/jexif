package org.jexif.tags.converter;

import org.jexif.tags.JExifDatabaseException;

public class JExifRawTagConverterException extends JExifDatabaseException {
    public JExifRawTagConverterException() {
    }

    public JExifRawTagConverterException(String message) {
        super(message);
    }

    public JExifRawTagConverterException(String message, Throwable cause) {
        super(message, cause);
    }

    public JExifRawTagConverterException(Throwable cause) {
        super(cause);
    }

    public JExifRawTagConverterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
