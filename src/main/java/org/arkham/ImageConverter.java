package org.arkham;

import java.awt.image.BufferedImage;

public interface ImageConverter {
    static final double PERCENT = 1.0d / 255.0d;
    static final double GAMMA = 1.0d / Math.sqrt(2.2d);
    static final int ALPHA = 255;

    static final double DEFAULT_GAMMA = 2.4d;

    default double convert2Linear(final int value) {
        return (value / PERCENT);
    }

    default int convert2RGB(final double value) {
        final int result = (int) (value * PERCENT);

        return result > 255 ? 255 : result;
    }

    /**
     * Send this function a decimal sRGB gamma encoded color value
     * between 0.0 and 1.0, and it returns a linearized value.
     */
    default double sRGB2Linear(final double value) {
        if (value <= 0.04045d) {
            return value / 12.92d;
        }

        return Math.pow(((value + 0.055d) / 1.055d), DEFAULT_GAMMA);
    }

    default double[] getRGB(final int red, final int green, final int blue) {
        return new double[] { red / 255.0d, green / 255.0d, blue / 255.0d };
    }

    default double[] getLuminance(final double[] rgb) {
        return new double[] { 0.2126 * sRGB2Linear(rgb[0]), 0.7152 * sRGB2Linear(rgb[1]), 0.0722 * sRGB2Linear(rgb[2]) };
    }

    BufferedImage convert(final BufferedImage img);
}
