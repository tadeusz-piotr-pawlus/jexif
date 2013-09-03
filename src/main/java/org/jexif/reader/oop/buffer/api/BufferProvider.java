package org.jexif.reader.oop.buffer.api;

import java.nio.ByteBuffer;
import java.nio.file.Path;

public interface BufferProvider {
    ByteBuffer getByteBuffer(Path path) throws BufferProviderException;
}
