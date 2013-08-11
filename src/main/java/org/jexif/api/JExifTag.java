package org.jexif.api;

import org.jexif.api.type.JExifType;

import java.io.Serializable;

public class JExifTag<T extends JExifType> implements Serializable {
    private final JExifTagNumber tagNumber;
    private final int count;
    private final Class<T> type;
    private final JExifValue<T> defaultValue;

    public JExifTag(int count, JExifTagNumber tagNumber, Class<T> type, JExifValue<T> defaultValue) {
        this.count = count;
        this.tagNumber = tagNumber;
        this.type = type;
        this.defaultValue = defaultValue;
    }

    public int getCount() {
        return count;
    }

    public JExifTagNumber getTagNumber() {
        return tagNumber;
    }

    public Class<T> getType() {
        return type;
    }

    public JExifValue<T> getDefaultValue() {
        return defaultValue;
    }

    @Override
    public String toString() {
        return "JExifTag{" +
                "tagNumber=" + tagNumber +
                ", count=" + count +
                ", type=" + type +
                ", defaultValue=" + defaultValue +
                '}';
    }
}
