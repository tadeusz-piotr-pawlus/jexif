package org.jexif.reader;

import org.jexif.api.JExifException;
import org.jexif.api.JExifProvider;
import org.jexif.api.reader.JExifReader;
import org.jexif.api.reader.JExifReaderData;
import org.jexif.api.reader.JExifReaderException;
import org.jexif.api.reader.JExifReaderFactory;
import org.jexif.reader.oop.buffer.api.BufferProvider;
import org.jexif.reader.oop.buffer.impl.ExtensionBasedBufferProvider;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LocalReader {

    private final BufferProvider bufferProvider;
    private JExifReader reader;

    public LocalReader() throws JExifException {
        JExifProvider provider = JExifProvider.provider();
        JExifReaderFactory factory = provider.createJExifReaderFactory();
        this.reader = factory.createJExifReader();
        this.bufferProvider = new ExtensionBasedBufferProvider();
    }

    public JExifReaderData readImage(Path p) throws JExifReaderException {
        ByteBuffer buffer = getBufferProvider().getByteBuffer(p);
        return getReader().readExifData(buffer);
    }

    private BufferProvider getBufferProvider() {
        return this.bufferProvider;
    }

    private JExifReader getReader() {
        return this.reader;
    }

    public static void main(String[] args) throws IOException, JExifException {
        LocalReader reader = new LocalReader();
        Path imgDir = getImageDirectory(args);

        DirectoryStream<Path> dir = Files.newDirectoryStream(imgDir, new ImagePathFilter());
        for (Path p : dir) {
            try{
            System.out.println(String.format("Exif for: %s", p.toAbsolutePath().toString()));
//            Path p = Paths.get("/home/keef/IdeaProjects/jexif/src/test/resources/image/fujifilm-finepix40i.jpg");
            JExifReaderData data = reader.readImage(p);
            System.out.println(String.format("All tags in file: %s.", data.getTags().size()));
            }catch(JExifException ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    private static Path getImageDirectory(String[] args) throws JExifException {
        if(args.length == 0){
            return Paths.get("src/test/resources/image/");
        }
        Path imgDir = Paths.get(args[0]);
        if (!(Files.exists(imgDir) && Files.isDirectory(imgDir) && Files.isReadable(imgDir))) {
            throw new JExifException("Missing path. Please provide path to dir with images or to image directly.");
        }
        return imgDir;
    }

    private final static class ImagePathFilter implements DirectoryStream.Filter<Path> {

        @Override
        public boolean accept(Path entry) throws IOException {
            return entry.getFileName().toString().toLowerCase().endsWith("jpg");
        }
    }
}
