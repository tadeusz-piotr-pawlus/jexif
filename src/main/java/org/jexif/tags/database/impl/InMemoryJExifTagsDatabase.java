package org.jexif.tags.database.impl;

import org.jexif.api.JExifTag;
import org.jexif.api.JExifTagNumber;
import org.jexif.api.type.JExifType;
import org.jexif.tags.converter.DefaultJExifRawTagConverter;
import org.jexif.tags.converter.JExifRawTagConverter;
import org.jexif.tags.database.api.JExifTagsDatabase;
import org.jexif.tags.database.api.JExifTagsDatabaseException;
import org.jexif.tags.raw.JExifRawTag;
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
            RawTagsHandler handler = new RawTagsHandler();

            InputStream is = this.getClass().getClassLoader().getResourceAsStream("exif-tags.xml");

            parser.parse(new InputSource(is), handler);
            this.tags = new ArrayList<>();

            JExifRawTagConverter rawTagConverter = new DefaultJExifRawTagConverter(handler.getRadix());
            for (JExifRawTag rawTag : handler.getTags()) {
                tags.add(rawTagConverter.convert(rawTag));
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

}
