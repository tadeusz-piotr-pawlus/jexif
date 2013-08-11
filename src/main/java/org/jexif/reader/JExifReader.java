package org.jexif.reader;

import org.jexif.api.JExifData;

import java.nio.file.Path;

public interface JExifReader {

    JExifData readExifData(Path path) throws JExifReaderException;
}
