package org.arkham;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageConverterTest {
    public static void main(final String[] args) {
        final String[] imgs = { "aurore_boreale.jpg", "chat.jpeg", "chien1.png", "coucher_soleil.jpg", "pere-noel-1.jpg", "rs3.jpg" };
        for (final String image : imgs) {
            try {
                final Path img = Paths.get(image);
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
}
