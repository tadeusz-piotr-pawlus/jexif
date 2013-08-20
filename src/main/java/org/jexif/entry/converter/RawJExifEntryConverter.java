package org.jexif.entry.converter;

import org.jexif.entry.JExifEntry;
import org.jexif.entry.raw.RawJExifEntry;

public interface RawJExifEntryConverter {

    JExifEntry convert(RawJExifEntry entry) throws RawExifEntryJExifConverterException;
}
