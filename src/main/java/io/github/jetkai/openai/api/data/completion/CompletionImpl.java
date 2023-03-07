package io.github.jetkai.openai.api.data.completion;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * CompletionImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 05/03/2023}
 */
final class CompletionImpl extends CompletionData {

    private final String model;
    private final List<String> prompt;
    private final String suffix;
    private final int maxTokens;
    private final double temperature;
    private final double topP;
    private final int n;
    private final boolean stream;
    private final String logprobs;
    private final boolean echo;
    private final List<String> stop;
    private final double presencePenalty;
    private final double frequencyPenalty;
    private final int bestOf;
    private final Map<Object, Object> logitBias;
    private final String user;

    static CompletionImpl create(CompletionBuilderImpl builder) {
        return new CompletionImpl(builder);
    }

    private CompletionImpl(CompletionBuilderImpl builder) {
        this.bestOf = builder.bestOf;
        this.echo = builder.echo;
        this.prompt = builder.prompt;
        this.logitBias = builder.logitBias;
        this.logprobs = builder.logprobs;
        this.maxTokens = builder.maxTokens;
        this.n = builder.n;
        this.presencePenalty = builder.presencePenalty;
        this.stop = builder.stop;
        this.suffix = builder.suffix;
        this.user = builder.user;
        this.model = builder.model;
        this.temperature = builder.temperature;
        this.topP = builder.topP;
        this.stream = builder.stream;
        this.frequencyPenalty = builder.frequencyPenalty;
    }

    @Override
    public Boolean getEcho() {
        return this.echo;
    }

    @Override
    public String getLogprobs() {
        return this.logprobs;
    }

    @Override
    public Integer getMaxTokens() {
        return this.maxTokens;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public Integer getN() {
        return this.n;
    }

    @Override
    public List<String> getPrompt() {
        return this.prompt;
    }

    @Override
    public List<String> getStop() {
        return this.stop;
    }

    @Override
    public Boolean isStream() {
        return this.stream;
    }

    @Override
    public Double getTemperature() {
        return this.temperature;
    }

    @Override
    public Double getTopP() {
        return this.topP;
    }

    @Override
    public String getSuffix() {
        return this.suffix;
    }

    @Override
    public Double getFrequencyPenalty() {
        return this.frequencyPenalty;
    }

    @Override
    public Double getPresencePenalty() {
        return this.presencePenalty;
    }

    @Override
    public Integer getBestOf() {
        return this.bestOf;
    }

    @Override
    public Map<Object, Object> getLogitBias() {
        return this.logitBias;
    }

    @Override
    public String getUser() {
        return this.user;
    }

    @Override
    public Optional<Boolean> echo() {
        return Optional.of(this.echo);
    }

    @Override
    public Optional<String> logprobs() {
        return Optional.ofNullable(this.logprobs);
    }

    @Override
    public Optional<Integer> maxTokens() {
        return Optional.of(this.maxTokens);
    }

    @Override
    public Optional<String> model() {
        return Optional.ofNullable(this.model);
    }

    @Override
    public Optional<Integer> n() {
        return Optional.of(this.n);
    }

    @Override
    public Optional<List<String>> prompt() {
        return Optional.ofNullable(this.prompt);
    }

    @Override
    public Optional<List<String>> stop() {
        return Optional.ofNullable(this.stop);
    }

    @Override
    public Optional<Boolean> stream() {
        return Optional.of(this.stream);
    }

    @Override
    public Optional<Double> temperature() {
        return Optional.of(this.temperature);
    }

    @Override
    public Optional<Double> topP() {
        return Optional.of(this.topP);
    }

    @Override
    public Optional<String> suffix() {
        return Optional.ofNullable(this.suffix);
    }

    @Override
    public Optional<Double> frequencyPenalty() {
        return Optional.of(this.frequencyPenalty);
    }

    @Override
    public Optional<Double> presencePenalty() {
        return Optional.of(this.presencePenalty);
    }

    @Override
    public Optional<Integer> bestOf() {
        return Optional.of(this.bestOf);
    }

    @Override
    public Optional<Map<Object, Object>> logitBias() {
        return Optional.ofNullable(this.logitBias);
    }

    @Override
    public Optional<String> user() {
        return Optional.ofNullable(this.user);
    }
}
