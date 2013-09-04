package org.jexif.entry;

import org.jexif.api.common.JExifTag;
import org.jexif.api.common.JExifValue;
import org.jexif.api.common.type.JExifType;

public class JExifEntry {
    private JExifTag tag;
    private JExifType type;
    private short count;
    private JExifValue value;

    public JExifEntry(JExifTag tag, JExifType type, short count, JExifValue value) {
        this.tag = tag;
        this.type = type;
        this.count = count;
        this.value = value;
    }

    public JExifTag getTag() {
        return tag;
    }

    public JExifType getType() {
        return type;
    }

    public short getCount() {
        return count;
    }

    public JExifValue getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("JExifEntry{tag=%s, type=%s, count=%s, value=%s}", tag, type, count, value);
    }
}
