package org.jexif.tags.database.type;

import org.jexif.tags.database.spi.JExifType;

public abstract class AbstractJExifType implements JExifType {

    private final short id;
    private final String name;
    private final short bytesNumber;

    AbstractJExifType(short id, String name, short bytesNumber) {
        this.id = id;
        this.name = name;
        this.bytesNumber = bytesNumber;
    }

    @Override
    public short getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public short getBytesNumber() {
        return bytesNumber;
    }

    @Override
    public String toString() {
        return getName();
    }
}
