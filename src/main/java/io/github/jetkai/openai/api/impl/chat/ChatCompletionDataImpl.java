package io.github.jetkai.openai.api.impl.chat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jetkai.openai.api.data.completion.chat.ChatCompletionData;
import io.github.jetkai.openai.api.data.completion.chat.ChatCompletionMessageData;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * ChatCompletionDataImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.1
 * {@code - 05/03/2023}
 * @since 1.0.0
 * {@code - 05/03/2023}
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@SuppressWarnings("unused")
public class ChatCompletionDataImpl extends ChatCompletionData {

    /**
     * model
     * string
     * Required
     * Default:gpt-3.5-turbo
     * <p>
     * ID of the model to use. Currently, only gpt-3.5-turbo and gpt-3.5-turbo-0301 are supported.
     */
    @JsonProperty("model")
    private final String model;

    /**
     * messages
     * array
     * Required
     * <p>
     * The messages to generate chat completions for, in the chat format.
     */
    @JsonProperty("messages")
    private final List<ChatCompletionMessageData> messages;

    /**
     * temperature
     * number
     * Optional
     * Defaults to 1
     * <p>
     * What sampling temperature to use, between 0 and 2. Higher values like 0.8 will make the output more random, while lower values like 0.2 will make it more focused and deterministic.
     * <p>
     * We generally recommend altering this or top_p but not both.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("temperature")
    private final double temperature;

    /**
     * top_p
     * number
     * Optional
     * Defaults to 1
     * <p>
     * An alternative to sampling with temperature, called nucleus sampling, where the model considers the results of the tokens with top_p probability mass. So 0.1 means only the tokens comprising the top 10% probability mass are considered.
     * <p>
     * We generally recommend altering this or temperature but not both.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("top_p")
    private final int topP;

    /**
     * n
     * integer
     * Optional
     * Defaults to 1
     * <p>
     * How many chat completion choices to generate for each input message.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("n")
    private final int n;

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
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("stream")
    private final boolean stream;

    /**
     * stop
     * string or array
     * Optional
     * Defaults to null
     * <p>
     * Up to 4 sequences where the API will stop generating further tokens.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("stop")
    private final List<String> stop;

    /**
     * max_tokens
     * integer
     * Optional
     * Defaults to inf
     * <p>
     * The maximum number of tokens allowed for the generated answer. By default, the number
     * of tokens the model can return will be (4096 - prompt tokens).
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("max_tokens")
    private final int maxTokens;

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
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("presence_penalty")
    private final int presencePenalty;

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
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("frequency_penalty")
    private final int frequencyPenalty;

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
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("logit_bias")
    private final Map<Object, Object> logitBias;

    /**
     * user
     * string
     * Optional
     * <p>
     * A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse.
     * <a href="https://platform.openai.com/docs/guides/safety-best-practices/end-user-ids">
     *     Learn more.
     * </a>
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("user")
    private final String user;

    static ChatCompletionDataImpl create(ChatCompletionDataBuilderImpl builder) {
        return new ChatCompletionDataImpl(builder);
    }

    private ChatCompletionDataImpl(ChatCompletionDataBuilderImpl builder) {
        this.model = builder.model;
        this.n = builder.n;
        this.messages = builder.messages;
        this.presencePenalty = builder.presencePenalty;
        this.frequencyPenalty = builder.frequencyPenalty;
        this.logitBias = builder.logitBias;
        this.maxTokens = builder.maxTokens;
        this.stop = builder.stop;
        this.user = builder.user;
        this.topP = builder.topP;
        this.temperature = builder.temperature;
        this.stream = builder.stream;
    }

    @Override
    public Optional<String> model() {
        return Optional.ofNullable(this.model);
    }

    @Override
    public Optional<List<ChatCompletionMessageData>> messages() {
        return Optional.ofNullable(this.messages);
    }

    @Override
    public Optional<Double> temperature() {
        return Optional.of(this.temperature);
    }

    @Override
    public Optional<String> user() {
        return Optional.ofNullable(this.user);
    }

    @Override
    public Optional<Integer> n() {
        return Optional.of(this.n);
    }

    @Override
    public Optional<Map<Object, Object>> logitBias() {
        return Optional.ofNullable(this.logitBias);
    }

    @Override
    public Optional<Integer> frequencyPenalty() {
        return Optional.of(this.frequencyPenalty);
    }

    @Override
    public Optional<Integer> topP() {
        return Optional.of(this.topP);
    }

    @Override
    public Optional<Boolean> stream() {
        return Optional.of(this.stream);
    }

    @Override
    public Optional<List<String>> stop() {
        return Optional.ofNullable(this.stop);
    }

    @Override
    public Optional<Integer> maxTokens() {
        return Optional.of(this.maxTokens);
    }

    @Override
    public Optional<Integer> presencePenalty() {
        return Optional.of(this.presencePenalty);
    }

}