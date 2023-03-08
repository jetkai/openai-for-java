import io.github.jetkai.openai.api.CreateEmbedding;
import io.github.jetkai.openai.api.data.embedding.EmbeddingData;
import io.github.jetkai.openai.api.data.embedding.response.EmbeddingResponseData;
import io.github.jetkai.openai.openai.OpenAI;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * CreateEmbeddingTest
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.1
 * @created 02/03/2023
 * @last-update 03/03/2023
 */
public class CreateEmbeddingTest {

    @Test
    void createEmbeddingTest() {
        //Grab OpenAI API key from system environment variables (gradle.properties)
        String apiKey = System.getenv("OPEN_AI_API_KEY");
        String organization = System.getenv("OPEN_AI_ORGANIZATION");
        assertNotNull(apiKey);
        assertNotNull(organization);

        //EmbeddingData, ready to send to the OpenAI API
        EmbeddingData embed = EmbeddingData.builder()
                //ID of the model to use. You can use the List models API to see all of your available models,
                //or see our Model overview for descriptions of them.
                .setModel("text-embedding-ada-002")
                //Input text to get embeddings for, encoded as a string or array of tokens.
                //To get embeddings for multiple inputs in a single request, pass an array of strings or array of token arrays.
                //Each input must not exceed 8192 tokens in length.
                .setInput("The food was delicious and the waiter...")
                .build();

        //Call the CreateEmbedding API from OpenAI & create instance
        OpenAI openAI = OpenAI.builder()
                .setApiKey(apiKey)
                .setOrganization(organization)
                .createEmbedding(embed)
                .build()
                .sendRequest();

        Optional<CreateEmbedding> optionalEmbedding = openAI.embedding();
        assertFalse(optionalEmbedding.isEmpty());

        //Additionally check the getter method is not null
        assertNotNull(openAI.getEmbedding());

        CreateEmbedding createEmbedding = optionalEmbedding.get();

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
