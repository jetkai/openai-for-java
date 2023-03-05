package io.github.jetkai.openai.api.data.completion.chat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ChatCompletionData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * {@code - 03/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@SuppressWarnings("unused")
public class ChatCompletionData {

    /**
     * model
     * string
     * Required
     * Default:gpt-3.5-turbo
     * <p>
     * ID of the model to use. Currently, only gpt-3.5-turbo and gpt-3.5-turbo-0301 are supported.
     */
    private String model;

    /**
     * messages
     * array
     * Required
     * <p>
     * The messages to generate chat completions for, in the chat format.
     */
    private List<ChatCompletionMessageData> messages;

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
    private double temperature;

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
    private int topP;

    /**
     * n
     * integer
     * Optional
     * Defaults to 1
     * <p>
     * How many chat completion choices to generate for each input message.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int n;

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
    private boolean stream;

    /**
     * stop
     * string or array
     * Optional
     * Defaults to null
     * <p>
     * Up to 4 sequences where the API will stop generating further tokens.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private List<String> stop;

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
    private int maxTokens;

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
    @JsonProperty("presense_penalty")
    private int presensePenalty;

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
    private int frequencyPenalty;

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
    private Map<Object, Object> logitBias;

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
    private String user;

    public ChatCompletionData() { }

    public ChatCompletionData(String model, List<ChatCompletionMessageData> messageHistory) {
        this.model = model;
        this.messages = messageHistory;
    }

    public static ChatCompletionData create(String model, List<ChatCompletionMessageData> messageHistory) {
        return new ChatCompletionData(model, messageHistory);
    }

    public static ChatCompletionData create(List<ChatCompletionMessageData> messageHistory) {
        return new ChatCompletionData("gpt-3.5-turbo", messageHistory);
    }

    public ChatCompletionData setModel(String model) {
        this.model = model;
        return this;
    }

    public ChatCompletionData setMessages(List<ChatCompletionMessageData> messages) {
        this.messages = messages;
        return this;
    }

    public ChatCompletionData setTemperature(double temperature) {
        this.temperature = temperature;
        return this;
    }

    public ChatCompletionData setUser(String user) {
        this.user = user;
        return this;
    }

    public ChatCompletionData setN(int n) {
        this.n = n;
        return this;
    }

    public ChatCompletionData setLogitBias(Map<Object, Object> logitBias) {
        this.logitBias = logitBias;
        return this;
    }

    public ChatCompletionData setFrequencyPenalty(int frequencyPenalty) {
        this.frequencyPenalty = frequencyPenalty;
        return this;
    }

    public ChatCompletionData setTopP(int topP) {
        this.topP = topP;
        return this;
    }

    public ChatCompletionData setStream(boolean stream) {
        this.stream = stream;
        return this;
    }

    public ChatCompletionData setStop(String stop) {
        this.stop = new ArrayList<>(List.of(stop));
        return this;
    }

    public ChatCompletionData setStop(List<String> stop) {
        this.stop = stop;
        return this;
    }

    public ChatCompletionData setMaxTokens(int maxTokens) {
        this.maxTokens = maxTokens;
        return this;
    }

    public ChatCompletionData setPresensePenalty(int presensePenalty) {
        this.presensePenalty = presensePenalty;
        return this;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getUser() {
        return user;
    }

    public int getN() {
        return n;
    }

    public Map<Object, Object> getLogitBias() {
        return logitBias;
    }

    public int getTopP() {
        return topP;
    }

    public int getMaxTokens() {
        return maxTokens;
    }

    public int getFrequencyPenalty() {
        return frequencyPenalty;
    }

    public int getPresensePenalty() {
        return presensePenalty;
    }

    public List<String> getStop() {
        return stop;
    }

    public boolean isStream() {
        return stream;
    }

    public String getModel() {
        return model;
    }

    public List<ChatCompletionMessageData> getMessages() {
        return messages;
    }

    @JsonIgnore
    public String toJson() {
        return JacksonJsonDeserializer.valuesAsString(this);
    }

}