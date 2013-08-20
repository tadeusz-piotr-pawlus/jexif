package org.jexif.entry.converter;

import org.jexif.api.JExifTag;
import org.jexif.api.JExifTagNumber;
import org.jexif.api.type.JExifType;
import org.jexif.api.type.JExifTypeFactory;
import org.jexif.entry.JExifEntry;
import org.jexif.entry.raw.RawJExifEntry;
import org.jexif.tags.database.api.JExifTagsDatabase;
import org.jexif.tags.database.api.JExifTagsDatabaseException;

import java.nio.ByteOrder;

public class DefaultRawJExifEntryConverter implements RawJExifEntryConverter {

    private final JExifTagsDatabase tagsDatabase;
    private JExifRawTagNumberConverter tagNumberConverter;
    private JExifRawTypeConverter typeConverter;
    private JExifIntegerConverter integerConverter;
    private JExifValueConverter valueConverter;

    public DefaultRawJExifEntryConverter(ByteOrder bo, JExifTypeFactory typeFactory, JExifTagsDatabase tagsDatabase) {
        tagNumberConverter = new JExifRawTagNumberConverter(bo);
        typeConverter = new JExifRawTypeConverter(bo, typeFactory);
        integerConverter = new JExifIntegerConverter(bo);
        valueConverter = new JExifValueConverter();
        this.tagsDatabase = tagsDatabase;
    }

    @Override
    public JExifEntry convert(RawJExifEntry entry) throws RawExifEntryJExifConverterException {
        try {
            JExifTagNumber tagNumber = getTagNumberConverter().convert(entry.getTagNumber());
            JExifType type = getTypeConverter().convert(entry.getType());
            short count = getIntegerConverter().convert(entry.getCount());
            JExifTag tag = getTagsDatabase().getTag(tagNumber, type);
            return new JExifEntry(tag, type, count, null);
        } catch (JExifConverterException | JExifTagsDatabaseException ex) {
            throw new RawExifEntryJExifConverterException(ex);
        }
    }

    public JExifRawTagNumberConverter getTagNumberConverter() {
        return tagNumberConverter;
    }

    public JExifRawTypeConverter getTypeConverter() {
        return typeConverter;
    }

    public JExifIntegerConverter getIntegerConverter() {
        return integerConverter;
    }

    public JExifValueConverter getValueConverter() {
        return valueConverter;
    }

    public JExifTagsDatabase getTagsDatabase() {
        return tagsDatabase;
    }
}
