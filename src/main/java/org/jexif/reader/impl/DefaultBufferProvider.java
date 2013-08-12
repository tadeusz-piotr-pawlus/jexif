package org.jexif.reader.impl;

import org.jexif.reader.buffer.api.BufferProvider;
import org.jexif.reader.buffer.api.BufferProviderException;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;

public class DefaultBufferProvider implements BufferProvider {

    //I think EXIF data has to be in first 64KB of picture... But I might be wrong.
    public static final int _64KB = 1024 * 64;
    private final static char LITTLE_ENDIAN = 0x49;
    private final static char BIG_ENDIAN = 0x4D;

    @Override
    public ByteBuffer getByteBuffer(Path path) throws BufferProviderException {
        try {

            RandomAccessFile raf = new RandomAccessFile(path.toFile(), "r");
            FileChannel channel = raf.getChannel();
            long length = raf.length() < _64KB ? raf.length() : _64KB;
            ByteBuffer bb = channel.map(FileChannel.MapMode.READ_ONLY, 0, length);

            //look for beginning of TIFF Header.
            byte first = bb.get();
            byte second = bb.get();
            while (bb.hasRemaining()) {
                if ((first == LITTLE_ENDIAN && second == LITTLE_ENDIAN) || first == BIG_ENDIAN && second == BIG_ENDIAN) {
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
        } catch (IOException ex) {
            throw new BufferProviderException(ex);
        }
    }
}
