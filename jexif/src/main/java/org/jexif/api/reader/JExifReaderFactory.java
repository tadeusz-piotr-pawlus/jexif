package org.jexif.api.reader;

public interface JExifReaderFactory {

    JExifReader createJExifReader() throws JExifReaderFactoryException;
}
