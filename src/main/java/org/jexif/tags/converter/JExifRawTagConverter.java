package org.jexif.tags.converter;

import org.jexif.api.JExifTag;
import org.jexif.tags.raw.JExifRawTag;

public interface JExifRawTagConverter {
    JExifTag convert(JExifRawTag rawTag) throws JExifTagsRawTagConverterException;
}
