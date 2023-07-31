package org.arkham;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageConverterTest {
    public static void main(final String[] args) {
        try {
            final Path img = Paths.get("lemarcien.png");
            final String ext = ImageUtility.getExtension(img);

            final ImageReader reader = new ImageReader();
            final BufferedImage bi = reader.read(img);

            final ImageConverter ic = new ImageConverterGamma();
            final BufferedImage nbi = ic.convert(bi);

            final ImageWriter writer = new ImageWriter();
            writer.write(ext, nbi, ImageUtility.changeExtension(img, ext));
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
