package org.jexif.reader.oop.buffer.impl;

import org.jexif.reader.oop.buffer.api.BufferProviderException;
import org.jexif.reader.oop.buffer.api.ExplicitCaseInsensitiveExtension;

import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class JPEGBufferProvider extends AbstractBufferProvider {
    //I think EXIF data has to be in first 64KB of picture... But I might be wrong.

    private final static char LITTLE_ENDIAN = 0x49;
    private final static char BIG_ENDIAN = 0x4D;
    private final static ExplicitCaseInsensitiveExtension jpeg = new ExplicitCaseInsensitiveExtension("JPEG");
    private final static ExplicitCaseInsensitiveExtension jpg = new ExplicitCaseInsensitiveExtension("JPG");

    @Override
    public ByteBuffer getByteBuffer(Path path) throws BufferProviderException {
        ByteBuffer bb = map(path);
        //look for beginning of TIFF Header.
        byte first = bb.get();
        byte second = bb.get();
        while (bb.hasRemaining()) {
            if ((first == LITTLE_ENDIAN && second == LITTLE_ENDIAN) || (first == BIG_ENDIAN && second == BIG_ENDIAN)) {
                break;
            }
            first = second;
            second = bb.get();
        }
        if (!bb.hasRemaining()) {
            throw new BufferProviderException("No Exif Header found!");
        }
        bb.position(bb.position() - 2);
        return bb.slice();
    }

    @Override
    public Collection<ExplicitCaseInsensitiveExtension> getSupportedExtensions() throws BufferProviderException {
        return Collections.unmodifiableCollection(Arrays.asList(jpeg, jpg));
    }
}
