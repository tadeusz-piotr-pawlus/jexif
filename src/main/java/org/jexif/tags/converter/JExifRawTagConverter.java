package org.jexif.tags.converter;

import org.jexif.api.*;
import org.jexif.api.type.JExifType;
import org.jexif.api.type.JExifTypeFactory;
import org.jexif.tags.raw.JExifRawTag;

public class JExifRawTagConverter {

    private final JExifTypeFactory typeFactory;
    private final JExifValueFactory valueFactory;
    private final int radix;

    public JExifRawTagConverter(int radix) {
        this.typeFactory = new JExifTypeFactory();
        this.valueFactory = new JExifValueFactory();
        this.radix = radix;
    }

    public JExifTag convert(JExifRawTag rawTag) throws JExifTagsRawTagConverterException {
        try {
            JExifTagNumber tagNumber = new JExifTagNumber(rawTag.getNumber(), getRadix());
            String name = rawTag.getName();
            JExifType type = this.typeFactory.createByName(rawTag.getType());
            int count = rawTag.getCount();
            JExifValue defaultValue = this.valueFactory.createValue(rawTag.getDefaultValue(), type);
            return new JExifTag(tagNumber, name, type, count, defaultValue);
        } catch (JExifFactoryException e) {
            throw new JExifTagsRawTagConverterException(e);
        }
    }

    public int getRadix() {
        return radix;
    }
}
