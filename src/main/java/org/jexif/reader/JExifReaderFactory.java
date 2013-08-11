package org.jexif.reader;

import org.jexif.api.JExifData;
import org.jexif.header.JExifHeader;
import org.jexif.header.JExifHeaderException;
import org.jexif.header.raw.RawJExifHeader;
import org.jexif.reader.buffer.api.BufferProvider;
import org.jexif.reader.impl.DefaultBufferProvider;

import java.nio.ByteBuffer;
import java.nio.file.Path;

public class JExifReaderFactory {


    public JExifReader createJExifReader() throws JExifReaderFactoryException {
        return new DefaultJExifReader();
    }

    private final static class DefaultJExifReader implements JExifReader {

        private static final int TIFF_HEADER_SIZE = 8;
        private final BufferProvider bufferProvider;

        DefaultJExifReader() {
            this.bufferProvider = new DefaultBufferProvider();
        }

        @Override
        public JExifData readExifData(Path path) throws JExifReaderException {
            try {
                ByteBuffer bb = this.bufferProvider.getByteBuffer(path);
                byte[] header = new byte[TIFF_HEADER_SIZE];
                bb.get(header);
                RawJExifHeader rawHeader = new RawJExifHeader(header);
                JExifHeader jexifHeader = new JExifHeader(rawHeader);
                System.out.println(String.format("Byte Order: %s", jexifHeader.getByteOrder()));
                System.out.println(String.format("Valid: %s", jexifHeader.isValid()));
                System.out.println(String.format("Offset of IFD: %s", jexifHeader.getOffsetOfIFD()));
            } catch (JExifHeaderException e) {
                throw new JExifReaderException(e);
            }
            return null;
        }
    }
}
