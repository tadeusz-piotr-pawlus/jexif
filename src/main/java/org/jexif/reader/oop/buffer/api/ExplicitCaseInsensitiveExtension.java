package org.jexif.reader.oop.buffer.api;

import java.nio.file.Path;

public class ExplicitCaseInsensitiveExtension {

    private final String extension;

    public ExplicitCaseInsensitiveExtension(String extension) {
        this.extension = extension.toLowerCase();
    }

    public boolean supports(String path) {
        return path.toLowerCase().endsWith(getExtension());
    }

    public boolean supports(Path path) {
        return supports(path.toString());
    }

    public String getExtension() {
        return extension;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExplicitCaseInsensitiveExtension that = (ExplicitCaseInsensitiveExtension) o;

        if (extension != null ? !extension.equalsIgnoreCase(that.extension) : that.extension != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return extension != null ? extension.hashCode() : 0;
    }
}
