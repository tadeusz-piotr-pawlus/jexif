package org.jexif.api.reader;

public interface JExifReaderFactory {

    public JExifReader createJExifReader() throws JExifReaderFactoryException;
}
