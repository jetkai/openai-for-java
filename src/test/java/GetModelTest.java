import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.data.model.ModelData;
import io.github.jetkai.openai.util.ApiKeyFileData;
import org.junit.jupiter.api.Test;

import static io.github.jetkai.openai.util.ReadApiKeyFromFile.getApiKeyFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetModelTest {

    @Test
    void getModelTest() {
        ApiKeyFileData keyData = getApiKeyFromFile();

        assertNotNull(keyData);

        OpenAI openAI = new OpenAI(keyData.getApiKey(), keyData.getOrganization());

        String modelName = "davinci";
        ModelData data = openAI.getModel(modelName); //You can view the listed model here

        assertEquals(data.getId(), modelName);
    }

}
