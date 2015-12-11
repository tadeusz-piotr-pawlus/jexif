package org.jexif.api.reader;

import org.jexif.api.common.JExifValue;
import org.jexif.tags.database.spi.JExifTagNumber;

import java.util.Collection;

public interface JExifReaderData {

    JExifValue getValueFor(JExifTagNumber tag) throws JExifReaderException;

    Collection<JExifTagNumber> getTagNumbers();
}
