import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.CreateImage;
import io.github.jetkai.openai.api.data.image.ImageData;
import io.github.jetkai.openai.api.data.image.ImageResponseData;
import io.github.jetkai.openai.util.ApiKeyFileData;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.net.URI;
import java.util.List;

import static io.github.jetkai.openai.util.ReadApiKeyFromFile.getApiKeyFromFile;
import static org.junit.jupiter.api.Assertions.*;

public class CreateImageTests {

    //Create OpenAI instance

    @Test
    void createImageTest() {
        //Grab API Key from .json file
        ApiKeyFileData keyData = getApiKeyFromFile();
        assertNotNull(keyData);

        //Create OpenAI instance
        OpenAI openAI = new OpenAI();
        //Set the API key (from .json file)
        openAI.setApiKey(keyData.getApiKey());
        //Set the organization (from .json file)
        openAI.setOrganization(keyData.getOrganization());

        //ImageData, ready to send to the OpenAI API
        ImageData imageData = new ImageData();

        //A text description of the desired image(s). The maximum length is 1000 characters
        imageData.setPrompt("A cute baby sea otter");

        //The number of images to generate. Must be between 1 and 10.
        imageData.setN(2);

        //The size of the generated images. Must be one of 256x256, 512x512, or 1024x1024
        imageData.setSize("1024x1024");

        //Call the CreateImage API from OpenAI & create instance
        CreateImage createImage = openAI.createImage(imageData);

        //Grabs the first image in the array, if your "setN" is higher than 1, then use imageArray
        Image image = createImage.asImage();
        assertNotNull(image);

        //Array of images, size depends on the "setN" value
        Image[] imageArray = createImage.asImageArray();
        assertNotNull(imageArray);
        assertNotEquals(0, imageArray.length);

        //String List example (contains all the image urls)
        List<String> imageList = createImage.asStringList();
        assertNotNull(imageList);

        //URIArray example (contains all the image urls)
        URI[] uriArray = createImage.asUriArray();
        assertNotNull(uriArray);

        //Data structure example
        ImageResponseData responseData = createImage.asData();
        assertNotNull(responseData);

        //Json example
        String json = createImage.asJson();
        assertNotNull(json);
        assertFalse(json.isEmpty());
    }

}
