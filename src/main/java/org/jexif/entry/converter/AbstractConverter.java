package org.jexif.entry.converter;

import java.nio.ByteOrder;

public abstract class AbstractConverter {

    private final ByteOrder bo;

    public AbstractConverter(ByteOrder bo) {
        this.bo = bo;
    }

    public ByteOrder getByteOrder() {
        return bo;
    }
}
