package org.jexif.reader;

import org.jexif.api.common.JExifValue;
import org.jexif.api.reader.JExifEntry;
import org.jexif.api.reader.JExifReaderFactoryException;
import org.jexif.reader.tag.database.api.JExifTagsDatabaseException;
import org.jexif.tags.database.JExifTagsDatabaseService;
import org.jexif.tags.database.spi.JExifTag;
import org.jexif.tags.database.spi.JExifTagNumber;
import org.jexif.tags.database.spi.JExifType;
import org.jexif.tags.database.type.JExifTypeFactory;

import java.nio.ByteBuffer;

public class JExifEntryFactory {
    private final JExifTagNumberFactory tagNumberFactory;
    private final JExifTypeFactory typeFactory;
    private JExifValueFactory valueFactory;

    public JExifEntryFactory() throws JExifTagsDatabaseException {
        this.tagNumberFactory = new JExifTagNumberFactory();
        this.typeFactory = new JExifTypeFactory();
        this.valueFactory = new JExifValueFactory();
    }

    public JExifEntry createEntry(ByteBuffer bb) throws JExifReaderFactoryException {
        JExifTagNumber tagNumber = tagNumberFactory.createNumber(bb.getShort());
        JExifType type = typeFactory.createById(bb.getShort());
        JExifTag tag = JExifTagsDatabaseService.getInstance().getTag(tagNumber, type);
        JExifValue value = valueFactory.createValue(tag, bb);
        return new JExifEntry(tag, value);
    }
}
