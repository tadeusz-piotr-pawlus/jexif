package org.jexif.reader;

import org.jexif.api.JExifData;
import org.jexif.header.JExifHeader;
import org.jexif.header.JExifHeaderException;
import org.jexif.header.raw.RawJExifHeader;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class JExifReaderFactory {

    public static final int HEADER_SIZE = 8;

    public JExifReader createJExifReader() throws JExifReaderFactoryException {
        return new DefaultJExifReader();
    }

    private final static class DefaultJExifReader implements JExifReader {

        @Override
        public JExifData readExifData(Path path) throws JExifReaderException {
            try {
                SeekableByteChannel channel = Files.newByteChannel(path, StandardOpenOption.READ);
                ByteBuffer header = ByteBuffer.allocate(HEADER_SIZE);
                channel.position(0);
                boolean isSOIMarker = false;
                while (!isSOIMarker) {
                    ByteBuffer soiMarker = ByteBuffer.allocate(2);
                    channel.read(soiMarker);
                    soiMarker.flip();
                    byte byte1 = soiMarker.get();
                    byte byte2 = soiMarker.get();
                    if (byte1 == (byte) 0xff && byte2 == (byte) 0xd8) {
                        isSOIMarker = true;
                    }
                    soiMarker.clear();
                }
                channel.position(12);
                channel.read(header);
                header.flip();
                RawJExifHeader rawHeader = new RawJExifHeader(header);
                JExifHeader jexifHeader = new JExifHeader(rawHeader);
                System.out.println(String.format("Byte Order: %s", jexifHeader.getByteOrder()));
                System.out.println(String.format("Valid: %s", jexifHeader.isValid()));
                System.out.println(String.format("Offset of IFD: %s", jexifHeader.getOffsetOfIFD()));
                return null;
            } catch (IOException e) {
                throw new JExifReaderException(e);
            } catch (JExifHeaderException e) {
                throw new JExifReaderException(e);
            }
        }
    }
}
