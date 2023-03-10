package examples;

import io.github.jetkai.openai.api.CreateChatCompletion;
import io.github.jetkai.openai.api.data.completion.chat.ChatCompletionData;
import io.github.jetkai.openai.api.data.completion.chat.message.ChatCompletionMessageData;
import io.github.jetkai.openai.net.OpenAIEndpoints;
import io.github.jetkai.openai.openai.OpenAI;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * ExampleChatGPT
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.2
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 05/03/2023}
 *
 * <p>
 * Note - This is just a test class, it is recommended to import this project as a library
 * <p>
 * You can get a free API key from <a href="https://platform.openai.com/account/api-keys">here</a>
 */
final class ExampleChatGPT {

    //This is a List that will store all our conversation history - This includes our chat history and the AI's
    private final List<ChatCompletionMessageData> messageHistory = new ArrayList<>();

    public static void main(String[] args) {
        ExampleChatGPT gpt = new ExampleChatGPT();

        System.setProperty("console.encoding", "UTF-8");

        //The first message that we want to send
        String message1 = "こんにちは";
        //The second message that we want to send
        String message2 = "What was the first thing I said?";

        //Response 1 from ChatGPT
        String response1 = gpt.exampleBuilders(message1);
        System.out.println("Sent: " + message1);
        System.out.println("Response: " + response1);

        //Response 2 from ChatGPT
        String response2 = gpt.exampleBuilders(message2);
        System.out.println("Sent: " + message2);
        System.out.println("Response: " + response2);
    }

    private String exampleBuilders(String message) {
        //Create the Message Data object with the message we wish to send
        ChatCompletionMessageData messageData = ChatCompletionMessageData.builder()
                .setRole("user")
                .setContent(message)
                .build();

        //Store the message that we want to send, to the MessageHistory List
        messageHistory.add(messageData);

        //Build the data structure which contains the message history and model information
        ChatCompletionData completionData = ChatCompletionData.builder()
                .setModel("gpt-3.5-turbo")
                .setMessages(messageHistory)
                .build();

        //Sends the request to OpenAI's endpoint & parses the response data
        //Instead of openAI.sendRequest(); you can initialize the request
        //for the class manually - openAI.createChatCompletion().initialize();
        OpenAI openAI = OpenAI.builder()
                .setApiKey(System.getenv("OPEN_AI_API_KEY"))
                .createChatCompletion(completionData)
                .build()
                .sendRequest();

        //Check to see if there is a valid response from OpenAI
        //You can also call Optional<CreateChatCompletion> createChatCompletion = openAI.chatCompletion();
        CreateChatCompletion createChatCompletion = openAI.getChatCompletion();

        //Store chat response from AI, this allows the AI to see the full history of our chat
        //Including both our messages and the AI's messages
        messageHistory.addAll(createChatCompletion.asChatResponseDataList());

        //Parse the response back as plain-text
        return createChatCompletion.asText();
    }

    private String exampleInstanceOrEnum(String message) {
        //Create the Message Data object with the message we wish to send
        ChatCompletionMessageData messageData = ChatCompletionMessageData.create(message);

        //Store the message that we want to send, to the MessageHistory List
        messageHistory.add(messageData);

        //Build the data structure which contains the message history and model information
        ChatCompletionData completionData = ChatCompletionData.create(messageHistory);

        //Sends the request to OpenAI's endpoint & parses the response data
        OpenAI openAI = OpenAI.builder()
                .setApiKey(System.getenv("OPEN_AI_API_KEY"))
                .build();

        //Create instance which will return as the API class that we specified in the enum or .class
        //CreateChatCompletion createChatCompletion = openAI.createInstance(CreateChatCompletion.class, completionData);
        CreateChatCompletion createChatCompletion = openAI.createInstance(
                OpenAIEndpoints.CREATE_CHAT_COMPLETION, completionData
        );

        //Store chat response from AI, this allows the AI to see the full history of our chat
        //Including both our messages and the AI's messages
        messageHistory.addAll(createChatCompletion.asChatResponseDataList());

        //Parse the response back as plain-text
        return createChatCompletion.asText();
    }

    private String exampleHttpClientProxy(String message) {
        //Create the Message Data object with the message we wish to send
        ChatCompletionMessageData messageData = ChatCompletionMessageData.create(message);

        //Store the message that we want to send, to the MessageHistory List
        messageHistory.add(messageData);

        //Build the data structure which contains the message history and model information
        ChatCompletionData completionData = ChatCompletionData.create(messageHistory);

        //Sends the request to OpenAI's endpoint & parses the response data
        //Instead of openAI.sendRequest(); you can initialize the request for the class manually
        //openAI.createChatCompletion().initialize();
        OpenAI openAI = OpenAI.builder()
                .setApiKey(System.getenv("OPEN_AI_API_KEY"))
                .setProxy("1.0.205.87", 8080)
                .setTimeout(Duration.ofSeconds(10))
                .build();

        //Create instance which will return as the API class that we specified in the enum or .class
        //CreateChatCompletion createChatCompletion = openAI.createInstance(CreateChatCompletion.class, completionData);
        CreateChatCompletion createChatCompletion = openAI.createInstance(
                OpenAIEndpoints.CREATE_CHAT_COMPLETION, completionData
        );

        //Store chat response from AI, this allows the AI to see the full history of our chat
        //Including both our messages and the AI's messages
        messageHistory.addAll(createChatCompletion.asChatResponseDataList());

        //Parse the response back as plain-text
        return createChatCompletion.asText();
    }

}
