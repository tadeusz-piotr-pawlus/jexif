package org.jexif.reader;

import org.jexif.api.common.JExifHeader;
import org.jexif.api.common.JExifTag;
import org.jexif.api.common.JExifValue;
import org.jexif.api.reader.*;
import org.jexif.reader.oop.header.JExifHeaderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DefaultJExifReaderFactory implements JExifReaderFactory {

    private final static Logger logger = LoggerFactory.getLogger(JExifReaderFactory.class);

    public JExifReader createJExifReader() throws JExifReaderFactoryException {
        return new DefaultJExifReader();
    }

    private final static class DefaultJExifReader implements JExifReader {

        private static final int TIFF_HEADER_SIZE = 8;
        private JExifHeaderFactory headerFactory;

        DefaultJExifReader() {
            this.headerFactory = new JExifHeaderFactory();
        }

        @Override
        public JExifReaderData readExifData(ByteBuffer image) throws JExifReaderException {
            byte[] headerBuffer = new byte[TIFF_HEADER_SIZE];
            image.get(headerBuffer);
            JExifHeader tiffHeader = this.headerFactory.buildHeader(headerBuffer);
            if (!tiffHeader.isValid()) {
                throw new JExifReaderException("Tiff Header is not valid!");
            }
            DefaultJExifReaderData exifReaderData = new DefaultJExifReaderData();
            int nextIFD = tiffHeader.getOffsetOfIFD();
            image.order(tiffHeader.getByteOrder());
            while (nextIFD != 0) {
                logger.debug("IFD block offset: {}", nextIFD);
                image.position(nextIFD);
                int entriesNo = image.getShort();
                for (int i = 0; i < entriesNo; i++) {
                    byte[] data = new byte[12];
                    image.get(data);
                    exifReaderData.put(null, null);
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

        public void put(JExifTag tag, JExifEntry entry) {
            tags.put(tag, entry);
        }
    }
}
