import org.gpt.ChatGPT;
import org.gpt.api.data.model.ModelData;
import org.gpt.util.ApiKeyFileData;
import org.junit.jupiter.api.Test;

import static org.gpt.util.ReadApiKeyFromFile.getApiKeyFromFile;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetModelsTest {

    @Test
    void getModelsTest() {
        ApiKeyFileData keyData = getApiKeyFromFile();

        assertNotNull(keyData);

        ChatGPT gpt = new ChatGPT(keyData.getApiKey(), keyData.getOrganization());

        ModelData[] data = gpt.getModels();

        assertTrue(data.length > 1);
    }

}
