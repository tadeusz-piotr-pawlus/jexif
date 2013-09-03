package org.jexif.api.reader;

import org.jexif.api.common.JExifTag;
import org.jexif.api.common.JExifValue;

public interface JExifData {

    JExifValue getValueFor(JExifTag tag);
}
