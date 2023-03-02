import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.data.model.ModelData;
import io.github.jetkai.openai.util.ApiKeyFileData;
import org.junit.jupiter.api.Test;

import static io.github.jetkai.openai.util.ReadApiKeyFromFile.getApiKeyFromFile;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetModelsTest {

    @Test
    void getModelsTest() {
        ApiKeyFileData keyData = getApiKeyFromFile();

        assertNotNull(keyData);

        OpenAI openAI = new OpenAI(keyData.getApiKey(), keyData.getOrganization());

        ModelData[] data = openAI.getModels(); //You can view all the listed models here

        assertTrue(data.length > 1);
    }

}
