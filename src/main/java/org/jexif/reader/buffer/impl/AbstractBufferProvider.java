package org.jexif.reader.buffer.impl;

import org.jexif.reader.buffer.api.BufferProvider;
import org.jexif.reader.buffer.api.BufferProviderException;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;

public abstract class AbstractBufferProvider implements BufferProvider {
    public static final int _64KB = 1024 * 64;
    public static final String READ_ONLY = "r";

    protected ByteBuffer map(Path path) throws BufferProviderException {
        try {
            RandomAccessFile raf = new RandomAccessFile(path.toFile(), READ_ONLY);
            FileChannel channel = raf.getChannel();
            long length = raf.length() < _64KB ? raf.length() : _64KB;
            return channel.map(FileChannel.MapMode.READ_ONLY, 0, length);
        } catch (IOException ex) {
            throw new BufferProviderException(ex);
        }

    }
}
