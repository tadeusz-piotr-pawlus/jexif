package org.jexif.tags.converter;

import org.jexif.api.*;
import org.jexif.api.type.JExifType;
import org.jexif.api.type.JExifTypeFactory;
import org.jexif.tags.raw.JExifRawTag;

public class DefaultJExifRawTagConverter implements JExifRawTagConverter {

    private final JExifTypeFactory typeFactory;
    private final JExifValueFactory valueFactory;
    private final int radix;

    public DefaultJExifRawTagConverter(int radix) {
        this.typeFactory = new JExifTypeFactory();
        this.valueFactory = new JExifValueFactory();
        this.radix = radix;
    }

    @Override
    public JExifTag<? extends JExifType> convert(JExifRawTag rawTag) throws JExifRawTagConverterException {
        try {
            JExifType type = this.typeFactory.createByName(rawTag.getType());
            JExifTagNumber tagNumber = new JExifTagNumber(rawTag.getNumber(), getRadix());
            JExifValue<? extends JExifType> defaultValue = this.valueFactory.createValue(rawTag.getDefaultValue(), type.getClass());

            return new JExifTag(rawTag.getCount(), tagNumber, type.getClass(), defaultValue);
        } catch (JExifFactoryException e) {
            throw new JExifRawTagConverterException(e);
        }
    }

    public int getRadix() {
        return radix;
    }
}
