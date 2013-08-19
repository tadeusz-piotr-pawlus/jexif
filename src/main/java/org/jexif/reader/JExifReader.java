package org.jexif.reader;

import org.jexif.api.JExifData;

import java.nio.ByteBuffer;

public interface JExifReader {

    JExifData readExifData(ByteBuffer image) throws JExifReaderException;
}
