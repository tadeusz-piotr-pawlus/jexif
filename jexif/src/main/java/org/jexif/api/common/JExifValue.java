package org.jexif.api.common;

import org.jexif.tags.database.spi.JExifTag;

public class JExifValue {

    private final JExifTag tag;
    private final String value;

    public JExifValue(JExifTag tag, String value) {
        this.tag = tag;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public JExifTag getTag() {
        return tag;
    }
}
