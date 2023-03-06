import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.GetModel;
import io.github.jetkai.openai.api.data.model.ModelData;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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

        //Set model to view
        String modelName = "davinci";

        //Create OpenAI instance using API key & organization
        //Organization is optional
        OpenAI openAI = OpenAI.builder()
                .setApiKey(apiKey)
                .setOrganization(organization)
                //Call the GetModel API from OpenAI & create instance
                .getModel(modelName)
                .build()
                //Finally, send our request to the API, this initiates the request (after .build())
                .sendRequest();

        assertNotNull(openAI);

        //Call the GetModel API from OpenAI & create instance
        Optional<GetModel> optionalGetModel = openAI.model();
        assertFalse(optionalGetModel.isEmpty());

        GetModel getModel = optionalGetModel.get();

        //Data structure example
        ModelData modelData = getModel.asData();
        assertNotNull(modelData);

        //Get id from data structure example
        String id = modelData.getId();
        assertNotNull(id);
        assertEquals(id, modelName);

        //Json example
        String json = getModel.asJson();
        assertNotNull(json);
    }

}
