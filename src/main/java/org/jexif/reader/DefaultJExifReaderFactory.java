package org.jexif.reader;

import org.jexif.api.common.JExifHeader;
import org.jexif.api.common.JExifTag;
import org.jexif.api.common.JExifValue;
import org.jexif.api.reader.*;
import org.jexif.reader.oop.header.JExifHeaderFactory;
import org.jexif.reader.tag.database.api.JExifTagsDatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
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
        private JExifHeaderFactory headerFactory;
        private JExifEntryFactory entryFactory;

        DefaultJExifReader() throws JExifTagsDatabaseException {
            this.headerFactory = new JExifHeaderFactory();
            this.entryFactory = new JExifEntryFactory();
        }

        @Override
        public JExifReaderData readExifData(ByteBuffer image) throws JExifReaderException {
            byte[] headerBuffer = new byte[TIFF_HEADER_SIZE];
            image.get(headerBuffer);
            JExifHeader tiffHeader = this.headerFactory.buildHeader(headerBuffer);
            DefaultJExifReaderData exifReaderData = new DefaultJExifReaderData();
            int nextIFD = tiffHeader.getOffsetOfIFD();
            ByteOrder bo = tiffHeader.getByteOrder();
            image.order(bo);
            while (nextIFD != 0) {
                logger.debug("IFD block offset: {}", nextIFD);
                image.position(nextIFD);
                int entriesNo = image.getShort();
                for (int i = 0; i < entriesNo; i++) {
                    try {
                        byte[] data = new byte[12];
                        image.get(data);
                        ByteBuffer bb = ByteBuffer.wrap(data).order(bo);
                        JExifEntry entry = entryFactory.createEntry(bb);
                        exifReaderData.put(entry);
                    } catch (JExifReaderFactoryException e) {
                        e.printStackTrace();
                    }
                }
                nextIFD = image.getShort();
            }
            return exifReaderData;
        }
    }

    private static class DefaultJExifReaderData implements JExifReaderData {

        private final Map<JExifTag, JExifEntry> tags;

        public DefaultJExifReaderData() {
            this.tags = new HashMap<>();
        }

        @Override
        public JExifValue getValueFor(JExifTag tag) {
            return null;
        }

        @Override
        public Collection<JExifTag> getTags() {
            return Collections.unmodifiableCollection(this.tags.keySet());
        }

        public void put(JExifEntry entry) {
            tags.put(entry.getTag(), entry);
        }
    }
}
