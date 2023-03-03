import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.CreateTranslation;
import io.github.jetkai.openai.api.data.translation.TranslationData;
import io.github.jetkai.openai.api.data.translation.TranslationResponseData;
import io.github.jetkai.openai.util.ApiKeyFileData;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

import static io.github.jetkai.openai.util.ReadApiKeyFromFile.getApiKeyFromFile;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * CreateTranslationTest
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * @created 02/03/2023
 * @last-update 03/03/2023
 */
public class CreateTranslationTest {

    @Test
    void createTranslationTest() {
        //Grab API Key from .json file
        ApiKeyFileData keyData = getApiKeyFromFile();
        assertNotNull(keyData);

        //Create OpenAI instance using API key loaded from the .json file (example)
        OpenAI openAI = new OpenAI(keyData.getApiKey(), keyData.getOrganization());

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

        //TranslationData, ready to send to the OpenAI API
        TranslationData translationData = new TranslationData();

        //Set the path for the audio file
        translationData.setFile(audioPath);

        //Use the whisper-1 model for translation
        translationData.setModel("whisper-1");

        //Set language to translate (French)
        translationData.setLanguage("fr");

        //Call the CreateTranslation API from OpenAI & create instance
        CreateTranslation createTranslation = openAI.createTranslation(translationData);

        //Data structure example
        TranslationResponseData responseData = createTranslation.asData();
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
