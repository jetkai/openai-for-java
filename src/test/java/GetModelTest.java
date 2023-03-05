import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.GetModel;
import io.github.jetkai.openai.api.data.model.ModelData;
import org.junit.jupiter.api.Test;

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
        //Grab OpenAI API key from system environment variables (gradle.properties)
        String apiKey = System.getenv("OPEN_AI_API_KEY");
        String organization = System.getenv("OPEN_AI_ORGANIZATION");
        assertNotNull(apiKey);
        assertNotNull(organization);

        //Create OpenAI instance using API key & organization
        //Organization is optional
        OpenAI openAI = new OpenAI(apiKey, organization);

        //Set model to view
        String modelName = "davinci";

        //Call the GetModel API from OpenAI & create instance
        GetModel getModel = openAI.model(modelName);

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
