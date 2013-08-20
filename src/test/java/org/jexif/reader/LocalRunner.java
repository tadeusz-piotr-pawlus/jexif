package org.jexif.reader;

import org.jexif.api.JExifException;
import org.jexif.reader.buffer.impl.ExtensionBasedBufferProvider;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LocalRunner {

    public static void main(String[] args) throws JExifException, IOException {
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
