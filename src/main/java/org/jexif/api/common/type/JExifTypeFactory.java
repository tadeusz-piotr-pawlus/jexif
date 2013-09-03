package org.jexif.api.common.type;

import java.util.HashMap;
import java.util.Map;

public class JExifTypeFactory {
    private final Map<String, Short> name2id;

    public JExifTypeFactory() {
        this.name2id = new HashMap<>();
        this.name2id.put(JExifByte.instance.getName(), JExifByte.instance.getId());
        this.name2id.put(JExifAscii.instance.getName(), JExifAscii.instance.getId());
        this.name2id.put(JExifShort.instance.getName(), JExifShort.instance.getId());
        this.name2id.put(JExifLong.instance.getName(), JExifLong.instance.getId());
        this.name2id.put(JExifRational.instance.getName(), JExifRational.instance.getId());
        this.name2id.put(JExifUndefined.instance.getName(), JExifUndefined.instance.getId());
        this.name2id.put(JExifSLong.instance.getName(), JExifSLong.instance.getId());
        this.name2id.put(JExifSRational.instance.getName(), JExifSRational.instance.getId());
    }

    public JExifType createByName(String name) throws JExifTypeFactoryException {
        Short id = name2id.get(name);
        if (id == null) {
            throw new JExifTypeFactoryException("Unknown type NAME: " + name);
        }
        return createById(id);
    }

    public JExifType createById(short id) throws JExifTypeFactoryException {
        switch (id) {
            case 1:
                return JExifByte.instance;
            case 2:
                return JExifAscii.instance;
            case 3:
                return JExifShort.instance;
            case 4:
                return JExifLong.instance;
            case 5:
                return JExifRational.instance;
            case 7:
                return JExifUndefined.instance;
            case 9:
                return JExifSLong.instance;
            case 10:
                return JExifSRational.instance;
            default:
                throw new JExifTypeFactoryException("Unknown type ID: " + id);
        }
    }

}
