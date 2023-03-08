import io.github.jetkai.openai.api.CreateImageEdit;
import io.github.jetkai.openai.api.data.image.edit.ImageEditData;
import io.github.jetkai.openai.api.data.image.response.ImageResponseData;
import io.github.jetkai.openai.openai.OpenAI;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
        //Grab OpenAI API key from system environment variables (gradle.properties)
        String apiKey = System.getenv("OPEN_AI_API_KEY");
        String organization = System.getenv("OPEN_AI_ORGANIZATION");
        assertNotNull(apiKey);
        assertNotNull(organization);

        //Example image file that we are going to upload to OpenAI
        URL imageUrl = CreateImageEditTest.class.getResource("otter.png");
        Path imagePath = null;
        try {
            if (imageUrl != null) {
                imagePath = Path.of(imageUrl.toURI());
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        //Example mask file that we are going to upload to OpenAI
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

        //ImageEditData, ready to send to the OpenAI API
        ImageEditData imageData = ImageEditData.builder()
                //The image to edit. Must be a valid PNG file, less than 4MB, and square.
                //If mask is not provided, image must have transparency, which will be used as the mask.
                .setImage(imagePath)
                //An additional image whose fully transparent areas (e.g. where alpha is zero) indicate where
                //image should be edited. Must be a valid PNG file, less than 4MB, and have the same dimensions as image.
                .setMask(maskPath)
                //A text description of the desired image(s). The maximum length is 1000 characters.
                .setPrompt("A cute baby sea otter wearing a beret")
                //The number of images to generate. Must be between 1 and 10.
                .setN(2)
                //The size of the generated images. Must be one of 256x256, 512x512, or 1024x1024.
                .setSize("256x256")
                .build();

        //Create OpenAI instance using API key & organization
        //Organization is optional
        OpenAI openAI = OpenAI.builder()
                .setApiKey(apiKey)
                .setOrganization(organization)
                .createImageEdit(imageData)
                .build()
                //Finally, send our request to the API, this initiates the request (after .build())
                .sendRequest();

        //Call the CreateImageEdit API from OpenAI & create instance
        Optional<CreateImageEdit> optionalCreateImageEdit = openAI.imageEdit();
        assertFalse(optionalCreateImageEdit.isEmpty());

        CreateImageEdit createImageEdit = optionalCreateImageEdit.get();

        //Data structure example
        ImageResponseData imageResponse = createImageEdit.asData();
        assertNotNull(imageResponse);

        //Grabs the first image in the array, if your "setN" is higher than 1, then use imageArray
        Image image = createImageEdit.asImage();
        assertNotNull(image);

        //Array of images, size depends on the "setN" value
        Image[] imageArray = createImageEdit.asImageArray();
        assertNotNull(imageArray);
        assertNotEquals(0, imageArray.length);

        //Grabs the first image in the array, if your "setN" is higher than 1, then use imageArray
        assertNotNull(createImageEdit.asImage());
        //Array of images, size depends on the "setN" value
        assertNotNull(createImageEdit.asImageArray());
        //URIArray example (contains all the image urls)
        assertNotNull(createImageEdit.asUriArray());
        //String List example (contains all the image urls)
        assertNotNull(createImageEdit.asStringList());

        //Json example
        String json = createImageEdit.asJson();
        assertNotNull(json);
        assertFalse(json.isEmpty());
    }

}
