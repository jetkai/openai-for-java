import io.github.jetkai.chatgpt.ChatGPT;
import io.github.jetkai.chatgpt.api.data.image.ImageData;
import io.github.jetkai.chatgpt.util.ApiKeyFileData;
import org.junit.jupiter.api.Test;

import static io.github.jetkai.chatgpt.util.ReadApiKeyFromFile.getApiKeyFromFile;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateImageUrlsTest {

    @Test
    void createImageUrlsTest() {
        ApiKeyFileData keyData = getApiKeyFromFile();

        assertNotNull(keyData);

        ChatGPT gpt = new ChatGPT(keyData.getApiKey(), keyData.getOrganization());

        //Completion Data, ready to send to the ChatGPT Api
        ImageData image = new ImageData();
        image.setPrompt("A cute baby sea otter");
        image.setN(2);
        image.setSize("1024x1024");

        String[] imageUrls = gpt.createImageUrls(image);
        assertNotNull(imageUrls);
        assertNotEquals(0, imageUrls.length);
    }

}
