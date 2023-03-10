import io.github.jetkai.openai.api.ListModel;
import io.github.jetkai.openai.net.OpenAIEndpoints;
import io.github.jetkai.openai.openai.OpenAI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * CreateInstanceTest
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.1
 * @created 02/03/2023
 * @last-update 05/03/2023
 */
public class CreateInstanceTest {

    @Test
    void createInstanceTest() {
        System.setProperty("is.testing", "true");
        String apiKey = System.getenv("OPEN_AI_API_KEY");
        String organization = System.getenv("OPEN_AI_ORGANIZATION");
        assertNotNull(apiKey);
        assertNotNull(organization);

        OpenAI openAI = OpenAI.builder()
                .setApiKey(apiKey)
                .setOrganization(organization)
                .build();

        ListModel instance1 = openAI.createInstance(ListModel.class, "davinci");
        assertNotNull(instance1);

        ListModel instance2 = openAI.createInstance(OpenAIEndpoints.LIST_MODEL, "davinci");
        assertNotNull(instance2);

        //This test should throw
        for(OpenAIEndpoints openAIEndpoints : OpenAIEndpoints.values()) {
            assertThrows(IllegalArgumentException.class, ()-> openAI.createInstance(openAIEndpoints, null));
        }

    }

}
