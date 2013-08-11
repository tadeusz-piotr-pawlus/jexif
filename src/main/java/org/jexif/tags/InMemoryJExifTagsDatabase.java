package org.jexif.tags;

import org.jexif.api.JExifTag;
import org.jexif.api.JExifTagNumber;
import org.jexif.api.type.JExifType;
import org.jexif.tags.converter.DefaultJExifRawTagConverter;
import org.jexif.tags.converter.JExifRawTagConverter;
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
    private final List<JExifTag<?>> tags;

    public InMemoryJExifTagsDatabase() throws JExifDatabaseException {
        try {
            JExifRawTagConverter rawTagConverter = new DefaultJExifRawTagConverter(16);

            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser parser = saxParserFactory.newSAXParser();
            RawTagsHandler handler = new RawTagsHandler();

            InputStream is = this.getClass().getClassLoader().getResourceAsStream("exif-tags.xml");

            parser.parse(new InputSource(is), handler);
            this.tags = new ArrayList<>();
            for (JExifRawTag rawTag : handler.getTags()) {
                tags.add(rawTagConverter.convert(rawTag));
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            throw new JExifDatabaseException(ex);
        }
    }

    @Override
    public <T extends JExifType> JExifTag<T> getTag(JExifTagNumber tagNumber, Class<T> tClass) throws JExifDatabaseException {
        for (JExifTag<?> tag : tags) {
            if (tag.getTagNumber().equals(tagNumber) && tag.getType().equals(tClass)) {
                return (JExifTag<T>) tag;
            }
        }
        throw new JExifDatabaseException("tag not found: " + tagNumber);
    }

}
