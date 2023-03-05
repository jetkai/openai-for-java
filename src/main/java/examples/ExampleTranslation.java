package examples;

import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.CreateTranslation;
import io.github.jetkai.openai.api.data.translation.TranslationData;

/**
 * ExampleTranslation
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * {@code - 05/03/2023}
 * @since 1.0.0
 * {@code - 05/03/2023}
 *
 * <p>
 * Note - This is just a test class, it is recommended to import this project as a library
 * and then call OpenAI openAI = new OpenAI("YOUR_API_KEY", "YOUR_ORGANIZATION");
 * </p>
 */
public class ExampleTranslation {

    /*
     * You can get a free API key from https://platform.openai.com/account/api-keys
     * private final OpenAI openAI = new OpenAI("YOUR_API_KEY");
     * private final OpenAI openAI = new OpenAI("YOUR_API_KEY", "YOUR_ORGANIZATION");
     */
    private final OpenAI openAI = new OpenAI(System.getenv("OPEN_AI_API_KEY"));

    public static void main(String[] args) {
        //Initialize ExampleTranslationFromAudioFile class
        ExampleTranslation translateFromAudioFile = new ExampleTranslation();

        //This is the language we want to translate our audio file to
        String toLanguage = "French";

        //Example audio file that we are going to upload to OpenAI to be translated
        String message = "Hello, how are you today?";

        //Response from OpenAI with the translated string
        String response = translateFromAudioFile.communicate(message, toLanguage);

        //Print the translation to the console
        System.out.println(response);
    }

    private String communicate(String message, String toLanguage) {
        //TranslationData, ready to send to the OpenAI API
        TranslationData audioTranslationData = TranslationData.simplified(message, toLanguage);

        //Call the CreateTranslation API from OpenAI & create instance
        CreateTranslation createTranslation = openAI.createTranslation(audioTranslationData);

        //Return as text, do not replace \n or ascii characters
        return createTranslation.asText();
    }

}
