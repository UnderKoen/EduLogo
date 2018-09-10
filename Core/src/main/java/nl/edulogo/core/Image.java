package nl.edulogo.core;

import java.io.File;

/**
 * Created by Under_Koen on 10/09/2018.
 */
public class Image {
    private File file;

    public Image(File file) {
        this.file = file;
        if (!file.isFile()) throw new IllegalArgumentException("File should be a file.");
        if (!file.exists()) throw new IllegalArgumentException("File should exist.");
        if (!file.canRead()) throw new IllegalArgumentException("Should be able to read file.");
    }

    public File getFile() {
        return file;
    }

    @Override
    public String toString() {
        return "Image{" +
                "file=" + file +
                '}';
    }
}
