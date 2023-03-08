package examples;

import io.github.jetkai.openai.api.CreateCompletion;
import io.github.jetkai.openai.api.CreateTranslation;
import io.github.jetkai.openai.api.data.completion.CompletionData;
import io.github.jetkai.openai.openai.OpenAI;

import java.util.Optional;

/**
 * ExampleTranslation
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 05/03/2023}
 *
 * <p>
 * Note - This is just a test class, it is recommended to import this project as a library
 * <p>
 * You can get a free API key from <a href="https://platform.openai.com/account/api-keys">here</a>
 */
final class ExampleTranslation {

    public static void main(String[] args) {
        //Initialize ExampleTranslation class
        ExampleTranslation translation = new ExampleTranslation();

        //This is the language we want to translate our audio file to
        String toLanguage = "French";

        //Example audio file that we are going to upload to OpenAI to be translated
        String message = "Hello, how are you today?";

        //Response from OpenAI with the translated string
        String response = translation.communicate(message, toLanguage);

        //Print the translation to the console
        System.out.println(response);
    }

    private String communicate(String message, String toLanguage) {
        //TranslationData, ready to send to the OpenAI API
        CompletionData translationData = CompletionData.translation(message, toLanguage);

        OpenAI openAI = OpenAI.builder()
                .setApiKey(System.getenv("OPEN_AI_API_KEY"))
                .createTranslation(translationData)
                .build()
                //Sends the request to OpenAI's endpoint & parses the response data
                .sendRequest();

        //Call the CreateTranslation API from OpenAI & create instance
        Optional<CreateTranslation> createTranslation = openAI.translation();

        //Return as text, do not replace \n or ascii characters
        return createTranslation.map(CreateCompletion::asText).orElse(null);
    }

}
