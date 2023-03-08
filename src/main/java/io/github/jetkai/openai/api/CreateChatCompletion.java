package io.github.jetkai.openai.api;

import io.github.jetkai.openai.api.data.completion.chat.ChatCompletionData;
import io.github.jetkai.openai.api.data.completion.chat.message.ChatCompletionMessageData;
import io.github.jetkai.openai.api.data.completion.choice.CompletionChoiceData;
import io.github.jetkai.openai.api.data.completion.message.CompletionMessageData;
import io.github.jetkai.openai.api.data.completion.response.CompletionResponseData;
import io.github.jetkai.openai.net.OpenAIEndpoints;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

import java.util.ArrayList;
import java.util.List;

/**
 * CreateChatCompletion
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
public class CreateChatCompletion extends CreateCompletion {

    /**
     * CreateChatCompletion
     * @param completion - The completion data specified
     */
    public CreateChatCompletion(ChatCompletionData completion) {
        super(JacksonJsonDeserializer.valuesAsString(completion), OpenAIEndpoints.CREATE_CHAT_COMPLETION);
    }

    /**
     * asChatResponseData
     * @return ChatCompletionMessageData
     */
    public ChatCompletionMessageData asChatResponseData() {
        List<ChatCompletionMessageData> chatDataList = asChatResponseDataList();
        if(chatDataList == null || chatDataList.isEmpty()) {
            return null;
        }
        return chatDataList.get(0);
    }

    /**
     * asChatResponseDataList
     * @return {@code List<ChatCompletionMessageData>}
     */
    public List<ChatCompletionMessageData> asChatResponseDataList() {
        if(this.deserializedData == null) {
            this.deserializedData = deserialize(CompletionResponseData.class);
        }
        if (!(this.deserializedData instanceof CompletionResponseData)) {
            return null;
        }
        List<ChatCompletionMessageData> chatDataList = new ArrayList<>();
        for(CompletionChoiceData choice : ((CompletionResponseData) this.deserializedData).getChoices()) {
            if(choice == null) {
                continue;
            }
            //Get the content & role response from ExampleChatGPT
            CompletionMessageData messageData = choice.getMessage();
            if(messageData == null) {
                continue;
            }
            //Set the content & role response from ExampleChatGPT as ChatCompletionMessageData
            ChatCompletionMessageData messageResponse = ChatCompletionMessageData.builder()
                    .setRole(messageData.getRole())
                    .setContent(messageData.getContent())
                    .build();

            //Store the response in an array, so that gpt-3.5 model can keep learning
            chatDataList.add(messageResponse);
        }
        return chatDataList;
    }

}
