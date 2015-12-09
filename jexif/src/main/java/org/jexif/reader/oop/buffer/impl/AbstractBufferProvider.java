package org.jexif.reader.oop.buffer.impl;

import org.jexif.reader.oop.buffer.api.BufferProvider;
import org.jexif.reader.oop.buffer.api.BufferProviderException;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;

public abstract class AbstractBufferProvider implements BufferProvider {
    private static final int _64KB = 1024 * 64;
    private static final String READ_ONLY = "r";

    protected ByteBuffer map(Path path) throws BufferProviderException {
        try {
            RandomAccessFile raf = new RandomAccessFile(path.toFile(), READ_ONLY);
            FileChannel channel = raf.getChannel();
            int length = raf.length() < _64KB ? (int)raf.length() : _64KB;
            ByteBuffer data = ByteBuffer.wrap(new byte[length]);
            channel.read(data);
            data.flip();
            raf.close();
            return data;
        } catch (IOException ex) {
            throw new BufferProviderException(ex);
        }
    }
}
