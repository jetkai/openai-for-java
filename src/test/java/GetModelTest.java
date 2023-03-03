import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.GetModel;
import io.github.jetkai.openai.api.data.model.ModelData;
import io.github.jetkai.openai.util.ApiKeyFileData;
import org.junit.jupiter.api.Test;

import static io.github.jetkai.openai.util.ReadApiKeyFromFile.getApiKeyFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * GetModelTest
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * @created 02/03/2023
 * @last-update 03/03/2023
 */
public class GetModelTest {

    @Test
    void getModelTest() {
        //Grab API Key from .json file
        ApiKeyFileData keyData = getApiKeyFromFile();
        assertNotNull(keyData);

        //Create OpenAI instance using API key loaded from the .json file (example)
        OpenAI openAI = new OpenAI(keyData.getApiKey(), keyData.getOrganization());

        //Set model to view
        String modelName = "davinci";

        //Call the GetModel API from OpenAI & create instance
        GetModel getModel = openAI.getModel(modelName);

        //Data structure example
        ModelData modelData = getModel.asData();
        assertNotNull(modelData);

        //Get id from data structure example
        String id = modelData.getId();
        assertEquals(id, modelName);

        //Json example
        String json = getModel.asJson();
        assertNotNull(json);
    }

}
