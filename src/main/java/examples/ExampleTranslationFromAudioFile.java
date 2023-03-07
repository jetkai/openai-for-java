package examples;

import io.github.jetkai.openai.api.CreateTranscription;
import io.github.jetkai.openai.api.CreateTranscriptionTranslation;
import io.github.jetkai.openai.api.data.audio.AudioData;
import io.github.jetkai.openai.openai.OpenAI;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Optional;

/**
 * ExampleTranslationFromAudioFile
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 05/03/2023}
 *
 * <p>
 * Note - This is just a test class, it is recommended to import this project as a library
 * and then call OpenAI openAI = new OpenAI("YOUR_API_KEY", "YOUR_ORGANIZATION");
 * </p>
 */
public class ExampleTranslationFromAudioFile {

    /*
     * You can get a free API key from https://platform.openai.com/account/api-keys
     * private final OpenAI openAI = new OpenAI("YOUR_API_KEY");
     * private final OpenAI openAI = new OpenAI("YOUR_API_KEY", "YOUR_ORGANIZATION");
     */
    //private final OpenAI openAI = new OpenAI(System.getenv("OPEN_AI_API_KEY"));

    public static void main(String[] args) throws URISyntaxException {
        //Initialize ExampleTranslationFromAudioFile class
        ExampleTranslationFromAudioFile translateFromAudioFile = new ExampleTranslationFromAudioFile();

        //This is the language we want to translate our audio file to
        //Language parameter must be specified in ISO-639-1 format or Locale
        //Example -> "French" = "fr" | "English UK" = "en_GB"
        //String language = "fr";
        Locale language = Locale.FRENCH;

        //Example audio file that we are going to upload to OpenAI to be translated
        URL audioUrl = ExampleTranslationFromAudioFile.class.getResource("what-can-i-do.mp3");
        Path filePath = null;
        if (audioUrl != null) {
            filePath = Path.of(audioUrl.toURI());
        }
        if(filePath == null) {
            System.err.println("File could not be found.");
            return;
        }

        //Response from OpenAI with the translated string
        String response = translateFromAudioFile.communicate(filePath, language);

        //Print the translation to the console
        System.out.println(response);
    }

    private String communicate(Path filePath, Locale language) {
        //Alternatively can use AudioData.create(audioPath, "fr");
        AudioData audioTranslationData = AudioData.create(filePath, language);

        OpenAI openAI = OpenAI.builder()
                .setApiKey(System.getenv("OPEN_AI_API_KEY"))
                .createTranscriptionTranslation(audioTranslationData)
                .build();

        //Sends the request to OpenAI's endpoint & parses the response data
        openAI.sendRequest();

        //Call the CreateTranslation API from OpenAI & create instance
        Optional<CreateTranscriptionTranslation> createTranslation = openAI.transcriptionTranslation();

        //Return as text, do not replace \n or ascii characters
        return createTranslation.map(CreateTranscription::asText).orElse(null);
    }

}
