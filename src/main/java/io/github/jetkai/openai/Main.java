package io.github.jetkai.openai;

import io.github.jetkai.openai.api.data.completion.chat.ChatCompletionData;
import io.github.jetkai.openai.api.data.completion.chat.ChatCompletionMessageData;

import java.util.ArrayList;
import java.util.List;

/**
 * Main
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * {@code - 03/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
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

        String response = main.sayHelloToChatGPT();

        if(response.contains("Error")) {
            System.err.println(response);
        } else {
            System.out.println(response);
        }

    }

    /**
     * Example - Say "Hello" to OpenAI
     * @return - ChatGPT-3.5 response from OpenAI
     */
    public String sayHelloToChatGPT() {
        //Grab API Key from .json file
        String apiKey = System.getenv("OPEN_AI_API_KEY");
        String organization = System.getenv("OPEN_AI_ORGANIZATION");
        if(apiKey == null) {
            return "API_KEY environment variable is missing...\n" +
                    "You must set this, or manually type in your API key within new OpenAI(\"YOUR_API_KEY\");";
        }

        /*
         * You can manually type in the API Key instead of loading from the file:
         * OpenAI openAI = new OpenAI("MY_API_KEY");
         * OpenAI openAI = new OpenAI("MY_API_KEY", "MY_ORGANIZATION");
         */
        //Create OpenAI instance using API key loaded from the .json file (example)
        OpenAI openAI = new OpenAI(apiKey, organization);

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
        String response = openAI.createChatCompletion(completion).asText();

        if(response != null && !response.isEmpty()) {
            return response;
        }

        return "Error - No response available.";

    }

}
