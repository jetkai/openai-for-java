package examples;

import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.CreateImage;
import io.github.jetkai.openai.api.data.image.ImageData;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;

/**
 * ExampleImageCreation
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.1
 * {@code - 05/03/2023}
 * @since 1.0.0
 * {@code - 05/03/2023}
 *
 * <p>
 * Note - This is just a test class, it is recommended to import this project as a library
 * and then call OpenAI openAI = new OpenAI("YOUR_API_KEY", "YOUR_ORGANIZATION");
 * </p>
 */
public class ExampleImageCreation {

    /*
     * You can get a free API key from https://platform.openai.com/account/api-keys
     * private final OpenAI openAI = new OpenAI("YOUR_API_KEY");
     * private final OpenAI openAI = new OpenAI("YOUR_API_KEY", "YOUR_ORGANIZATION");
     */
    //private final OpenAI openAI = new OpenAI(System.getenv("OPEN_AI_API_KEY"));

    public static void main(String[] args) throws IOException {
        //Initialize ExampleImageCreation class
        ExampleImageCreation imageCreation = new ExampleImageCreation();

        //A text description of the desired image(s). The maximum length is 1000 characters
        String description = "A picture of a red panda with glasses, drawn as a cartoon.";

        //The number of images to generate. Must be between 1 and 10.
        int amount = 1;

        //The size of the generated images. Must be one of 256x256, 512x512, or 1024x1024
        String size = "1024x1024";

        URI[] imageLinks = imageCreation.communicate(description, amount, size);

        for(URI imageLink : imageLinks) {
            System.out.println("Opening URI ["+imageLink+"] in the web browser.");
            Desktop.getDesktop().browse(imageLink);
        }
    }

    private URI[] communicate(String imageDescription, int numberOfImages, String size) {
        //Alternatively can use ImageData.create(imageDescription, numberOfImages, size);
        ImageData imageData = ImageData.create()
                //A text description of the desired image(s). The maximum length is 1000 characters
                .setPrompt(imageDescription)
                //The number of images to generate. Must be between 1 and 10.
                .setN(numberOfImages)
                //The size of the generated images. Must be one of 256x256, 512x512, or 1024x1024
                .setSize(size);

        OpenAI openAI = OpenAI.builder()
                .setApiKey(System.getenv("OPEN_AI_API_KEY"))
                .createImage(imageData)
                .build();

        //Sends the request to OpenAI's endpoint & parses the response data
        openAI.sendRequest();

        //Call the CreateImage API from OpenAI & create instance
        Optional<CreateImage> createImage = openAI.image();

        //Gather the URI(s) from the API response
        return createImage.map(CreateImage::asUriArray).orElse(null);
    }

}
