import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.CreateChatCompletion;
import io.github.jetkai.openai.api.data.completion.chat.ChatCompletionData;
import io.github.jetkai.openai.api.data.completion.chat.ChatCompletionMessageData;
import io.github.jetkai.openai.api.data.completion.response.CompletionResponseData;
import io.github.jetkai.openai.util.ApiKeyFileData;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.github.jetkai.openai.util.ReadApiKeyFromFile.getApiKeyFromFile;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
        //Grab API Key from .json file
        ApiKeyFileData keyData = getApiKeyFromFile();
        assertNotNull(keyData);

        //Create OpenAI instance using API key loaded from the .json file (example)
        OpenAI openAI = new OpenAI(keyData.getApiKey(), keyData.getOrganization());

        //Create message object, this will contain the data we want to send to ChatGPT
        ChatCompletionMessageData message = new ChatCompletionMessageData();

        //List of Messages that you would like to send to the Chat Bot
        List<ChatCompletionMessageData> messages = new ArrayList<>();

        //The role of the user
        message.setRole("user");

        //Message that you would like to send to OpenAI ChatGPT
        message.setContent("Hello!");

        //Add message to the messages list
        messages.add(message);

        //Completion Data, ready to send to the OpenAI Api
        ChatCompletionData completion = new ChatCompletionData();

        //ID of the model to use. Currently, only gpt-3.5-turbo and gpt-3.5-turbo-0301 are supported.
        completion.setModel("gpt-3.5-turbo");

        //The messages to generate chat completions for, in the chat format.
        completion.setMessages(messages);

        //Call the CreateChatCompletion API from OpenAI & create instance
        CreateChatCompletion createChatCompletion = openAI.createChatCompletion(completion);

        //Data structure example
        CompletionResponseData responseData = createChatCompletion.asData();
        assertNotNull(responseData);

        //StringArray example - contains the response in plaintext from ChatGPT
        String[] stringArray = createChatCompletion.asStringArray();
        assertNotNull(stringArray);

        //Json example
        String json = createChatCompletion.asJson();
        assertNotNull(json);
        assertFalse(json.isEmpty());
    }

}
