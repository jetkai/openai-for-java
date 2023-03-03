import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.data.completion.CompletionData;
import io.github.jetkai.openai.api.data.completion.response.CompletionResponseData;
import io.github.jetkai.openai.util.ApiKeyFileData;
import org.junit.jupiter.api.Test;

import static io.github.jetkai.openai.util.ReadApiKeyFromFile.getApiKeyFromFile;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * CreateCompletionTest
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * @created 02/03/2023
 * @last-update 03/03/2023
 */
public class CreateCompletionTest {


    @Test
    void createCompletionTest() {
        ApiKeyFileData keyData = getApiKeyFromFile();

        assertNotNull(keyData);

        OpenAI openAI = new OpenAI(keyData.getApiKey(), keyData.getOrganization());

        CompletionData completion = new CompletionData();
        completion.setModel("text-davinci-003");
        completion.setPrompt("Say this is a test");
        completion.setMaxTokens(7);
        completion.setTemperature(0);
        completion.setTopP(1);
        completion.setN(1);
        completion.setStream(false);
        completion.setLogprobs(null);
        completion.setStop("\n");

        CompletionResponseData data = openAI.createCompletion(completion).asData();  //You can call "data" to see the response

        assertNotNull(data.getModel());

    }

}
