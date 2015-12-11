package org.jexif.tags.database;

import org.jexif.tags.database.spi.*;
import org.jexif.tags.database.type.JExifTypeFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryJExifTagsDatabase implements JExifTagsDatabase {
    private final List<JExifTag> tags;

    public InMemoryJExifTagsDatabase() {
        this.tags = readTagsFromXML();
    }

    private List<JExifTag> readTagsFromXML() {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser parser = saxParserFactory.newSAXParser();
            JExifTagDatabaseHandler handler = new JExifTagDatabaseHandler();
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("exif-tags.xml");
            parser.parse(new InputSource(is), handler);
            TagsConverter tagConverter = new TagsConverter(handler.getRadix());
            return handler.getTags()
                    .stream()
                    .map(tagConverter::convert)
                    .collect(Collectors.toList());
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            //TODO
            ex.printStackTrace();
        }
        //TODO
        return new ArrayList<>();
    }

    @Override
    public JExifTag getTag(JExifTagNumber tagNumber, JExifType type) {
        for (JExifTag tag : tags) {
            if (tag.getTagNumber().equals(tagNumber) && tag.getType().equals(type)) {
                return tag;
            }
        }
        return null;
    }

    private final static class TagsConverter {
        private final JExifTypeFactory typeFactory;
        private final int radix;

        public TagsConverter(int radix) {
            this.typeFactory = new JExifTypeFactory();
            this.radix = radix;
        }

        public JExifTag convert(JExifDatabaseTag rawTag) {
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
