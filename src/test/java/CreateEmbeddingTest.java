import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.CreateEmbedding;
import io.github.jetkai.openai.api.data.embedding.EmbeddingData;
import io.github.jetkai.openai.api.data.embedding.EmbeddingResponseData;
import io.github.jetkai.openai.util.ApiKeyFileData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.github.jetkai.openai.util.ReadApiKeyFromFile.getApiKeyFromFile;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * CreateEmbeddingTest
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * @created 02/03/2023
 * @last-update 03/03/2023
 */
public class CreateEmbeddingTest {

    @Test
    void createEmbeddingTest() {
        //Grab API Key from .json file
        ApiKeyFileData keyData = getApiKeyFromFile();
        assertNotNull(keyData);

        //Create OpenAI instance using API key loaded from the .json file (example)
        OpenAI openAI = new OpenAI(keyData.getApiKey(), keyData.getOrganization());

        //EmbeddingData, ready to send to the OpenAI API
        EmbeddingData embed = new EmbeddingData();

        //ID of the model to use. You can use the List models API to see all of your available models,
        //or see our Model overview for descriptions of them.
        embed.setModel("text-embedding-ada-002");

        //Input text to get embeddings for, encoded as a string or array of tokens.
        //To get embeddings for multiple inputs in a single request, pass an array of strings or array of token arrays.
        //Each input must not exceed 8192 tokens in length.
        embed.setInput("The food was delicious and the waiter...");

        //Call the CreateEmbedding API from OpenAI & create instance
        CreateEmbedding createEmbedding = openAI.createEmbedding(embed);

        //Data structure example
        EmbeddingResponseData embeddingBlock = createEmbedding.asData();
        assertNotNull(embeddingBlock);

        //Float List example
        List<Float> floatList = createEmbedding.asFloatList();
        assertNotNull(floatList);
        assertFalse(floatList.isEmpty());

        //Json example
        String json = createEmbedding.asJson();
        assertNotNull(json);
        assertFalse(json.isEmpty());
    }

}
