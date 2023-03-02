import org.gpt.ChatGPT;
import org.gpt.api.data.model.ModelData;
import org.gpt.util.ApiKeyFileData;
import org.junit.jupiter.api.Test;

import static org.gpt.util.ReadApiKeyFromFile.getApiKeyFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetModelTest {

    @Test
    void getModelTest() {
        ApiKeyFileData keyData = getApiKeyFromFile();

        assertNotNull(keyData);

        ChatGPT gpt = new ChatGPT(keyData.getApiKey(), keyData.getOrganization());

        String modelName = "davinci";
        ModelData data = gpt.getModel(modelName); //You can view the listed model here

        assertEquals(data.getId(), modelName);
    }

}
