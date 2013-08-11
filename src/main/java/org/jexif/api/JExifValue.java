package org.jexif.api;

import org.jexif.api.type.JExifType;

public class JExifValue {

    private JExifType type;

    public JExifValue(JExifType type) {
        this.type = type;
    }

    public JExifType getType() {
        return type;
    }
}
