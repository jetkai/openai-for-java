import io.github.jetkai.chatgpt.ChatGPT;
import io.github.jetkai.chatgpt.api.data.model.ModelData;
import io.github.jetkai.chatgpt.util.ApiKeyFileData;
import org.junit.jupiter.api.Test;

import static io.github.jetkai.chatgpt.util.ReadApiKeyFromFile.getApiKeyFromFile;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetModelsTest {

    @Test
    void getModelsTest() {
        ApiKeyFileData keyData = getApiKeyFromFile();

        assertNotNull(keyData);

        ChatGPT gpt = new ChatGPT(keyData.getApiKey(), keyData.getOrganization());

        ModelData[] data = gpt.getModels(); //You can view all the listed models here

        assertTrue(data.length > 1);
    }

}
