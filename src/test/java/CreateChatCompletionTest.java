import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.data.completion.chat.ChatCompletionData;
import io.github.jetkai.openai.api.data.completion.chat.ChatCompletionMessageData;
import io.github.jetkai.openai.api.data.completion.response.CompletionResponseData;
import io.github.jetkai.openai.util.ApiKeyFileData;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.github.jetkai.openai.util.ReadApiKeyFromFile.getApiKeyFromFile;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * CreateChatCompletionTest
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * @created 02/03/2023
 * @last-update 03/03/2023
 */
public class CreateChatCompletionTest {

    @Test
    void createChatCompletionTest() {
        ApiKeyFileData keyData = getApiKeyFromFile();

        assertNotNull(keyData);

        OpenAI openAI = new OpenAI(keyData.getApiKey(), keyData.getOrganization());

        //Object of the Message it-self
        ChatCompletionMessageData message = new ChatCompletionMessageData();
        //List of Messages that you would like to send to the Chat Bot
        List<ChatCompletionMessageData> messages = new ArrayList<>();

        message.setRole("user");
        message.setContent("Hello!");

        messages.add(message);

        //Completion Data, ready to send to the OpenAI Api
        ChatCompletionData completion = new ChatCompletionData();
        completion.setModel("gpt-3.5-turbo");
        completion.setMessages(messages);

        CompletionResponseData data = openAI.createChatCompletion(completion).asData();

        assertNotNull(data.getModel());

    }

}
