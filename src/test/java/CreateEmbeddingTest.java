import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.data.embedding.EmbeddingData;
import io.github.jetkai.openai.api.data.embedding.EmbeddingResponseData;
import io.github.jetkai.openai.api.data.embedding.EmbeddingResponseDataBlock;
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
        ApiKeyFileData keyData = getApiKeyFromFile();

        assertNotNull(keyData);

        OpenAI openAI = new OpenAI(keyData.getApiKey(), keyData.getOrganization());

        //Completion Data, ready to send to the OpenAI Api
        EmbeddingData embed = new EmbeddingData();
        embed.setModel("text-embedding-ada-002");
        embed.setInput("The food was delicious and the waiter...");

        EmbeddingResponseData data = openAI.createEmbedding(embed).asData();

        List<EmbeddingResponseDataBlock> embeddingBlock = data.getData();

        assertNotNull(embeddingBlock);

        assertFalse(embeddingBlock.isEmpty());
        assertFalse(embeddingBlock.get(0).getEmbedding().isEmpty());
    }

}
