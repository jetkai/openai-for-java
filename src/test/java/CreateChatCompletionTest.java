import org.gpt.ChatGPT;
import org.gpt.api.data.completion.chat.ChatCompletionData;
import org.gpt.api.data.completion.chat.ChatCompletionMessageData;
import org.gpt.api.data.completion.response.CompletionResponseData;
import org.gpt.util.ApiKeyFileData;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.gpt.util.ReadApiKeyFromFile.getApiKeyFromFile;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateChatCompletionTest {

    @Test
    void createChatCompletionTest() {
        ApiKeyFileData keyData = getApiKeyFromFile();

        assertNotNull(keyData);

        ChatGPT gpt = new ChatGPT(keyData.getApiKey(), keyData.getOrganization());

        //Object of the Message it-self
        ChatCompletionMessageData message = new ChatCompletionMessageData();
        //List of Messages that you would like to send to the Chat Bot
        List<ChatCompletionMessageData> messages = new ArrayList<>();

        message.setRole("user");
        message.setContent("Hello!");

        messages.add(message);

        //Completion Data, ready to send to the ChatGPT Api
        ChatCompletionData completion = new ChatCompletionData();
        completion.setModel("gpt-3.5-turbo");
        completion.setMessages(messages);

        CompletionResponseData data = gpt.createChatCompletionResponse(completion);

        assertNotNull(data.getModel());

    }

}
