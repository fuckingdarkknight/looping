package org.arkham;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "looping", mixinStandardHelpOptions = true)
public class Convert implements Runnable {
    @Parameters(paramLabel = "<filename>", description = "Filename to convert")
    String filename;
    @Option(names = { "-l", "--linear" }, required = false, defaultValue = "gamma")
    String transformation;
    @Option(names = { "-v", "--verbose" }, required = false, defaultValue = "false")
    boolean verbose;

    @Override
    public void run() {
        try {
            final Path img = Paths.get(filename);
            final String ext = ImageUtility.getExtension(img);

            final ImageReader reader = new ImageReader();
            final BufferedImage bi = reader.read(img);

            ImageConverter ic = null;
            if ("gamma".equals(transformation)) {
                ic = new ImageConverterGamma();
            } else {
                ic = new ImageConverterLinear();
            }

            final BufferedImage nbi = ic.convert(bi);

            final ImageWriter writer = new ImageWriter();
            writer.write(ext, nbi, ImageUtility.changeExtension(img, ext));
        } catch (final IOException e) {
            System.out.printf("Exception %s, please call Alex!\n", e.toString());
        }
    }
}
