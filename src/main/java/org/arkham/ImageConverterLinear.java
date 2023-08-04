package org.arkham;

import java.awt.image.BufferedImage;

public class ImageConverterLinear implements ImageConverter {
    private final boolean verbose;

    ImageConverterLinear() {
        this(false);
    }

    ImageConverterLinear(final boolean verbose) {
        this.verbose = verbose;
    }

    @Override
    public BufferedImage convert(final BufferedImage img) {
        final int width = img.getWidth();
        final int height = img.getHeight();

        System.out.println("Reading image, dimensions are : " + width + "/" + height);
        int max_alpha = -1;
        int min_alpha = 256;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = img.getRGB(x, y);

                // Alpha channel
                int alpha = p >> 24 & 0xff;
                max_alpha = Math.max(max_alpha, alpha);
                min_alpha = Math.min(min_alpha, alpha);

                final int red = p >> 16 & 0xff;
                final int green = p >> 8 & 0xff;
                final int blue = p & 0xff;

                // final float alpha_p = convert2Linear(alpha);
                final double alpha_p = alpha / ALPHA;
                // if (Float.compare(alpha_p, 1.0f) > 0) {
                // alpha_p = 1.0f;
                // }
                final double red_p = convert2Linear(red) * alpha_p;
                final double green_p = convert2Linear(green) * alpha_p;
                final double blue_p = convert2Linear(blue) * alpha_p;

                // Luminance
                final double lr = 0.2126d * red_p;
                final double lg = 0.7152d * green_p;
                final double lb = 0.0722d * blue_p;
                // final double l = lr + lg + lb;
                // final double lrg = 1.0d / lg;
                // final double lrb = 1.0d / lb;

                // final int a = (p >> 24) & 0xff;
                // final int r = convert2RGB(red_p);
                // Augmente les niveaux GB du niveau de rouge
                final int r = 0;
                final int g = convert2RGB(green_p + lr);
                // final int g = convert2RGB(green_p);
                final int b = convert2RGB(blue_p + lr);
                alpha = ALPHA;

                // set new RGB
                p = alpha << 24 | r << 16 | g << 8 | b << 0;
                img.setRGB(x, y, p);
            }
        }

        System.out.println(String.format("Alpha min=%d max=%d", min_alpha, max_alpha));

        return img;
    }
}
