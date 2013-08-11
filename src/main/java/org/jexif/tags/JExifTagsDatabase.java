package org.jexif.tags;

import org.jexif.api.JExifTag;
import org.jexif.api.JExifTagNumber;
import org.jexif.api.type.JExifType;

public interface JExifTagsDatabase {
    <T extends JExifType> JExifTag<T> getTag(JExifTagNumber tagNumber, Class<T> clazz) throws JExifDatabaseException;
}
