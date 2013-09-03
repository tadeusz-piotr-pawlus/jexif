package org.jexif.api.reader;

import java.nio.ByteBuffer;

public interface JExifReader {

    JExifData readExifData(ByteBuffer image) throws JExifReaderException;
}
