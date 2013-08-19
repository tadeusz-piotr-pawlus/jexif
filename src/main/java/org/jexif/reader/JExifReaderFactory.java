package org.jexif.reader;

import org.jexif.api.JExifData;
import org.jexif.header.JExifHeader;
import org.jexif.header.JExifHeaderException;
import org.jexif.header.raw.RawImageFileDirectory;
import org.jexif.header.raw.RawJExifHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class JExifReaderFactory {

    private final static Logger logger = LoggerFactory.getLogger(JExifReaderFactory.class);

    public JExifReader createJExifReader() throws JExifReaderFactoryException {
        return new DefaultJExifReader();
    }

    private final static class DefaultJExifReader implements JExifReader {

        private static final int TIFF_HEADER_SIZE = 8;

        DefaultJExifReader() {
        }

        @Override
        public JExifData readExifData(ByteBuffer image) throws JExifReaderException {
            try {
                byte[] header = new byte[TIFF_HEADER_SIZE];
                image.get(header);
                RawJExifHeader rawHeader = new RawJExifHeader(header);
                JExifHeader tiffHeader = new JExifHeader(rawHeader);
                if (!tiffHeader.isValid()) {
                    throw new JExifReaderException("Tiff Header is not valid!");
                }
                List<RawImageFileDirectory> listOfIFD = new ArrayList<>();
                int nextIFD = tiffHeader.getOffsetOfIFD();
                logger.debug("First IFD block offset: {}", tiffHeader.getOffsetOfIFD());
                image.order(tiffHeader.getByteOrder());
                while (nextIFD != 0) {
                    image.position(nextIFD);
                    RawImageFileDirectory ifd = new RawImageFileDirectory(image);
                    listOfIFD.add(ifd);
                    nextIFD = ifd.getNextIFDOffset();
                    logger.debug("Entries: {} ", ifd.getNumberOfInteroperability());
                    logger.debug("Next IFD block offset: {}", ifd.getNextIFDOffset());
                }
                logger.debug("Number of IFD blocks: {}", listOfIFD.size());
                return null;
            } catch (JExifHeaderException e) {
                throw new JExifReaderException(e);
            }
        }
    }
}
