package org.jexif.reader.oop.buffer.api;

import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.util.Collection;

public interface BufferProvider {
    ByteBuffer getByteBuffer(Path path) throws BufferProviderException;

    Collection<ExplicitCaseInsensitiveExtension> getSupportedExtensions() throws BufferProviderException;
}
