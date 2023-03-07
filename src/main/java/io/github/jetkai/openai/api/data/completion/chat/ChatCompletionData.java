package io.github.jetkai.openai.api.data.completion.chat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jetkai.openai.api.data.completion.chat.message.ChatCompletionMessageData;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * ChatCompletionData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@JsonSerialize
public abstract class ChatCompletionData {
    public ChatCompletionData() { }

    public static ChatCompletionData create() {
        return builder().build();
    }

    public static ChatCompletionData.Builder builder() {
        return new ChatCompletionBuilderImpl();
    }

    public static ChatCompletionData create(String model, List<ChatCompletionMessageData> messageHistory) {
        return builder().setModel(model)
                .setMessages(messageHistory)
                .build();
    }

    public static ChatCompletionData create(List<ChatCompletionMessageData> messageHistory) {
        return builder().setModel("gpt-3.5-turbo")
                .setMessages(messageHistory)
                .build();
    }

    public interface Builder {
        Builder setModel(String model);
        Builder setMessages(List<ChatCompletionMessageData> messages);
        Builder setTemperature(double temperature);
        Builder setUser(String user);
        Builder setN(int n);
        Builder setLogitBias(Map<Object, Object> logitBias);
        Builder setFrequencyPenalty(int frequencyPenalty);
        Builder setTopP(int topP);
        Builder setStream(boolean stream);
        Builder setStop(String stop);
        Builder setStop(List<String> stop);
        Builder setMaxTokens(int maxTokens);
        Builder setPresencePenalty(int presencePenalty);
        ChatCompletionData build();
    }

    /**
     * model
     * string
     * Required
     * Default:gpt-3.5-turbo
     * <p>
     * ID of the model to use. Currently, only gpt-3.5-turbo and gpt-3.5-turbo-0301 are supported.
     * @return model
     */
    @JsonProperty("model")
    public abstract String getModel();

    /**
     * messages
     * array
     * Required
     * <p>
     * The messages to generate chat completions for, in the chat format.
     * @return messages
     */
    @JsonProperty("messages")
    public abstract List<ChatCompletionMessageData> getMessages();

    /**
     * temperature
     * number
     * Optional
     * Defaults to 1
     * <p>
     * What sampling temperature to use, between 0 and 2. Higher values like 0.8 will make the output more random, while lower values like 0.2 will make it more focused and deterministic.
     * <p>
     * We generally recommend altering this or top_p but not both.
     * @return temperature
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("temperature")
    public abstract Double getTemperature();

    /**
     * user
     * string
     * Optional
     * <p>
     * A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse.
     * <a href="https://platform.openai.com/docs/guides/safety-best-practices/end-user-ids">
     *     Learn more.
     * </a>
     * @return user
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("user")
    public abstract String getUser();

    /**
     * n
     * integer
     * Optional
     * Defaults to 1
     * <p>
     * How many chat completion choices to generate for each input message.
     * @return n
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("n")
    public abstract Integer getN();

    /**
     * logit_bias
     * map
     * Optional
     * Defaults to null
     * <p>
     * Modify the likelihood of specified tokens appearing in the completion.
     * <p>
     * Accepts a json object that maps tokens (specified by their token ID in the tokenizer) to an
     * associated bias value from -100 to 100. Mathematically, the bias is added to the logits generated
     * by the model prior to sampling. The exact effect will vary per model, but values between -1 and 1
     * should decrease or increase likelihood of selection; values like -100 or 100 should result in a
     * ban or exclusive selection of the relevant token.
     * @return logitBias
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("logit_bias")
    public abstract Map<Object, Object> getLogitBias();

    /**
     * frequency_penalty
     * number
     * Optional
     * Defaults to 0
     * <p>
     * Number between -2.0 and 2.0. Positive values penalize new tokens based on their existing
     * frequency in the text so far, decreasing the model's likelihood to repeat the same line verbatim.
     * <a href="https://platform.openai.com/docs/api-reference/parameter-details">
     *     See more information about frequency and presence penalties.
     * </a>
     * @return frequencyPenalty
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("frequency_penalty")
    public abstract Integer getFrequencyPenalty();

    /**
     * top_p
     * number
     * Optional
     * Defaults to 1
     * <p>
     * An alternative to sampling with temperature, called nucleus sampling, where the model considers the results of the tokens with top_p probability mass. So 0.1 means only the tokens comprising the top 10% probability mass are considered.
     * <p>
     * We generally recommend altering this or temperature but not both.
     * @return topP
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("top_p")
    public abstract Integer getTopP();

    /**
     * stream
     * boolean
     * Optional
     * Defaults to false
     * <p>
     * If set, partial message deltas will be sent, like in ExampleChatGPT.
     * Tokens will be sent as data-only server-sent events as they become available,
     * with the stream terminated by a data: [DONE] message.
     * <a href="https://developer.mozilla.org/en-US/docs/Web/API/Server-sent_events/Using_server-sent_events#Event_stream_format">
     *     Server Sent Events
     * </a>
     * @return stream
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("stream")
    public abstract Boolean isStream();

    /**
     * stop
     * string or array
     * Optional
     * Defaults to null
     * <p>
     * Up to 4 sequences where the API will stop generating further tokens.
     * @return stop
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("stop")
    public abstract List<String> getStop();

    /**
     * max_tokens
     * integer
     * Optional
     * Defaults to inf
     * <p>
     * The maximum number of tokens allowed for the generated answer. By default, the number
     * of tokens the model can return will be (4096 - prompt tokens).
     * @return maxTokens
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("max_tokens")
    public abstract Integer getMaxTokens();

    /**
     * presence_penalty
     * number
     * Optional
     * Defaults to 0
     * <p>
     * Number between -2.0 and 2.0. Positive values penalize new tokens based on whether they
     * appear in the text so far, increasing the model's likelihood to talk about new topics.
     * <a href="https://platform.openai.com/docs/api-reference/parameter-details">
     *     See more information about frequency and presence penalties.
     * </a>
     * @return presencePenalty
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("presence_penalty")
    public abstract Integer getPresencePenalty();

    public abstract Optional<String> model();
    public abstract Optional<List<ChatCompletionMessageData>> messages();
    public abstract Optional<Double> temperature();
    public abstract Optional<String> user();
    public abstract Optional<Integer> n();
    public abstract Optional<Map<Object, Object>> logitBias();
    public abstract Optional<Integer> frequencyPenalty();
    public abstract Optional<Integer> topP();
    public abstract Optional<Boolean> stream();
    public abstract Optional<List<String>> stop();
    public abstract Optional<Integer> maxTokens();
    public abstract Optional<Integer> presencePenalty();

}