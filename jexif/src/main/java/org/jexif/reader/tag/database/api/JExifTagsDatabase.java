package org.jexif.reader.tag.database.api;

import org.jexif.tags.database.spi.JExifTag;
import org.jexif.tags.database.spi.JExifTagNumber;
import org.jexif.tags.database.spi.JExifType;

public interface JExifTagsDatabase {
    JExifTag getTag(JExifTagNumber tagNumber, JExifType type);
}
