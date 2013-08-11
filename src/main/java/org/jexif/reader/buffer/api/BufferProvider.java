package org.jexif.reader.buffer.api;

import java.nio.ByteBuffer;
import java.nio.file.Path;

public interface BufferProvider {
    ByteBuffer getByteBuffer(Path path) throws BufferProviderException;
}
