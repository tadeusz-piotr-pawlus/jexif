package org.jexif.tags.converter;

import org.jexif.api.JExifTag;
import org.jexif.api.type.JExifType;
import org.jexif.tags.raw.JExifRawTag;

public interface JExifRawTagConverter {
    JExifTag<? extends JExifType> convert(JExifRawTag rawTag) throws JExifRawTagConverterException;
}
