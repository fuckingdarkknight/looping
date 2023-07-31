package org.arkham;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageUtilityTest {
    public static void main(final String[] args) {
        final String filename = "toto.jpg";
        final Path path = Paths.get(filename);

        System.out.println(ImageUtility.getExtension(path));

        final Path newPath = ImageUtility.changeExtension(path, "jpg");
        System.out.println(newPath);
    }
}
