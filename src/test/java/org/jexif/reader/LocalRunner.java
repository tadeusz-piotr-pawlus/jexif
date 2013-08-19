package org.jexif.reader;

import org.jexif.api.*;
import org.jexif.api.type.JExifShort;
import org.jexif.reader.buffer.impl.ExtensionBasedBufferProvider;
import org.jexif.tags.database.api.JExifTagsDatabase;
import org.jexif.tags.database.impl.InMemoryJExifTagsDatabase;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LocalRunner {

    public static void main(String[] args) throws JExifException, IOException {
        JExifTagsDatabase database = new InMemoryJExifTagsDatabase();
        JExifTagNumber tagNumber = new JExifTagNumber("a405", 16);
        JExifTag tag = database.getTag(tagNumber, JExifShort.instance);
        System.out.println(String.format("%s", tag));

        JExifValueFactory fac = new JExifValueFactory();
        JExifValue value = fac.createValue(new byte[]{}, JExifShort.instance);
        JExifEntry entry = new JExifEntry(tag, value);
        System.out.println(String.format("%s", entry));

        JExifReaderFactory jExifReaderFactory = new JExifReaderFactory();
        JExifReader reader = jExifReaderFactory.createJExifReader();

        Path imgDir = Paths.get("src/test/resources/image/");
        imgDir = imgDir.normalize();
        if (!(Files.exists(imgDir) && Files.isDirectory(imgDir) && Files.isReadable(imgDir))) {
            return;
        }
        DirectoryStream<Path> dir = Files.newDirectoryStream(imgDir, new ImagePathFilter());

        ExtensionBasedBufferProvider ebbp = new ExtensionBasedBufferProvider();

        for (Path p : dir) {
            try {
//            Path p = Paths.get("/home/keef/IdeaProjects/jexif/src/test/resources/image/fujifilm-finepix40i.jpg");
                System.out.println(String.format("Exif for: %s", p.toAbsolutePath().toString()));
                ByteBuffer image = ebbp.getByteBuffer(p);
                reader.readExifData(image);
            } catch (JExifReaderException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private final static class ImagePathFilter implements DirectoryStream.Filter<Path> {

        @Override
        public boolean accept(Path entry) throws IOException {
            return entry.getFileName().toString().toLowerCase().endsWith("jpg");
        }
    }
}
