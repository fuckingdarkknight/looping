package org.arkham;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;

public class ImageReader {
    private final boolean verbose;

    ImageReader() {
        this(false);
    }

    ImageReader(final boolean verbose) {
        this.verbose = verbose;
    }

    public BufferedImage read(final Path path) throws IOException {
        final File file = path.toFile();
        if (file.exists()) {
            return ImageIO.read(file);
        }

        throw new IOException(String.format("File %s does not exists", file.toString()));
    }
}
