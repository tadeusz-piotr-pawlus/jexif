package org.jexif.api;

import org.jexif.api.reader.JExifReaderFactory;
import org.jexif.api.writer.JExifWriterFactory;

public interface JExifProvider {

    JExifReaderFactory createJExifReaderFactory() throws JExifProviderException;

    JExifWriterFactory createJExifWriterFactory() throws JExifProviderException;
}
