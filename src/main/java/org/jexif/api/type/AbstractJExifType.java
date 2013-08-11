package org.jexif.api.type;

public abstract class AbstractJExifType implements JExifType {

    private final short id;
    private final String name;

    AbstractJExifType(short id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public short getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
