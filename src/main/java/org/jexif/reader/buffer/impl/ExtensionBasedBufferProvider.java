package org.jexif.reader.buffer.impl;

import org.jexif.reader.buffer.api.BufferProvider;
import org.jexif.reader.buffer.api.BufferProviderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class ExtensionBasedBufferProvider implements BufferProvider {

    private final static Logger logger = LoggerFactory.getLogger(ExtensionBasedBufferProvider.class);
    private final Map<String, BufferProvider> supportedExtensions;

    public ExtensionBasedBufferProvider() {
        this.supportedExtensions = new HashMap<>();
        this.supportedExtensions.put("jpg", new JPEGBufferProvider());
        this.supportedExtensions.put("jpeg", new JPEGBufferProvider());
    }

    @Override
    public ByteBuffer getByteBuffer(Path path) throws BufferProviderException {
        String extension = getExtension(path).toLowerCase();
        BufferProvider bufferProvider = getSupportedExtensions().get(extension);
        if (bufferProvider == null) {
            logger.debug("No registered buffer provider for extension: {}", extension);
            throw new BufferProviderException("No registered buffer provider for extension: " + extension);
        }
        return bufferProvider.getByteBuffer(path);
    }

    private String getExtension(Path path) {
        String name = path.getFileName().toString();
        int dotIndex = name.lastIndexOf('.');
        return name.substring(dotIndex + 1);
    }

    public Map<String, BufferProvider> getSupportedExtensions() {
        return supportedExtensions;
    }
}
