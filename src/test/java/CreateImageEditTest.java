import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.CreateImageEdit;
import io.github.jetkai.openai.api.data.image.ImageResponseData;
import io.github.jetkai.openai.api.data.image.edit.ImageEditData;
import io.github.jetkai.openai.util.ApiKeyFileData;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;

import static io.github.jetkai.openai.util.ReadApiKeyFromFile.getApiKeyFromFile;
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
        //Grab API Key from .json file
        ApiKeyFileData keyData = getApiKeyFromFile();
        assertNotNull(keyData);

        //Create OpenAI instance using API key loaded from the .json file (example)
        OpenAI openAI = new OpenAI(keyData.getApiKey(), keyData.getOrganization());

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
        ImageEditData imageData = new ImageEditData();

        //The image to edit. Must be a valid PNG file, less than 4MB, and square.
        //If mask is not provided, image must have transparency, which will be used as the mask.
        imageData.setImage(imagePath);

        //An additional image whose fully transparent areas (e.g. where alpha is zero) indicate where
        //image should be edited. Must be a valid PNG file, less than 4MB, and have the same dimensions as image.
        imageData.setMask(maskPath);

        //A text description of the desired image(s). The maximum length is 1000 characters.
        imageData.setPrompt("A cute baby sea otter wearing a beret");

        //The number of images to generate. Must be between 1 and 10.
        imageData.setN(2);

        //The size of the generated images. Must be one of 256x256, 512x512, or 1024x1024.
        imageData.setSize("1024x1024");

        //Call the CreateImageEdit API from OpenAI & create instance
        CreateImageEdit createImageEdit = openAI.createImageEdit(imageData);

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

        //String List example (contains all the image urls)
        List<String> imageList = createImageEdit.asStringList();
        assertNotNull(imageList);

        //URIArray example (contains all the image urls)
        URI[] uriArray = createImageEdit.asUriArray();
        assertNotNull(uriArray);

        //Json example
        String json = createImageEdit.asJson();
        assertNotNull(json);
        assertFalse(json.isEmpty());
    }

}
