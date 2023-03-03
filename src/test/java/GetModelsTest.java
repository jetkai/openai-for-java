import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.GetModels;
import io.github.jetkai.openai.api.data.model.ModelData;
import io.github.jetkai.openai.util.ApiKeyFileData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.github.jetkai.openai.util.ReadApiKeyFromFile.getApiKeyFromFile;
import static org.junit.jupiter.api.Assertions.*;

/**
 * GetModelsTest
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * @created 02/03/2023
 * @last-update 03/03/2023
 */
public class GetModelsTest {

    @Test
    void getModelsTest() {
        //Grab API Key from .json file
        ApiKeyFileData keyData = getApiKeyFromFile();
        assertNotNull(keyData);

        //Create OpenAI instance using API key loaded from the .json file (example)
        OpenAI openAI = new OpenAI(keyData.getApiKey(), keyData.getOrganization());

        //Call the GetModels API from OpenAI & create instance
        GetModels getModels = openAI.getModels();

        //Data structure example
        ModelData[] modelData = getModels.asDataArray(); //You can view all the listed models here
        assertNotNull(modelData);
        assertTrue(modelData.length > 0);

        //Data list example
        List<ModelData> modelList = getModels.asDataList();
        assertNotNull(modelList);
        assertTrue(modelList.size() > 0);

        //Json example
        String json = getModels.asJson();
        assertNotNull(json);
        assertFalse(json.isEmpty());
    }

}
