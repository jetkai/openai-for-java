import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.CreateTranscriptionTranslation;
import io.github.jetkai.openai.api.CreateTranslation;
import io.github.jetkai.openai.api.data.audio.AudioData;
import io.github.jetkai.openai.api.data.audio.AudioResponseData;
import io.github.jetkai.openai.api.data.completion.response.CompletionResponseData;
import io.github.jetkai.openai.api.data.translation.TranslationData;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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

    //Create OpenAI instance using API key & organization
    //Organization is optional
    private final OpenAI openAI = new OpenAI(
            //Grab OpenAI API key from system environment variables (gradle.properties)
            System.getenv("OPEN_AI_API_KEY"),
            System.getenv("OPEN_AI_ORGANIZATION")
    );

    @Test
    void createTranslationTest() {
        assertNotNull(openAI.getApiKey());
        assertNotNull(openAI.getOrganization());

        //AudioTranslationData, ready to send to the OpenAI API
        TranslationData translation = new TranslationData();

        //Set the path for the audio file
        List<String> instruction = new ArrayList<>(List.of(
                "Translate the following to French:",
                "Hey, how are you today?",
                "I was just wondering what you were up to?")
        );
        translation.setPrompt(instruction);

        //Use the whisper-1 model for translation
        translation.setModel("text-davinci-003");

        //Call the CreateTranslation API from OpenAI & create instance
        CreateTranslation createTranslation = openAI.createTranslation(translation);

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
        assertNotNull(openAI.getApiKey());
        assertNotNull(openAI.getOrganization());

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

        //AudioTranslationData, ready to send to the OpenAI API
        AudioData audioTranslationData = new AudioData();

        //Set the path for the audio file
        audioTranslationData.setFile(audioPath);

        //Use the whisper-1 model for translation
        audioTranslationData.setModel("whisper-1");

        //Set language to translate (French)
        audioTranslationData.setLanguage("fr");

        //Call the CreateTranslation API from OpenAI & create instance
        CreateTranscriptionTranslation createTranslation = openAI.createTranscriptionTranslation(audioTranslationData);

        //Data structure example
        AudioResponseData responseData = createTranslation.asData();
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

}
