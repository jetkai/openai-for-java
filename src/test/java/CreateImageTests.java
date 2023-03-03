import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.data.image.ImageData;
import io.github.jetkai.openai.api.data.image.ImageResponseData;
import io.github.jetkai.openai.util.ApiKeyFileData;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.net.URI;

import static io.github.jetkai.openai.util.ReadApiKeyFromFile.getApiKeyFromFile;
import static org.junit.jupiter.api.Assertions.*;

public class CreateImageTests {

    private final ApiKeyFileData keyData = getApiKeyFromFile();
    private final OpenAI openAI = new OpenAI();

    @Test
    void createImageTest() {

        assertNotNull(keyData);

        openAI.setApiKey(keyData.getApiKey());
        openAI.setOrganization(keyData.getOrganization());

        //Completion Data, ready to send to the OpenAI Api
        ImageData image = new ImageData();
        image.setPrompt("A cute baby sea otter");
        image.setN(2);
        image.setSize("1024x1024");

        ImageResponseData data = openAI.createImage(image).asData();

        assertFalse(data.getData().isEmpty());
    }

    @Test
    void createImageUrlsTest() {

        assertNotNull(keyData);

        openAI.setApiKey(keyData.getApiKey());
        openAI.setOrganization(keyData.getOrganization());

        //Completion Data, ready to send to the OpenAI Api
        ImageData image = new ImageData();
        image.setPrompt("A cute baby sea otter");
        image.setN(2);
        image.setSize("1024x1024");

        URI[] imageUris = openAI.createImage(image).asUriArray();
        assertNotNull(imageUris);
        assertNotEquals(0, imageUris.length);
    }

    @Test
    void createImageAwtTest() {

        assertNotNull(keyData);

        openAI.setApiKey(keyData.getApiKey());
        openAI.setOrganization(keyData.getOrganization());

        //Completion Data, ready to send to the OpenAI Api
        ImageData image = new ImageData();
        image.setPrompt("A cute baby sea otter");
        image.setN(2);
        image.setSize("1024x1024");

        Image[] images = openAI.createImage(image).asImageArray();
        assertNotNull(images);
        assertNotEquals(0, images.length);
    }

}
