package org.jexif.api.common;

import org.jexif.api.common.type.JExifType;

public class JExifValue {

    private JExifType type;

    public JExifValue(JExifType type) {
        this.type = type;
    }

    public JExifType getType() {
        return type;
    }
}
