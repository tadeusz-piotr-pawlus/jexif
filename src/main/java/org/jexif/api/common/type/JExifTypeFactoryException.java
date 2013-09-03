package org.jexif.api.common.type;

import org.jexif.api.JExifFactoryException;

public class JExifTypeFactoryException extends JExifFactoryException {
    public JExifTypeFactoryException() {
    }

    public JExifTypeFactoryException(String message) {
        super(message);
    }

    public JExifTypeFactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public JExifTypeFactoryException(Throwable cause) {
        super(cause);
    }

    public JExifTypeFactoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
