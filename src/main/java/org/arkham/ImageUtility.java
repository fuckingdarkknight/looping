package org.arkham;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageUtility {
    static String getExtension(final Path path) {
        final String filename = path.getFileName().toString();
        final int extIdx = filename.lastIndexOf('.') + 1;

        return filename.substring(extIdx);
    }

    static Path changeExtension(final Path path, final String newExt) {
        String filename = path.getFileName().toString();
        final int extIdx = filename.lastIndexOf('.');
        filename = filename.substring(0, extIdx);
        final long ct = System.currentTimeMillis();
        filename = filename + "." + ct + "." + newExt;

        return Paths.get(filename);
    }
}
