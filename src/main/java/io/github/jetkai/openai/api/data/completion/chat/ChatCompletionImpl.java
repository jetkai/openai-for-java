package io.github.jetkai.openai.api.data.completion.chat;

import io.github.jetkai.openai.api.data.completion.chat.message.ChatCompletionMessageData;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * ChatCompletionImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 07/03/2023}
 */
final class ChatCompletionImpl extends ChatCompletionData {

    private final String model;
    private final List<ChatCompletionMessageData> messages;
    private final double temperature;
    private final double topP;
    private final int n;
    private final boolean stream;
    private final List<String> stop;
    private final int maxTokens;
    private final double presencePenalty;
    private final double frequencyPenalty;
    private final Map<Object, Object> logitBias;
    private final String user;

    static ChatCompletionImpl create(ChatCompletionBuilderImpl builder) {
        return new ChatCompletionImpl(builder);
    }

    private ChatCompletionImpl(ChatCompletionBuilderImpl builder) {
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
    public String getModel() {
        return this.model;
    }

    @Override
    public List<ChatCompletionMessageData> getMessages() {
        return this.messages;
    }

    @Override
    public double getTemperature() {
        return this.temperature;
    }

    @Override
    public String getUser() {
        return this.user;
    }

    @Override
    public int getN() {
        return this.n;
    }

    @Override
    public Map<Object, Object> getLogitBias() {
        return this.logitBias;
    }

    @Override
    public double getFrequencyPenalty() {
        return this.frequencyPenalty;
    }

    @Override
    public double getTopP() {
        return this.topP;
    }

    @Override
    public boolean isStream() {
        return this.stream;
    }

    @Override
    public List<String> getStop() {
        return this.stop;
    }

    @Override
    public int getMaxTokens() {
        return this.maxTokens;
    }

    @Override
    public double getPresencePenalty() {
        return this.presencePenalty;
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
    public Optional<Double> frequencyPenalty() {
        return Optional.of(this.frequencyPenalty);
    }

    @Override
    public Optional<Double> topP() {
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
    public Optional<Double> presencePenalty() {
        return Optional.of(this.presencePenalty);
    }

}