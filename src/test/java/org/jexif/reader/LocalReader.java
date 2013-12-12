package org.jexif.reader;

import org.jexif.api.JExifException;
import org.jexif.api.JExifProvider;
import org.jexif.api.common.JExifTag;
import org.jexif.api.common.JExifTagNumber;
import org.jexif.api.common.JExifValue;
import org.jexif.api.common.type.JExifAscii;
import org.jexif.api.reader.JExifReader;
import org.jexif.api.reader.JExifReaderData;
import org.jexif.api.reader.JExifReaderException;
import org.jexif.api.reader.JExifReaderFactory;
import org.jexif.reader.oop.buffer.impl.CR2BufferProvider;
import org.jexif.reader.oop.buffer.impl.JPEGBufferProvider;
import org.jexif.reader.oop.buffer.impl.SupportedFileTypesProvider;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LocalReader {

    private final SupportedFileTypesProvider supportedFileTypes;
    private JExifReader reader;

    public LocalReader() throws JExifException {
        JExifProvider provider = JExifProvider.provider();
        JExifReaderFactory factory = provider.createJExifReaderFactory();
        this.reader = factory.createJExifReader();
        this.supportedFileTypes = new SupportedFileTypesProvider();
        this.supportedFileTypes.registerBufferProvider(new JPEGBufferProvider());
        this.supportedFileTypes.registerBufferProvider(new CR2BufferProvider());
    }

    public JExifReaderData readImage(Path p) throws JExifReaderException {
        ByteBuffer buffer = getSupportedFileTypes().getByteBuffer(p);
        return getReader().readExifData(buffer);
    }

    private SupportedFileTypesProvider getSupportedFileTypes() {
        return this.supportedFileTypes;
    }

    private JExifReader getReader() {
        return this.reader;
    }

    public static void main(String[] args) throws IOException, JExifException {
        LocalReader reader = new LocalReader();
        Path imgDir = getImageDirectory(args);

        DirectoryStream<Path> dir = Files.newDirectoryStream(imgDir, new ImagePathFilter());
        for (Path p : dir) {
            try {
                System.out.println(String.format("Exif for: %s", p.toAbsolutePath().toString()));
//            Path p = Paths.get("/home/keef/IdeaProjects/jexif/src/test/resources/image/fujifilm-finepix40i.jpg");
                JExifReaderData data = reader.readImage(p);
                System.out.println(String.format("All tags in file: %s.", data.getTagNumbers().size()));
                for (JExifTagNumber tagNumber : data.getTagNumbers()) {
                    JExifValue value = data.getValueFor(tagNumber);
                    JExifTag tag = value.getTag();
                    if (JExifAscii.instance.equals(tag.getType())) {
                        System.out.println(String.format("%s ( %s ): %s", tag, tagNumber, new String(value.getValue(), 0, value.getValue().length - 1, "US-ASCII")));
                    } else {
                        System.out.println(String.format("Skipping tag for tag: %s ( %s )", tag, tagNumber));
                    }
                    assert tagNumber.equals(tag.getTagNumber());
                }
            } catch (JExifException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private static Path getImageDirectory(String[] args) throws JExifException {
        if (args.length == 0) {
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
