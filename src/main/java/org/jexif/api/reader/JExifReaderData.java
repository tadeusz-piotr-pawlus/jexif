package org.jexif.api.reader;

import org.jexif.api.common.JExifTag;
import org.jexif.api.common.JExifValue;

import java.util.Collection;

public interface JExifReaderData {

    JExifValue getValueFor(JExifTag tag);

    Collection<JExifTag> getTags();
}
