package org.jexif.tags.database.spi;


public interface JExifTagsDatabase {
    JExifTag getTag(JExifTagNumber tagNumber, JExifType type);
}
