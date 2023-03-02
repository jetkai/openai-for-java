import org.gpt.ChatGPT;
import org.gpt.api.data.image.ImageResponseData;
import org.gpt.api.data.image.edit.ImageEditData;
import org.gpt.util.ApiKeyFileData;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

import static org.gpt.util.ReadApiKeyFromFile.getApiKeyFromFile;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateImageEditTest {

    @Test
    void createImageTest() {
        ApiKeyFileData keyData = getApiKeyFromFile();

        assertNotNull(keyData);

        ChatGPT gpt = new ChatGPT(keyData.getApiKey(), keyData.getOrganization());

        //Completion Data, ready to send to the ChatGPT Api
        ImageEditData image = new ImageEditData();

        Path imagePath = null;
        URL imageUrl = CreateImageEditTest.class.getResource("otter.png");
        try {
            if (imageUrl != null) {
                imagePath = Path.of(imageUrl.toURI());
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        URL maskUrl = CreateImageEditTest.class.getResource("otter-mask.png");
        Path maskPath = null;
        try {
            if (maskUrl != null) {
                maskPath = Path.of(maskUrl.toURI());
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(imagePath);
        assertNotNull(maskPath);

        image.setImage(imagePath);
        image.setMask(maskPath);

        image.setPrompt("A cute baby sea otter wearing a beret");
        image.setN(2);
        image.setSize("1024x1024");

        ImageResponseData data = gpt.createImageEditResponse(image);

        assertFalse(data.getData().isEmpty());
    }

}
