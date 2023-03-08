import io.github.jetkai.openai.util.ConvertImageFormat;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class ConvertImageFormatTest {

    @Test
    public void testConvertPngToRGBA() {
        // Test converting a valid PNG file to RGBA format
        Path inputPath = imagePath();
        Path outputPath = ConvertImageFormat.convertPngToRGBA(inputPath);
        assertTrue(outputPath.toFile().exists(), "Output file should exist");

        // Test handling an invalid input file
        Path badImagePath = Path.of("otter-bad-path.png");
        assertThrows(RuntimeException.class, () -> ConvertImageFormat.convertPngToRGBA(badImagePath),
                "Invalid input file should not throw an exception");
    }

    private Path imagePath() {
        URL imageUrl = getClass().getResource("otter.png");
        assertNotNull(imageUrl, "Test image not found: " + "otter.png");
        try {
            return Paths.get(imageUrl.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}