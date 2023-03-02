package org.gpt;

import org.gpt.api.data.completion.chat.ChatCompletionData;
import org.gpt.api.data.completion.chat.ChatCompletionMessageData;
import org.gpt.util.ApiKeyFileData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.gpt.util.ReadApiKeyFromFile.getApiKeyFromFile;

/**
 * Note - This is just a test class, it is recommended to import this project as a library
 * and then call ChatGPT gpt = new ChatGPT("YOUR_API_KEY", "YOUR_ORGANIZATION");
 *
 * If you would like more examples, check out the tests directory
 *      CreateChatCompletionTest - Sends a chat message to ChatGPT and returns a response
 *      CreateCompletionTest - Sends a message to ChatGPT and returns a response
 *      GetModelsTest - Returns all the ChatGPT Models
 *      GetModelTest - Returns a specified Model
 */
public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        String chatGPTResponse = main.sayHelloToChatGPT();

        if(chatGPTResponse.contains("Error")) {
            System.err.println(chatGPTResponse);
        } else {
            System.out.println(chatGPTResponse);
        }

    }

    /**
     * Example - Say Hello To ChatGPT
     * @return - message response from ChatGPT
     */
    public String sayHelloToChatGPT() {

        ApiKeyFileData keyData = getApiKeyFromFile();

        if(keyData == null) {
            return "Error - Could not find API Key within \"resources/org/gpt/util/ChatGPT-API-Key.json\"";
        }

        /*
         * You can manually type in the API Key instead of loading from the file:
         * ChatGPT gpt = new ChatGPT("MY_API_KEY");
         * ChatGPT gpt = new ChatGPT("MY_API_KEY", "MY_ORGANIZATION");
         */
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

        String[] response = gpt.createChatCompletion(completion);

        if(response != null && response.length > 0) {
            return Arrays.toString(response);
        }

        return "Error - No choice found.";

    }

}
