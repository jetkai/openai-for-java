import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.CreateTranscription;
import io.github.jetkai.openai.api.data.audio.AudioData;
import io.github.jetkai.openai.api.data.audio.AudioResponseData;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * CreateTranscriptionTest
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * @created 02/03/2023
 * @last-update 03/03/2023
 */
public class CreateTranscriptionTest {

    @Test
    void createTranscriptionTest() {
        //Grab OpenAI API key from system environment variables (gradle.properties)
        String apiKey = System.getenv("OPEN_AI_API_KEY");
        String organization = System.getenv("OPEN_AI_ORGANIZATION");
        assertNotNull(apiKey);
        assertNotNull(organization);

        //Create OpenAI instance using API key & organization
        //Organization is optional
        OpenAI openAI = new OpenAI(apiKey, organization);

        //Example audio file that we are going to upload to OpenAI to have a transcript of
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

        //AudioTranscriptionData, ready to send to the OpenAI Api
        AudioData audioTranscriptionData = new AudioData();

        //Set the path for the audio file
        audioTranscriptionData.setFile(audioPath);

        //Use the whisper-1 model for translation
        audioTranscriptionData.setModel("whisper-1");

        //Option to specify language of the audio file
        //audioTranscriptionData.setLanguage("en");

        //Call the CreateTranscription API from OpenAI & create instance
        CreateTranscription createTranscription = openAI.createTranscription(audioTranscriptionData);

        //Transcript as a string (Audio File -> English)
        String transcript = createTranscription.asText();
        assertNotNull(transcript);
        assertFalse(transcript.isEmpty());

        //Get id from data structure example
        AudioResponseData responseData = createTranscription.asData();
        assertNotNull(responseData);

        //Json example
        String json = createTranscription.asJson();
        assertNotNull(json);
        assertFalse(json.isEmpty());
    }

}
