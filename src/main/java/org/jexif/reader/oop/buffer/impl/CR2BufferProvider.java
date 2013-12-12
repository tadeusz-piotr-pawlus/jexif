package org.jexif.reader.oop.buffer.impl;

import org.jexif.reader.oop.buffer.api.BufferProviderException;
import org.jexif.reader.oop.buffer.api.ExplicitCaseInsensitiveExtension;

import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class CR2BufferProvider extends AbstractBufferProvider {
    //I think EXIF data has to be in first 64KB of picture... But I might be wrong.

    private final static char LITTLE_ENDIAN = 0x49;
    private final static char BIG_ENDIAN = 0x4D;
    private final static ExplicitCaseInsensitiveExtension cr2 = new ExplicitCaseInsensitiveExtension("CR2");

    @Override
    public ByteBuffer getByteBuffer(Path path) throws BufferProviderException {
        //TODO: update according to CR2 spec.
        ByteBuffer bb = map(path);
        return bb;
    }

    @Override
    public Collection<ExplicitCaseInsensitiveExtension> getSupportedExtensions() throws BufferProviderException {
        return Collections.unmodifiableCollection(Arrays.asList(cr2));
    }
}
