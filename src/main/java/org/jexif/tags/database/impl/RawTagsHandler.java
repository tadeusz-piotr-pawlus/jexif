package org.jexif.tags.database.impl;

import org.jexif.tags.raw.JExifRawTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Collection;

public class RawTagsHandler extends DefaultHandler {

    private final static Logger logger = LoggerFactory.getLogger(RawTagsHandler.class);
    private final static String TAG_NAME = "tag";
    public static final String NAME = "name";
    public static final String HEX = "hex";
    public static final String TYPE = "type";
    private Collection<JExifRawTag> tags;
    private String name;
    private String number;
    private String type;

    public RawTagsHandler() {

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        logger.debug("Creating raw tag: {} (hex={}, type={}, count={}, defaultValue={}).", this.name, this.number);

        JExifRawTag rawTag = new JExifRawTag(name, number, type);
        this.tags.add(rawTag);
    }

    @Override
    public void startDocument() throws SAXException {
        this.tags = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (TAG_NAME.equals(qName)) {
            this.name = attributes.getValue(NAME);
            this.number = attributes.getValue(HEX);
            this.type = attributes.getValue(TYPE);
        }
    }

    public Collection<JExifRawTag> getTags() {
        return this.tags;
    }
}