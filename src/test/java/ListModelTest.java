import io.github.jetkai.openai.api.ListModel;
import io.github.jetkai.openai.api.data.model.ModelData;
import io.github.jetkai.openai.openai.OpenAI;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ListModelTest
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * @created 02/03/2023
 * @last-update 03/03/2023
 */
public class ListModelTest {

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
                //Call the ListModel API from OpenAI & create instance
                .listModel(modelName)
                .build()
                //Finally, send our request to the API, this initiates the request (after .build())
                .sendRequest();

        assertNotNull(openAI);

        //Call the ListModel API from OpenAI & create instance
        Optional<ListModel> optionalGetModel = openAI.model();
        assertFalse(optionalGetModel.isEmpty());

        ListModel listModel = optionalGetModel.get();

        //Data structure example
        ModelData modelData = listModel.asData();
        assertNotNull(modelData);

        //Get id from data structure example
        String id = modelData.getId();
        assertNotNull(id);
        assertEquals(id, modelName);

        //Json example
        String json = listModel.asJson();
        assertNotNull(json);
    }

}
