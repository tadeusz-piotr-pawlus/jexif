package org.jexif.api;

import org.jexif.api.type.JExifType;

import java.io.Serializable;

public class JExifTag implements Serializable {
    private final JExifTagNumber tagNumber;
    private final int count;
    private final JExifType type;
    private final JExifValue defaultValue;
    private final String name;

    public JExifTag(JExifTagNumber tagNumber, String name, JExifType type, int count, JExifValue defaultValue) {
        this.tagNumber = tagNumber;
        this.name = name;
        this.type = type;
        this.count = count;
        this.defaultValue = defaultValue;
    }

    public JExifTagNumber getTagNumber() {
        return tagNumber;
    }

    public String getName() {
        return name;
    }

    public JExifType getType() {
        return type;
    }

    public int getCount() {
        return count;
    }

    public JExifValue getDefaultValue() {
        return defaultValue;
    }

    @Override
    public String toString() {
        return getName();
    }
}
