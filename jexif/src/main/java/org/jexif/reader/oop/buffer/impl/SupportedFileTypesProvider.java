package org.jexif.reader.oop.buffer.impl;

import org.jexif.reader.oop.buffer.api.BufferProvider;
import org.jexif.reader.oop.buffer.api.BufferProviderException;
import org.jexif.reader.oop.buffer.api.ExplicitCaseInsensitiveExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class SupportedFileTypesProvider {

    private final static Logger logger = LoggerFactory.getLogger(SupportedFileTypesProvider.class);
    private final Map<ExplicitCaseInsensitiveExtension, BufferProvider> supportedExtensions;

    public SupportedFileTypesProvider() {
        this.supportedExtensions = new HashMap<>();
    }

    public ByteBuffer getByteBuffer(Path path) throws BufferProviderException {
        BufferProvider bufferProvider = getBufferProvider(path);
        if (bufferProvider == null) {
            logger.debug("No registered buffer provider for file: {}", path);
            throw new BufferProviderException("No registered buffer provider for file: " + path);
        }
        return bufferProvider.getByteBuffer(path);
    }

    private BufferProvider getBufferProvider(Path path) {
        for (Map.Entry<ExplicitCaseInsensitiveExtension, BufferProvider> supportedExtension : getSupportedExtensions().entrySet()) {
            if (supportedExtension.getKey().supports(path)) {
                return supportedExtension.getValue();
            }
        }
        return null;
    }

    public void registerBufferProvider(BufferProvider bufferProvider) throws BufferProviderException {
        for (ExplicitCaseInsensitiveExtension e : bufferProvider.getSupportedExtensions()) {
            getSupportedExtensions().put(e, bufferProvider);
        }
    }

    public Map<ExplicitCaseInsensitiveExtension, BufferProvider> getSupportedExtensions() {
        return supportedExtensions;
    }
}
