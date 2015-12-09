package org.jexif.reader.oop.buffer.api;

import org.jexif.api.reader.JExifReaderException;

public class BufferProviderException extends JExifReaderException {
    public BufferProviderException() {
    }

    public BufferProviderException(String message) {
        super(message);
    }

    public BufferProviderException(String message, Throwable cause) {
        super(message, cause);
    }

    public BufferProviderException(Throwable cause) {
        super(cause);
    }

    public BufferProviderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
