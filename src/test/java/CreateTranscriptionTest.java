import io.github.jetkai.chatgpt.ChatGPT;
import io.github.jetkai.chatgpt.api.data.transcription.TranscriptionData;
import io.github.jetkai.chatgpt.util.ApiKeyFileData;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

import static io.github.jetkai.chatgpt.util.ReadApiKeyFromFile.getApiKeyFromFile;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateTranscriptionTest {

    @Test
    void createTranscriptionTest() {
        ApiKeyFileData keyData = getApiKeyFromFile();

        assertNotNull(keyData);

        ChatGPT gpt = new ChatGPT(keyData.getApiKey(), keyData.getOrganization());

        //Completion Data, ready to send to the ChatGPT Api
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

        String data = gpt.createTranscription(transcript);

        assertNotNull(data);
        assertFalse(data.isEmpty());

    }

}
