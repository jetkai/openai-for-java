import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.data.image.ImageResponseData;
import io.github.jetkai.openai.api.data.image.edit.ImageEditData;
import io.github.jetkai.openai.util.ApiKeyFileData;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

import static io.github.jetkai.openai.util.ReadApiKeyFromFile.getApiKeyFromFile;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * CreateImageEditTest
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * @created 02/03/2023
 * @last-update 03/03/2023
 */
public class CreateImageEditTest {

    @Test
    void createImageEditTest() {
        ApiKeyFileData keyData = getApiKeyFromFile();

        assertNotNull(keyData);

        OpenAI openAI = new OpenAI(keyData.getApiKey(), keyData.getOrganization());

        //Completion Data, ready to send to the OpenAI Api
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

        ImageResponseData data = openAI.createImageEdit(image).asData();

        assertFalse(data.getData().isEmpty());
    }

}
