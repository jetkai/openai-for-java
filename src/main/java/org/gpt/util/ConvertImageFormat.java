package org.gpt.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class ConvertImageFormat {

    /**
     * Required format for OpenAI
     */
    public static Path convertPngToRGBA(Path inputPath) {
        try {
            // Read in the input PNG file
            File input = inputPath.toFile();
            String fileName = input.getName();
            BufferedImage inputImage = ImageIO.read(input);

            // Create a new BufferedImage with RGBA color space
            BufferedImage outputImage = new BufferedImage(
                    inputImage.getWidth(),
                    inputImage.getHeight(),
                    BufferedImage.TYPE_INT_ARGB
            );

            // Copy the RGB data from the input image to the output image
            outputImage.getGraphics().drawImage(inputImage, 0, 0, null);

            // Write the output image to a PNG file
            File output = File.createTempFile(fileName + "-" + "rgba", ".png");
            ImageIO.write(outputImage, "png", output);
            return output.toPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
