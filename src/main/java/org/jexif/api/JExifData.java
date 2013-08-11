package org.jexif.api;

import org.jexif.api.type.JExifType;

public interface JExifData {

    <T extends JExifType> JExifValue<T> getValueFor(JExifTag tag);
}
