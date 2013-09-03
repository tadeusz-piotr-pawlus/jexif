package org.jexif.reader.tag.database.impl;

import org.jexif.api.common.JExifTag;
import org.jexif.api.common.JExifTagNumber;
import org.jexif.api.common.JExifValue;
import org.jexif.api.common.type.JExifType;
import org.jexif.api.common.type.JExifTypeFactory;
import org.jexif.api.common.type.JExifTypeFactoryException;
import org.jexif.reader.tag.database.api.JExifTagsDatabase;
import org.jexif.reader.tag.database.api.JExifTagsDatabaseException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class InMemoryJExifTagsDatabase implements JExifTagsDatabase {
    private final List<JExifTag> tags;

    public InMemoryJExifTagsDatabase() throws JExifTagsDatabaseException {
        try {

            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser parser = saxParserFactory.newSAXParser();
            JExifTagDatabaseHandler handler = new JExifTagDatabaseHandler();

            InputStream is = this.getClass().getClassLoader().getResourceAsStream("exif-tags.xml");

            parser.parse(new InputSource(is), handler);
            this.tags = new ArrayList<>();

            TagsConverter tagConverter = new TagsConverter(handler.getRadix());
            for (JExifDatabaseTag rawTag : handler.getTags()) {
                try {
                    tags.add(tagConverter.convert(rawTag));
                } catch (JExifTypeFactoryException e) {
                    //unable to convert type.
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            throw new JExifTagsDatabaseException(ex);
        }
    }

    @Override
    public JExifTag getTag(JExifTagNumber tagNumber, JExifType type) throws JExifTagsDatabaseException {
        for (JExifTag tag : tags) {
            if (tag.getTagNumber().equals(tagNumber) && tag.getType().equals(type)) {
                return tag;
            }
        }
        throw new JExifTagsDatabaseException("tag not found: " + tagNumber);
    }

    private final static class TagsConverter {
        private final JExifTypeFactory typeFactory;
        private final int radix;

        public TagsConverter(int radix) {
            this.typeFactory = new JExifTypeFactory();
            this.radix = radix;
        }

        public JExifTag convert(JExifDatabaseTag rawTag) throws JExifTypeFactoryException {
            JExifTagNumber tagNumber = new JExifTagNumber(rawTag.getNumber(), getRadix());
            String name = rawTag.getName();
            JExifType type = this.typeFactory.createByName(rawTag.getType());
            int count = rawTag.getCount();
//                JExifValue defaultValue = this.valueFactory.createValue(rawTag.getDefaultValue(), type);
            JExifValue defaultValue = null;
            return new JExifTag(tagNumber, name, type, count, defaultValue);
        }

        public int getRadix() {
            return radix;
        }
    }
}
