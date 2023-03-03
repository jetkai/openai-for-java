import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.CreateImageVariation;
import io.github.jetkai.openai.api.data.image.ImageResponseData;
import io.github.jetkai.openai.api.data.image.variation.ImageVariationData;
import io.github.jetkai.openai.util.ApiKeyFileData;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;

import static io.github.jetkai.openai.util.ReadApiKeyFromFile.getApiKeyFromFile;
import static org.junit.jupiter.api.Assertions.*;

/**
 * CreateImageVariationTest
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * @created 02/03/2023
 * @last-update 03/03/2023
 */
public class CreateImageVariationTest {

    @Test
    void createImageVariationTest() {
        //Grab API Key from .json file
        ApiKeyFileData keyData = getApiKeyFromFile();
        assertNotNull(keyData);

        //Create OpenAI instance using API key loaded from the .json file (example)
        OpenAI openAI = new OpenAI(keyData.getApiKey(), keyData.getOrganization());

        //Example image file that we are going to upload to OpenAI
        URL imageUrl = CreateImageVariationTest.class.getResource("otter.png");
        Path imagePath = null;
        try {
            if (imageUrl != null) {
                imagePath = Path.of(imageUrl.toURI());
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(imagePath);

        //ImageVariationData, ready to send to the OpenAI API
        ImageVariationData image = new ImageVariationData();

        //Set the path for the image file
        image.setImage(imagePath);

        //Set the number of images to generate. Must be between 1 and 10.
        image.setN(2);

        //Set the size of the generated images. Must be one of 256x256, 512x512, or 1024x1024.
        image.setSize("1024x1024");

        //Call the CreateTranscription API from OpenAI & create instance
        CreateImageVariation createImageVariation = openAI.createImageVariation(image);

        //String List example (contains all the image urls)
        List<String> stringList = createImageVariation.asStringList();
        assertNotNull(stringList);
        assertFalse(stringList.isEmpty());

        //URI array example (contains all the image urls)
        URI[] uriArray = createImageVariation.asUriArray();
        assertNotNull(uriArray);
        assertNotEquals(0, uriArray.length);

        //Data structure example
        ImageResponseData responseData = createImageVariation.asData();
        assertNotNull(responseData);

        //Json example
        String json = createImageVariation.asJson();
        assertNotNull(json);
        assertFalse(json.isEmpty());
    }

}
