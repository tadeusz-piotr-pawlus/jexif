package org.jexif.entry.converter;

import org.jexif.api.common.type.JExifType;
import org.jexif.api.common.type.JExifTypeFactory;
import org.jexif.api.common.type.JExifTypeFactoryException;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class JExifRawTypeConverter extends AbstractConverter {

    private final JExifTypeFactory exifTypeFactory;

    public JExifRawTypeConverter(ByteOrder bo, JExifTypeFactory exifTypeFactory) {
        super(bo);
        this.exifTypeFactory = exifTypeFactory;
    }

    public JExifType convert(byte[] type) throws JExifConverterException {
        try {
            return exifTypeFactory.createById(ByteBuffer.wrap(type).order(getByteOrder()).getShort());
        } catch (JExifTypeFactoryException ex) {
            throw new JExifConverterException(ex);
        }
    }
}
