import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.CreateTranscription;
import io.github.jetkai.openai.api.data.transcription.TranscriptionData;
import io.github.jetkai.openai.api.data.transcription.TranscriptionResponseData;
import io.github.jetkai.openai.util.ApiKeyFileData;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

import static io.github.jetkai.openai.util.ReadApiKeyFromFile.getApiKeyFromFile;
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
        //Grab API Key from .json file
        ApiKeyFileData keyData = getApiKeyFromFile();
        assertNotNull(keyData);

        //Create OpenAI instance using API key loaded from the .json file (example)
        OpenAI openAI = new OpenAI(keyData.getApiKey(), keyData.getOrganization());

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

        //TranscriptionData, ready to send to the OpenAI Api
        TranscriptionData transcriptionData = new TranscriptionData();

        //Set the path for the audio file
        transcriptionData.setFile(audioPath);

        //Use the whisper-1 model for translation
        transcriptionData.setModel("whisper-1");

        //Option to specify language of the audio file
        //transcriptionData.setLanguage("en");

        //Call the CreateTranscription API from OpenAI & create instance
        CreateTranscription createTranscription = openAI.createTranscription(transcriptionData);

        //Transcript as a string (Audio File -> English)
        String transcript = createTranscription.asText();
        assertNotNull(transcript);
        assertFalse(transcript.isEmpty());

        //Get id from data structure example
        TranscriptionResponseData responseData = createTranscription.asData();
        assertNotNull(responseData);

        //Json example
        String json = createTranscription.asJson();
        assertNotNull(json);
        assertFalse(json.isEmpty());
    }

}
