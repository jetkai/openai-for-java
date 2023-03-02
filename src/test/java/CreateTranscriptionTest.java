import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.data.transcription.TranscriptionData;
import io.github.jetkai.openai.util.ApiKeyFileData;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

import static io.github.jetkai.openai.util.ReadApiKeyFromFile.getApiKeyFromFile;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateTranscriptionTest {

    @Test
    void createTranscriptionTest() {
        ApiKeyFileData keyData = getApiKeyFromFile();

        assertNotNull(keyData);

        OpenAI openAI = new OpenAI(keyData.getApiKey(), keyData.getOrganization());

        //Completion Data, ready to send to the OpenAI Api
        TranscriptionData transcript = new TranscriptionData();

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

        transcript.setFile(audioPath);
        transcript.setModel("whisper-1");

        String data = openAI.createTranscription(transcript);

        assertNotNull(data);
        assertFalse(data.isEmpty());

    }

}
