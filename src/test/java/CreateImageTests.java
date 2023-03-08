import io.github.jetkai.openai.api.CreateImage;
import io.github.jetkai.openai.api.data.image.ImageData;
import io.github.jetkai.openai.api.data.image.response.ImageResponseData;
import io.github.jetkai.openai.openai.OpenAI;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateImageTests {

    @Test
    void createImageTest() {
        //Grab OpenAI API key from system environment variables (gradle.properties)
        String apiKey = System.getenv("OPEN_AI_API_KEY");
        String organization = System.getenv("OPEN_AI_ORGANIZATION");
        assertNotNull(apiKey);
        assertNotNull(organization);

        //ImageData, ready to send to the OpenAI API
        ImageData imageData = ImageData.builder()
                //A text description of the desired image(s). The maximum length is 1000 characters
                .setPrompt("A cute baby sea otter")
                //The number of images to generate. Must be between 1 and 10.
                .setN(2)
                //The size of the generated images. Must be one of 256x256, 512x512, or 1024x1024
                .setSize("256x256")
                .build();

        //Create OpenAI instance using API key & organization
        //Organization is optional
        OpenAI openAI = OpenAI.builder()
                .setApiKey(apiKey)
                .setOrganization(organization)
                .createImage(imageData)
                .build()
                //Finally, send our request to the API, this initiates the request (after .build())
                .sendRequest();

        assertNotNull(openAI);

        //Call the CreateImage API from OpenAI & create instance
        Optional<CreateImage> optionalCreateImage = openAI.image();
        assertFalse(optionalCreateImage.isEmpty());

        CreateImage createImage = optionalCreateImage.get();

        //Grabs the first image in the array, if your "setN" is higher than 1, then use imageArray
        assertNotNull(createImage.asImage());
        //Array of images, size depends on the "setN" value
        assertNotNull(createImage.asImageArray());
        //URIArray example (contains all the image urls)
        assertNotNull(createImage.asUriArray());
        //String List example (contains all the image urls)
        assertNotNull(createImage.asStringList());

        //Data structure example
        ImageResponseData responseData = createImage.asData();
        assertNotNull(responseData);

        //Json example
        String json = createImage.asJson();
        assertNotNull(json);
        assertFalse(json.isEmpty());
    }

}
