import io.github.jetkai.openai.api.CreateEmbedding;
import io.github.jetkai.openai.api.data.embedding.EmbeddingData;
import io.github.jetkai.openai.openai.OpenAI;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
        // Grab OpenAI API key and organization from environment variables
        String apiKey = System.getenv("OPEN_AI_API_KEY");
        String organization = System.getenv("OPEN_AI_ORGANIZATION");
        assertNotNull(apiKey, "OPEN_AI_API_KEY environment variable not set");
        assertNotNull(organization, "OPEN_AI_ORGANIZATION environment variable not set");

        // Create EmbeddingData object
        EmbeddingData embed = EmbeddingData.builder()
                .setModel("text-embedding-ada-002")
                .setInput("The food was delicious and the waiter...")
                .setInput(List.of("The food was delicious and the waiter..."))
                .setUser("user")
                .build();

        // Call CreateEmbedding API and retrieve response
        OpenAI openAI = OpenAI.builder()
                .setApiKey(apiKey)
                .setOrganization(organization)
                .createEmbedding(embed)
                .build()
                .sendRequest();

        Optional<CreateEmbedding> optionalEmbedding = openAI.embedding();
        assertFalse(optionalEmbedding.isEmpty(), "CreateEmbedding object not found");

        // Assertions using assertAll
        assertAll(
                () -> assertNotNull(openAI.getEmbedding(), "CreateEmbedding object not found"),
                () -> assertNotNull(optionalEmbedding.get().asData(), "EmbeddingResponseData object not found"),
                () -> assertFalse(optionalEmbedding.get().asData().model().isEmpty(), "Model name not found"),
                () -> assertFalse(optionalEmbedding.get().asData().data().isEmpty(), "Embedding data not found"),
                () -> assertFalse(optionalEmbedding.get().asData().object().isEmpty(), "Object type not found"),
                () -> assertFalse(optionalEmbedding.get().asData().usage().isEmpty(), "Usage information not found"),
                () -> assertNotNull(optionalEmbedding.get().asFloatList(), "Float list not found"),
                () -> assertFalse(optionalEmbedding.get().asFloatList().isEmpty(), "Float list is empty"),
                () -> assertNotNull(optionalEmbedding.get().asJson(), "JSON string not found"),
                () -> assertFalse(optionalEmbedding.get().asJson().isEmpty(), "JSON string is empty")
        );
    }

}
