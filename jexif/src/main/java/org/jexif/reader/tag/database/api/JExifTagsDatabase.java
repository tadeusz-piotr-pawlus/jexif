package org.jexif.reader.tag.database.api;

import org.jexif.api.common.JExifTag;
import org.jexif.api.common.JExifTagNumber;
import org.jexif.api.common.type.JExifType;

public interface JExifTagsDatabase {
    JExifTag getTag(JExifTagNumber tagNumber, JExifType type) throws JExifTagsDatabaseException;
}
