import io.github.jetkai.openai.api.ListModel;
import io.github.jetkai.openai.openai.OpenAI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * GetOpenAITests
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.1
 * @created 02/03/2023
 * @last-update 05/03/2023
 */
public class GetOpenAITests {

    @Test
    void getOpenAITests() {
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

        assertNotNull(openAI.getHttpClient());
        assertNotNull(openAI.getHttpClientInstance());
        assertNotNull(openAI.getApiKey());
        assertNotNull(openAI.getOrganization());

    }

}
