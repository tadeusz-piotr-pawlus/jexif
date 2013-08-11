package org.jexif.tags.converter;

import org.jexif.tags.database.api.JExifTagsDatabaseException;

public class JExifTagsRawTagConverterException extends JExifTagsDatabaseException {
    public JExifTagsRawTagConverterException() {
    }

    public JExifTagsRawTagConverterException(String message) {
        super(message);
    }

    public JExifTagsRawTagConverterException(String message, Throwable cause) {
        super(message, cause);
    }

    public JExifTagsRawTagConverterException(Throwable cause) {
        super(cause);
    }

    public JExifTagsRawTagConverterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
