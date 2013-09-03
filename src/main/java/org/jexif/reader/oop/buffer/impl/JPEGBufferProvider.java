package org.jexif.reader.oop.buffer.impl;

import org.jexif.reader.oop.buffer.api.BufferProviderException;

import java.nio.ByteBuffer;
import java.nio.file.Path;

public class JPEGBufferProvider extends AbstractBufferProvider {
    //I think EXIF data has to be in first 64KB of picture... But I might be wrong.

    private final static char LITTLE_ENDIAN = 0x49;
    private final static char BIG_ENDIAN = 0x4D;

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
}
