import io.github.jetkai.chatgpt.ChatGPT;
import io.github.jetkai.chatgpt.api.data.embedding.EmbeddingData;
import io.github.jetkai.chatgpt.api.data.embedding.EmbeddingResponseData;
import io.github.jetkai.chatgpt.api.data.embedding.EmbeddingResponseDataBlock;
import io.github.jetkai.chatgpt.util.ApiKeyFileData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.github.jetkai.chatgpt.util.ReadApiKeyFromFile.getApiKeyFromFile;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateEmbeddingTest {

    @Test
    void createEmbeddingTest() {
        ApiKeyFileData keyData = getApiKeyFromFile();

        assertNotNull(keyData);

        ChatGPT gpt = new ChatGPT(keyData.getApiKey(), keyData.getOrganization());

        //Completion Data, ready to send to the ChatGPT Api
        EmbeddingData embed = new EmbeddingData();
        embed.setModel("text-embedding-ada-002");
        embed.setInput("The food was delicious and the waiter...");

        EmbeddingResponseData data = gpt.createEmbeddingResponse(embed);

        List<EmbeddingResponseDataBlock> embeddingBlock = data.getData();

        assertNotNull(embeddingBlock);

        assertFalse(embeddingBlock.isEmpty());
        assertFalse(embeddingBlock.get(0).getEmbedding().isEmpty());
    }

}
