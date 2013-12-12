package org.jexif.api.reader;

import org.jexif.api.common.JExifTagNumber;
import org.jexif.api.common.JExifValue;

import java.util.Collection;

public interface JExifReaderData {

    JExifValue getValueFor(JExifTagNumber tag) throws JExifReaderException;

    Collection<JExifTagNumber> getTagNumbers();
}
