package org.jexif.tags.database;

import org.jexif.tags.database.spi.JExifTag;
import org.jexif.tags.database.spi.JExifTagNumber;
import org.jexif.tags.database.spi.JExifTagsDatabase;
import org.jexif.tags.database.spi.JExifType;

import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

/**
 * Created by tadekp on 10/12/2015.
 */
public class JExifTagsDatabaseService {

    private static JExifTagsDatabaseService service;
    private ServiceLoader<JExifTagsDatabase> loader;

    private JExifTagsDatabaseService() {
        loader = ServiceLoader.load(JExifTagsDatabase.class);
    }

    public static synchronized JExifTagsDatabaseService getInstance() {
        if (service == null) {
            service = new JExifTagsDatabaseService();
        }
        return service;
    }

    public JExifTag getTag(JExifTagNumber tagNumber, JExifType jExifType) {
        JExifTag definition = null;
        try {
            Iterator<JExifTagsDatabase> jExifTagsDatabase = loader.iterator();
            while (definition == null && jExifTagsDatabase.hasNext()) {
                JExifTagsDatabase database = jExifTagsDatabase.next();
                definition = database.getTag(tagNumber, jExifType);
            }
        } catch (ServiceConfigurationError serviceError) {
            definition = null;
            serviceError.printStackTrace();

        }
        return definition;
    }
}
