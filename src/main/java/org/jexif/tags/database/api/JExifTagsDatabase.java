package org.jexif.tags.database.api;

import org.jexif.api.JExifTag;
import org.jexif.api.JExifTagNumber;
import org.jexif.api.type.JExifType;

public interface JExifTagsDatabase {
    JExifTag getTag(JExifTagNumber tagNumber, JExifType type) throws JExifTagsDatabaseException;
}
