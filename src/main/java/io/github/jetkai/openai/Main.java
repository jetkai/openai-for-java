package io.github.jetkai.openai;

import io.github.jetkai.openai.api.data.completion.chat.ChatCompletionData;
import io.github.jetkai.openai.api.data.completion.chat.ChatCompletionMessageData;
import io.github.jetkai.openai.util.ApiKeyFileData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.github.jetkai.openai.util.ReadApiKeyFromFile.getApiKeyFromFile;

/**
 * Main
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * @created 02/03/2023
 * @last-update 03/03/2023
 *
 * <p>
 * Note - This is just a test class, it is recommended to import this project as a library
 * and then call OpenAI openAI = new OpenAI("YOUR_API_KEY", "YOUR_ORGANIZATION");
 * <p>
 * If you would like more examples, check out the tests directory
 *      CreateChatCompletionTest - Sends a chat message to OpenAI and returns a response
 *      CreateCompletionTest - Sends a message to OpenAI and returns a response
 *      GetModelsTest - Returns all the OpenAI Models
 *      GetModelTest - Returns a specified Model
 */
public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        String openAIResponse = main.sayHelloToChatGPT();

        if(openAIResponse.contains("Error")) {
            System.err.println(openAIResponse);
        } else {
            System.out.println(openAIResponse);
        }

    }

    /**
     * Example - Say Hello To OpenAI
     * @return - message response from OpenAI
     */
    public String sayHelloToChatGPT() {

        ApiKeyFileData keyData = getApiKeyFromFile();

        if(keyData == null) {
            return "Error - Could not find API Key within \"resources/org/openAI/util/OpenAI-API-Key.json\"";
        }

        /*
         * You can manually type in the API Key instead of loading from the file:
         * OpenAI openAI = new OpenAI("MY_API_KEY");
         * OpenAI openAI = new OpenAI("MY_API_KEY", "MY_ORGANIZATION");
         */
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
        completion.setModel("openAI-3.5-turbo");
        completion.setMessages(messages);

        String[] response = openAI.createChatCompletion(completion).asStringArray();

        if(response != null && response.length > 0) {
            return Arrays.toString(response);
        }

        return "Error - No choice found.";

    }

}
