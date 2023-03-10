import io.github.jetkai.openai.api.CreateChatCompletion;
import io.github.jetkai.openai.api.data.completion.chat.ChatCompletionData;
import io.github.jetkai.openai.api.data.completion.chat.message.ChatCompletionMessageData;
import io.github.jetkai.openai.api.data.completion.response.CompletionResponseData;
import io.github.jetkai.openai.openai.OpenAI;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CreateChatCompletionTest
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.1
 * @created 02/03/2023
 * @last-update 03/03/2023
 */
public class CreateChatCompletionTest {

    @Test
    void createChatCompletionTest() {
        //Grab OpenAI API key from system environment variables (gradle.properties)
        String apiKey = System.getenv("OPEN_AI_API_KEY");
        String organization = System.getenv("OPEN_AI_ORGANIZATION");
        assertNotNull(apiKey);
        assertNotNull(organization);

        //List of Messages that you would like to send to the Chat Bot
        List<ChatCompletionMessageData> messages = new ArrayList<>();

        //Create message object, this will contain the data we want to send to ExampleChatGPT
        ChatCompletionMessageData message = ChatCompletionMessageData.builder()
                //The role of the user
                .setRole("user")
                //Message that you would like to send to OpenAI ExampleChatGPT
                .setContent("Hello!")
                .build();

        assertAll(
                () -> assertNotNull(ChatCompletionMessageData.create(message.getContent())),
                () -> assertNotNull(ChatCompletionMessageData.create(message.getRole(), message.getContent()))
        );


        //Add message to the messages list
        messages.add(message);

        //Completion Data, ready to send to the OpenAI Api
        ChatCompletionData completion = ChatCompletionData.builder()
                //ID of the model to use. Currently, only gpt-3.5-turbo and gpt-3.5-turbo-0301 are supported.
                .setModel("gpt-3.5-turbo")
                //The messages to generate chat completions for, in the chat format.
                .setMessages(messages)
                .setTemperature(1.0)
                .setTopP(1.0)
                .setN(1)
                .setStream(false)
                .setMaxTokens(100)
                .setPresencePenalty(0.1)
                .setFrequencyPenalty(0.1)
                .setUser("user")
                .build();

        assertAll(
                () -> assertNotNull(ChatCompletionData.create(completion.getModel(), messages)),
                () -> assertNotNull(ChatCompletionData.create(messages))
        );

        //Create OpenAI instance using API key & organization
        //Organization is optional
        OpenAI openAI = OpenAI.builder()
                .setApiKey(apiKey)
                .setOrganization(organization)
                .createChatCompletion(completion)
                .setTimeout(Duration.ofSeconds(90))
                .build()
                .sendRequest();

        assertAll("Create Chat Completion Test", () -> {
            assertNotNull(openAI);

            //Call the CreateChatCompletion API from OpenAI & create instance
            Optional<CreateChatCompletion> optionalChatCompletion = openAI.chatCompletion();
            assertFalse(optionalChatCompletion.isEmpty());

            //Additionally check the getter method is not null
            assertNotNull(openAI.getChatCompletion());

            CreateChatCompletion createChatCompletion = optionalChatCompletion.get();

            //Data structure example
            boolean skipTestApiDown = false;
            CompletionResponseData responseData = null;
            try {
                responseData = createChatCompletion.asData();
            } catch (IllegalStateException e) {
                if(e.getMessage().contains("That model is currently overloaded with other requests.")) {
                    skipTestApiDown = true;
                }
            }
            if(skipTestApiDown) {
                return;
            }
            assertNotNull(responseData);

            //Test all
            assertNotNull(createChatCompletion.asStringArray());
            assertNotNull(createChatCompletion.asChatResponseData());
            assertNotNull(createChatCompletion.asSentences());
            assertNotNull(createChatCompletion.asNormalizedSentences(2048));
            assertNotNull(createChatCompletion.asNormalizedSentences(1));
            assertNotNull(createChatCompletion.asText());

            //Json example
            String json = createChatCompletion.asJson();
            assertNotNull(json);
            assertFalse(json.isEmpty());
        });
    }

}
