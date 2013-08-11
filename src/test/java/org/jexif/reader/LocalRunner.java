package org.jexif.reader;

import org.jexif.api.*;
import org.jexif.api.type.JExifShort;
import org.jexif.tags.InMemoryJExifTagsDatabase;
import org.jexif.tags.JExifTagsDatabase;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LocalRunner {

    public static void main(String[] args) throws JExifException, IOException {
        JExifTagsDatabase database = new InMemoryJExifTagsDatabase();
        JExifTagNumber tagNumber = new JExifTagNumber("a405", 16);
        JExifTag<JExifShort> tag = database.getTag(tagNumber, JExifShort.class);
        System.out.println(String.format("%s", tag));

        JExifValueFactory fac = new JExifValueFactory();
        JExifValue<JExifShort> value = fac.createValue(new byte[]{}, JExifShort.class);
        JExifEntry<JExifShort> entry = new JExifEntry<>(tag, value);
        System.out.println(String.format("%s", entry));

        JExifReaderFactory jExifReaderFactory = new JExifReaderFactory();
        JExifReader reader = jExifReaderFactory.createJExifReader();

        Path imgDir = Paths.get("src/test/resources/img/");
        imgDir = imgDir.normalize();
        if (!(Files.exists(imgDir) && Files.isDirectory(imgDir) && Files.isReadable(imgDir))) {
            return;
        }
        DirectoryStream<Path> dir = Files.newDirectoryStream(imgDir, new ImagePathFilter());
        for (Path p : dir) {
            System.out.println(String.format("Exif for: %s", p.toAbsolutePath().toString()));
            reader.readExifData(p);
        }
    }

    private final static class ImagePathFilter implements DirectoryStream.Filter<Path> {

        @Override
        public boolean accept(Path entry) throws IOException {
            return entry.getFileName().toString().endsWith("jpg");
        }
    }
}
