import io.github.jetkai.openai.api.ListModels;
import io.github.jetkai.openai.api.data.model.ModelData;
import io.github.jetkai.openai.openai.OpenAI;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ListModelsTest
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.1
 * @created 02/03/2023
 * @last-update 03/03/2023
 */
public class ListModelsTest {

    @Test
    void getModelsTest() {
        //Grab OpenAI API key from system environment variables (gradle.properties)
        String apiKey = System.getenv("OPEN_AI_API_KEY");
        String organization = System.getenv("OPEN_AI_ORGANIZATION");
        assertNotNull(apiKey);
        assertNotNull(organization);

        //Create OpenAI instance using API key & organization
        //Organization is optional
        OpenAI openAI = OpenAI.builder()
                .setApiKey(apiKey)
                .setOrganization(organization)
                //Call the ListModels API from OpenAI & create instance
                .listModels()
                .build()
                //Finally, send our request to the API, this initiates the request (after .build())
                .sendRequest();

        Optional<ListModels> optionalGetModels = openAI.models();
        assertFalse(optionalGetModels.isEmpty());

        //Additionally check the getter method is not null
        assertNotNull(openAI.getModels());

        ListModels listModels = optionalGetModels.get();

        //Data structure example
        ModelData[] modelData = listModels.asDataArray(); //You can view all the listed models here
        assertNotNull(modelData);
        assertTrue(modelData.length > 0);

        //Data list example
        List<ModelData> modelList = listModels.asDataList();
        assertNotNull(modelList);
        assertFalse(modelList.isEmpty());

        //Json example
        String json = listModels.asJson();
        assertNotNull(json);
        assertFalse(json.isEmpty());
    }

}
