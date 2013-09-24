package org.jexif.api.reader;

import org.jexif.api.common.JExifTag;
import org.jexif.api.common.JExifValue;
import org.jexif.api.common.type.JExifType;

import java.io.Serializable;

/**
 * Class represents single entry. It's value is converted from byte[] to proper object.
 *
 * @author Tadeusz Piotr Pawlus
 */
public class JExifEntry implements Serializable {
    private final JExifTag tag;
    private final JExifValue value;

    public JExifEntry(JExifTag tag, JExifType type, short count, JExifValue value) {
        this.tag = tag;
        this.value = value;
    }

    public JExifTag getTag() {
        return tag;
    }

    public JExifValue getValue() {
        return value;
    }
}
