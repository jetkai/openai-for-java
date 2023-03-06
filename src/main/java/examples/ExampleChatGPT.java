package examples;

import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.CreateChatCompletion;
import io.github.jetkai.openai.api.data.completion.chat.ChatCompletionData;
import io.github.jetkai.openai.api.data.completion.chat.ChatCompletionMessageData;

import java.util.ArrayList;
import java.util.List;

/**
 * ExampleChatGPT
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.1
 * {@code - 05/03/2023}
 * @since 1.0.0
 * {@code - 05/03/2023}
 *
 * <p>
 * Note - This is just a test class, it is recommended to import this project as a library
 * and then call OpenAI openAI = new OpenAI("YOUR_API_KEY", "YOUR_ORGANIZATION");
 * </p>
 */
public class ExampleChatGPT {

    /*
     * You can get a free API key from https://platform.openai.com/account/api-keys
     * private final OpenAI openAI = new OpenAI("YOUR_API_KEY");
     * private final OpenAI openAI = new OpenAI("YOUR_API_KEY", "YOUR_ORGANIZATION");
     */

    //This is a List that will store all our conversation history
    //This includes our chat history and the AI's
    private final List<ChatCompletionMessageData> messageHistory = new ArrayList<>();

    public static void main(String[] args) {
        ExampleChatGPT gpt = new ExampleChatGPT();

        //The first message that we want to send
        String message1 = "Hello ChatGPT!";
        //The second message that we want to send
        String message2 = "What was the last thing I just said?";

        //Response 1 from ChatGPT
        String response1 = gpt.communicate(message1);
        System.out.println("Sent: " + message1);
        System.out.println("Response: " + response1);

        //Response 2 from ChatGPT
        String response2 = gpt.communicate(message2);
        System.out.println("Sent: " + message2);
        System.out.println("Response: " + response2);
    }

    private String communicate(String message) {
        //Create the Message Data object with the message we wish to send
        //Alternatively can use a builder
        //ChatCompletionMessageData messageData ChatCompletionMessageData.builder()
        //        .setRole("user")
        //        .setContent(message)
        //        .build();
        ChatCompletionMessageData messageData = ChatCompletionMessageData.create(message);

        //Store the message that we want to send, to the MessageHistory List
        messageHistory.add(messageData);

        //Build the data structure which contains the message history and model information
        //Can also use a builder instead
        //ChatCompletionData completionData = ChatCompletionData.builder()
        //        .setModel("gpt-3.5-turbo")
        //        .setMessages(messageHistory)
        //        .build();
        ChatCompletionData completionData = ChatCompletionData.create(messageHistory);

        OpenAI openAI = OpenAI.builder()
                .setApiKey(System.getenv("OPEN_AI_API_KEY"))
                .createChatCompletion(completionData)
                .build();

        //Sends the request to OpenAI's endpoint & parses the response data
        //Instead of openAI.sendRequest(); you can initialize the request for the class manually
        //openAI.createChatCompletion().initialize();
        openAI.sendRequest();

        //Check to see if there is a valid response from OpenAI
        //You can also call Optional<CreateChatCompletion> createChatCompletion = openAI.chatCompletion();
        CreateChatCompletion createChatCompletion = openAI.createChatCompletion();

        //Store chat response from AI, this allows the AI to see the full history of our chat
        //Including both our messages and the AI's messages
        messageHistory.addAll(createChatCompletion.asChatResponseDataList());

        //Parse the response back as plain-text & replace \n & ascii characters (Šťŕĭńġ -> String)
        return createChatCompletion.asNormalizedText();
    }

}
