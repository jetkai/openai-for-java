import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.CreateTranscriptionTranslation;
import io.github.jetkai.openai.api.CreateTranslation;
import io.github.jetkai.openai.api.data.audio.AudioData;
import io.github.jetkai.openai.api.data.audio.AudioResponseData;
import io.github.jetkai.openai.api.data.completion.CompletionData;
import io.github.jetkai.openai.api.data.completion.response.CompletionResponseData;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * CreateTranslationTests
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * @created 02/03/2023
 * @last-update 03/03/2023
 */
public class CreateTranslationTests {

    //Grab OpenAI API key from system environment variables (gradle.properties)
    private final String apiKey = System.getenv("OPEN_AI_API_KEY");
    //Organization is optional
    private final String organization = System.getenv("OPEN_AI_ORGANIZATION");

    @Test
    void createTranslationTest() {
        assertNotNull(apiKey);
        assertNotNull(organization);

        //Create OpenAI instance using API key & organization

        //What we ask the AI to do, we are asking it to translate the following text to French
        List<String> instruction = new ArrayList<>(List.of(
                "Translate the following to French:",
                "Hey, how are you today?",
                "I was just wondering what you were up to?")
        );

        //Build the data that we wish to send to the API
        CompletionData translation = CompletionData.builder()
                .setPrompt(instruction)
                //Use the whisper-1 model for translation
                .setModel("text-davinci-003")
                .build();

        //Create OpenAI instance using API key & organization
        //Organization is optional
        OpenAI openAI = OpenAI.builder()
                .setApiKey(apiKey)
                .setOrganization(organization)
                .createTranslation(translation)
                .build()
                //Finally, send our request to the API, this initiates the request
                .sendRequest();

        //Call the CreateTranslation API response from OpenAI instance
        Optional<CreateTranslation> optionalCreateTranslation = openAI.translation();
        assertFalse(optionalCreateTranslation.isEmpty());

        CreateTranslation createTranslation = optionalCreateTranslation.get();

        //Data structure example
        CompletionResponseData responseData = createTranslation.asData();
        assertNotNull(responseData);

        //Translated text as string (English -> French)
        String translatedText = createTranslation.asText();
        assertNotNull(translatedText);
        assertFalse(translatedText.isEmpty());

        //Json example
        String json = createTranslation.asJson();
        assertNotNull(json);
        assertFalse(json.isEmpty());
    }

    @Test
    void createAudioTranslationTest() {
        assertNotNull(apiKey);
        assertNotNull(organization);

        //Example audio file that we are going to upload to OpenAI to be translated
        URL audioUrl = CreateImageEditTest.class.getResource("what-can-i-do.mp3");
        Path audioPath = null;
        try {
            if (audioUrl != null) {
                audioPath = Path.of(audioUrl.toURI());
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(audioPath);

        //Data that we are going to be sending to the API
        AudioData audioTranslationData = AudioData.builder()
                //Set the path for the audio file
                .setFilePath(audioPath)
                //Use the whisper-1 model for translation
                .setModel("whisper-1")
                //Set language to translate (French)
                .setLanguage("fr")
                .build();

        assertNotNull(audioTranslationData);

        //Create OpenAI instance using API key & organization
        //Organization is optional
        OpenAI openAI = OpenAI.builder()
                .setApiKey(apiKey)
                .setOrganization(organization)
                .createTranscriptionTranslation(audioTranslationData)
                .build()
                //Finally, send our request to the API, this initiates the request (after .build())
                .sendRequest();

        assertNotNull(openAI);

        //Call the CreateTranslation API from OpenAI & create instance
        Optional<CreateTranscriptionTranslation> optionalTranscriptionTranslation = openAI.transcriptionTranslation();
        assertFalse(optionalTranscriptionTranslation.isEmpty());

        CreateTranscriptionTranslation transcriptionTranslation = optionalTranscriptionTranslation.get();

        //Data structure example
        AudioResponseData responseData = transcriptionTranslation.asData();
        assertNotNull(responseData);

        //Translated text as string (English -> French)
        String translatedText = transcriptionTranslation.asText();
        assertNotNull(translatedText);
        assertFalse(translatedText.isEmpty());

        //Json example
        String json = transcriptionTranslation.asJson();
        assertNotNull(json);
        assertFalse(json.isEmpty());
    }

}
