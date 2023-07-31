package org.arkham;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;

public class ImageWriter {
    void write(final String ext, final BufferedImage img, final Path output) throws IOException {
        ImageIO.write(img, ext, output.toFile());
    }
}
