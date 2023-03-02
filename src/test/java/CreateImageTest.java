import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.data.image.ImageData;
import io.github.jetkai.openai.api.data.image.ImageResponseData;
import io.github.jetkai.openai.util.ApiKeyFileData;
import org.junit.jupiter.api.Test;

import static io.github.jetkai.openai.util.ReadApiKeyFromFile.getApiKeyFromFile;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateImageTest {

    @Test
    void createImageTest() {
        ApiKeyFileData keyData = getApiKeyFromFile();

        assertNotNull(keyData);

        OpenAI openAI = new OpenAI(keyData.getApiKey(), keyData.getOrganization());

        //Completion Data, ready to send to the OpenAI Api
        ImageData image = new ImageData();
        image.setPrompt("A cute baby sea otter");
        image.setN(2);
        image.setSize("1024x1024");

        ImageResponseData data = openAI.createImageResponse(image);

        assertFalse(data.getData().isEmpty());
    }

}
