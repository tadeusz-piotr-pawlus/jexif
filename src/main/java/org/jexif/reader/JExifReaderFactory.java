package org.jexif.reader;

import org.jexif.api.JExifData;
import org.jexif.api.type.JExifTypeFactory;
import org.jexif.entry.JExifEntry;
import org.jexif.entry.converter.DefaultRawJExifEntryConverter;
import org.jexif.entry.converter.RawExifEntryJExifConverterException;
import org.jexif.entry.converter.RawJExifEntryConverter;
import org.jexif.entry.raw.RawJExifEntry;
import org.jexif.header.JExifHeader;
import org.jexif.header.JExifHeaderException;
import org.jexif.header.raw.RawImageFileDirectory;
import org.jexif.header.raw.RawJExifHeader;
import org.jexif.tags.database.api.JExifTagsDatabase;
import org.jexif.tags.database.api.JExifTagsDatabaseException;
import org.jexif.tags.database.impl.InMemoryJExifTagsDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class JExifReaderFactory {

    private final static Logger logger = LoggerFactory.getLogger(JExifReaderFactory.class);

    public JExifReader createJExifReader() throws JExifReaderFactoryException {
        try {
            JExifTypeFactory typeFactory = new JExifTypeFactory();
            JExifTagsDatabase tagsDatabase = new InMemoryJExifTagsDatabase();

            return new DefaultJExifReader(typeFactory, tagsDatabase);
        } catch (JExifTagsDatabaseException ex) {
            throw new JExifReaderFactoryException(ex);
        }
    }

    private final static class DefaultJExifReader implements JExifReader {

        private static final int TIFF_HEADER_SIZE = 8;
        private final JExifTypeFactory typeFactory;
        private final JExifTagsDatabase tagsDatabase;

        DefaultJExifReader(JExifTypeFactory typeFactory, JExifTagsDatabase tagsDatabase) {
            this.typeFactory = typeFactory;
            this.tagsDatabase = tagsDatabase;
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

                List<JExifEntry> entries = new ArrayList<>();
                RawJExifEntryConverter converter = new DefaultRawJExifEntryConverter(tiffHeader.getByteOrder(), getTypeFactory(), getTagsDatabase());

                for (RawImageFileDirectory rifd : listOfIFD) {
                    for (RawJExifEntry rawEntry : rifd.getData()) {
                        try {
                            JExifEntry entry = converter.convert(rawEntry);
                            System.out.println(entry);
                            entries.add(entry);
                        } catch (RawExifEntryJExifConverterException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                }
                return null;
            } catch (JExifHeaderException e) {
                throw new JExifReaderException(e);
            }
        }

        private JExifTypeFactory getTypeFactory() {
            return typeFactory;
        }

        private JExifTagsDatabase getTagsDatabase() {
            return tagsDatabase;
        }
    }
}
