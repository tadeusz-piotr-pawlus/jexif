package org.jexif.reader;

import org.jexif.api.common.JExifHeader;
import org.jexif.api.common.JExifTagNumber;
import org.jexif.api.common.JExifValue;
import org.jexif.api.reader.*;
import org.jexif.reader.oop.header.JExifHeaderFactory;
import org.jexif.reader.oop.header.JExifHeaderFactoryException;
import org.jexif.reader.tag.database.api.JExifTagsDatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DefaultJExifReaderFactory implements JExifReaderFactory {

    private final static Logger logger = LoggerFactory.getLogger(JExifReaderFactory.class);

    public DefaultJExifReaderFactory() {
    }

    public JExifReader createJExifReader() throws JExifReaderFactoryException {
        try {
            return new DefaultJExifReader();
        } catch (JExifTagsDatabaseException ex) {
            throw new JExifReaderFactoryException(ex);
        }
    }

    private final static class DefaultJExifReader implements JExifReader {

        private static final int TIFF_HEADER_SIZE = 8;
        private final JExifHeaderFactory headerFactory;
        private JExifEntryFactory entryFactory;

        DefaultJExifReader() throws JExifTagsDatabaseException {
            this.headerFactory = new JExifHeaderFactory();
            this.entryFactory = new JExifEntryFactory();
        }

        @Override
        public JExifReaderData readExifData(ByteBuffer image) throws JExifReaderException {
            JExifHeader header = getExifHeader(image);
            image.order(header.getByteOrder());
            return getExifReaderData(image, header.getOffsetOfIFD());
        }

        private DefaultJExifReaderData getExifReaderData(ByteBuffer image, int firstOffsetOfIFD) {
            DefaultJExifReaderData exifReaderData = new DefaultJExifReaderData();
            int ifdOffset = firstOffsetOfIFD;
            while (ifdOffset != 0) {
                logger.debug("IFD block offset: {}", ifdOffset);
                image.position(ifdOffset);
                getExifEntriesFromIFD(image, exifReaderData);
                ifdOffset = image.getShort();
            }
            return exifReaderData;
        }

        private void getExifEntriesFromIFD(ByteBuffer image, DefaultJExifReaderData exifReaderData) {
            int entriesNo = image.getShort();
            for (int i = 0; i < entriesNo; i++) {
                try {
                    JExifEntry entry = entryFactory.createEntry(image);
                    exifReaderData.put(entry);
                } catch (JExifReaderFactoryException e) {
                    logger.error("Unable to create Exif Entry starting at {}. Continuing...", image.position());
                }
            }
        }

        private JExifHeader getExifHeader(ByteBuffer image) throws JExifHeaderFactoryException {
            byte[] headerBuffer = new byte[TIFF_HEADER_SIZE];
            image.get(headerBuffer);
            return this.headerFactory.buildHeader(headerBuffer);
        }
    }

    private static class DefaultJExifReaderData implements JExifReaderData {

        private final Map<JExifTagNumber, JExifEntry> tags;

        public DefaultJExifReaderData() {
            this.tags = new HashMap<>();
        }

        @Override
        public JExifValue getValueFor(JExifTagNumber tag) throws JExifReaderException {
            if (tags.containsKey(tag)) {
                return tags.get(tag).getValue();
            }
            throw new JExifReaderException("No tag with number " + tag.getNumber() + " defined");
        }

        @Override
        public Collection<JExifTagNumber> getTagNumbers() {
            return Collections.unmodifiableCollection(this.tags.keySet());
        }

        public void put(JExifEntry entry) {
            tags.put(entry.getTag().getTagNumber(), entry);
        }
    }
}
