import io.github.jetkai.chatgpt.ChatGPT;
import io.github.jetkai.chatgpt.api.data.image.ImageResponseData;
import io.github.jetkai.chatgpt.api.data.image.variation.ImageVariationData;
import io.github.jetkai.chatgpt.util.ApiKeyFileData;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

import static io.github.jetkai.chatgpt.util.ReadApiKeyFromFile.getApiKeyFromFile;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateImageVariationTest {

    @Test
    void createImageVariationTest() {
        ApiKeyFileData keyData = getApiKeyFromFile();

        assertNotNull(keyData);

        ChatGPT gpt = new ChatGPT(keyData.getApiKey(), keyData.getOrganization());

        //Completion Data, ready to send to the ChatGPT Api
        ImageVariationData image = new ImageVariationData();

        Path imagePath = null;
        URL imageUrl = CreateImageVariationTest.class.getResource("otter.png");
        try {
            if (imageUrl != null) {
                imagePath = Path.of(imageUrl.toURI());
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(imagePath);

        image.setImage(imagePath);

        image.setN(2);
        image.setSize("1024x1024");

        ImageResponseData data = gpt.createImageVariationResponse(image);

        assertFalse(data.getData().isEmpty());
    }

}
