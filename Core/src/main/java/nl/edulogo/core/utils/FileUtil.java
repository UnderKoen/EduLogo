package nl.edulogo.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Created by Under_Koen on 25/05/2018.
 */
public class FileUtil {
    private FileUtil() {
    }

    public static File getRunningDir() {
        try {
            return new File(FileUtil.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile();
        } catch (URISyntaxException e) {
            return null;
        }
    }

    public static String getAllContent(InputStream inputStream) {
        StringBuilder result = new StringBuilder();
        try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }
        }
        return result.toString();
    }

    public static String getContent(File file) {
        try {
            return getAllContent(new FileInputStream(file));
        } catch (Exception ignored) {
            return null;
        }
    }

    public static InputStream getResource(String name) {
        return ClassLoader.getSystemClassLoader().getResourceAsStream(name);
    }

    public static String getResourceContent(String name) {
        return getAllContent(getResource(name));
    }
}
