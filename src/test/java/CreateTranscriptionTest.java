import io.github.jetkai.openai.api.CreateTranscription;
import io.github.jetkai.openai.api.data.audio.AudioData;
import io.github.jetkai.openai.api.data.audio.response.AudioResponseData;
import io.github.jetkai.openai.openai.OpenAI;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * CreateTranscriptionTest
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.1
 * @created 02/03/2023
 * @last-update 05/03/2023
 */
public class CreateTranscriptionTest {

    @Test
    void createTranscriptionTest() {
        //Grab OpenAI API key from system environment variables (gradle.properties)
        String apiKey = System.getenv("OPEN_AI_API_KEY");
        String organization = System.getenv("OPEN_AI_ORGANIZATION");
        assertNotNull(apiKey);
        assertNotNull(organization);

        //Example audio file that we are going to upload to OpenAI to have a transcript of
        Path audioPath = getAudioPath();
        assertNotNull(audioPath);

        //Data that we are going to be sending to the API
        AudioData audioTranscriptionData = AudioData.builder()
                //Set the path for the audio file
                .setFilePath(audioPath)
                //Use the whisper-1 model for translation
                .setModel("whisper-1")
                //Option to specify language of the audio file
                //audioTranscriptionData.setLanguage("en");
                .build();

        //Create OpenAI instance using API key & organization
        //Organization is optional
        OpenAI openAI = OpenAI.builder()
                .setApiKey(apiKey)
                .setOrganization(organization)
                .createTranscription(audioTranscriptionData)
                .build()
                //Finally, send our request to the API, this initiates the request (after .build())
                .sendRequest();

        assertNotNull(openAI);

        //Call the CreateTranscription API from OpenAI & create instance
        Optional<CreateTranscription> optionalCreateTranscription = openAI.transcription();
        assertFalse(optionalCreateTranscription.isEmpty());

        //Additionally check the getter method is not null
        assertNotNull(openAI.getTranscription());

        CreateTranscription createTranscription = optionalCreateTranscription.get();

        //Transcript as a string (Audio File -> English)
        String transcript = createTranscription.asText();
        assertNotNull(transcript);
        assertFalse(transcript.isEmpty());

        //Get id from data structure example
        AudioResponseData responseData = createTranscription.asData();
        assertNotNull(responseData);

        assertNotNull(createTranscription.asNormalizedText());

        //Json example
        String json = createTranscription.asJson();
        assertNotNull(json);
        assertFalse(json.isEmpty());
    }

    private Path getAudioPath() {
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
        return audioPath;
    }

}
