package org.jexif.api;

import org.jexif.api.type.JExifType;

import java.io.Serializable;

/**
 * Class represents single entry. It's value is converted from byte[] to proper object.
 *
 * @author Tadeusz Piotr Pawlus
 */
public class JExifEntry<T extends JExifType> implements Serializable {
    private final JExifTag<T> tag;
    private final JExifValue<T> value;

    public JExifEntry(JExifTag<T> tag, JExifValue<T> value) {
        this.tag = tag;
        this.value = value;
    }

    public JExifTag<T> getTag() {
        return tag;
    }

    public JExifValue<T> getValue() {
        return value;
    }
}
