package org.jexif.entry.converter;

import org.jexif.reader.JExifReaderException;

public class JExifConverterException extends JExifReaderException {
    public JExifConverterException() {
    }

    public JExifConverterException(String message) {
        super(message);
    }

    public JExifConverterException(String message, Throwable cause) {
        super(message, cause);
    }

    public JExifConverterException(Throwable cause) {
        super(cause);
    }

    public JExifConverterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
