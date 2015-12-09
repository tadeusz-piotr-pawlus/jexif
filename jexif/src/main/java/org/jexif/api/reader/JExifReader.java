package org.jexif.api.reader;

import java.nio.ByteBuffer;

public interface JExifReader {

    JExifReaderData readExifData(ByteBuffer image) throws JExifReaderException;
}
