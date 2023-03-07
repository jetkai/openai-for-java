package io.github.jetkai.openai.api.data.completion.chat;

import io.github.jetkai.openai.api.data.completion.chat.message.ChatCompletionMessageData;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

/**
 * ChatCompletionBuilderImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 07/03/2023}
 */
final class ChatCompletionBuilderImpl implements ChatCompletionData.Builder {

    /**
     * model
     * string
     * Required
     * Default:gpt-3.5-turbo
     * <p>
     * ID of the model to use. Currently, only gpt-3.5-turbo and gpt-3.5-turbo-0301 are supported.
     */
    String model;

    /**
     * messages
     * array
     * Required
     * <p>
     * The messages to generate chat completions for, in the chat format.
     */
    List<ChatCompletionMessageData> messages;

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
    double temperature;

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
    int topP;

    /**
     * n
     * integer
     * Optional
     * Defaults to 1
     * <p>
     * How many chat completion choices to generate for each input message.
     */
    int n;

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
    boolean stream;

    /**
     * stop
     * string or array
     * Optional
     * Defaults to null
     * <p>
     * Up to 4 sequences where the API will stop generating further tokens.
     */
    List<String> stop;

    /**
     * max_tokens
     * integer
     * Optional
     * Defaults to inf
     * <p>
     * The maximum number of tokens allowed for the generated answer. By default, the number
     * of tokens the model can return will be (4096 - prompt tokens).
     */
    int maxTokens;

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
    int presencePenalty;

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
    int frequencyPenalty;

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
    Map<Object, Object> logitBias;

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
    String user;

    public ChatCompletionBuilderImpl() { }

    @Override
    public ChatCompletionData.Builder setModel(String model) {
        requireNonNull(model);
        this.model = model;
        return this;
    }

    @Override
    public ChatCompletionData.Builder setMessages(List<ChatCompletionMessageData> messages) {
        requireNonNull(messages);
        this.messages = messages;
        return this;
    }

    @Override
    public ChatCompletionData.Builder setTemperature(double temperature) {
        this.temperature = temperature;
        return this;
    }

    @Override
    public ChatCompletionData.Builder setUser(String user) {
        requireNonNull(user);
        this.user = user;
        return this;
    }

    @Override
    public ChatCompletionData.Builder setN(int n) {
        this.n = n;
        return this;
    }

    @Override
    public ChatCompletionData.Builder setLogitBias(Map<Object, Object> logitBias) {
        requireNonNull(logitBias);
        this.logitBias = logitBias;
        return this;
    }

    @Override
    public ChatCompletionData.Builder setFrequencyPenalty(int frequencyPenalty) {
        this.frequencyPenalty = frequencyPenalty;
        return this;
    }

    @Override
    public ChatCompletionData.Builder setTopP(int topP) {
        this.topP = topP;
        return this;
    }

    @Override
    public ChatCompletionData.Builder setStream(boolean stream) {
        this.stream = stream;
        return this;
    }

    @Override
    public ChatCompletionData.Builder setStop(String stop) {
        requireNonNull(stop);
        this.stop = Collections.singletonList(stop);
        return this;
    }

    @Override
    public ChatCompletionData.Builder setStop(List<String> stop) {
        requireNonNull(stop);
        this.stop = stop;
        return this;
    }

    @Override
    public ChatCompletionData.Builder setMaxTokens(int maxTokens) {
        this.maxTokens = maxTokens;
        return this;
    }

    @Override
    public ChatCompletionData.Builder setPresencePenalty(int presencePenalty) {
        this.presencePenalty = presencePenalty;
        return this;
    }

    @Override
    public ChatCompletionData build() {
        return ChatCompletionImpl.create(this);
    }

}