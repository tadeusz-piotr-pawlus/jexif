package org.jexif.api;

import org.jexif.api.reader.JExifReaderFactory;
import org.jexif.api.writer.JExifWriterFactory;
import org.jexif.reader.DefaultJExifReaderFactory;

public class JExifProviderImpl extends JExifProvider{
    @Override
    public JExifReaderFactory createJExifReaderFactory() throws JExifProviderException {
        return new DefaultJExifReaderFactory();
    }

    @Override
    public JExifWriterFactory createJExifWriterFactory() throws JExifProviderException {
        return null;
    }
}
