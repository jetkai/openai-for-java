package io.github.jetkai.openai.api.data.completion.chat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
public class ChatCompletionData {

    /*
     * ID of the model to use. Currently, only gpt-3.5-turbo and gpt-3.5-turbo-0301 are supported.
     */
    private String model;

    /*
     * The messages to generate chat completions for, in the chat format
     * (https://platform.openai.com/docs/guides/chat/introduction).
     *   messages=[
     *         {"role": "system", "content": "You are a helpful assistant."},
     *         {"role": "user", "content": "Who won the world series in 2020?"},
     *         {"role": "assistant", "content": "The Los Angeles Dodgers won the World Series in 2020."},
     *         {"role": "user", "content": "Where was it played?"}
     *     ]
     */
    private List<ChatCompletionMessageData> messages;

    public ChatCompletionData() { }

    public ChatCompletionData(String model, List<ChatCompletionMessageData> messages) {
        this.model = model;
        this.messages = messages;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setMessages(List<ChatCompletionMessageData> messages) {
        this.messages = messages;
    }

    public String getModel() {
        return model;
    }

    public List<ChatCompletionMessageData> getMessages() {
        return messages;
    }

    @JsonIgnore
    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        String json;
        try {
            json = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

}