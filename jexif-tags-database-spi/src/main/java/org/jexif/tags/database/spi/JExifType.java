package org.jexif.tags.database.spi;

import java.nio.ByteOrder;

public interface JExifType {
    short getId();

    String getName();

    short getBytesNumber();

    String convert(byte[] value, ByteOrder bo);
}
