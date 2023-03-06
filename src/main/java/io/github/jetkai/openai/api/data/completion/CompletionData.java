package io.github.jetkai.openai.api.data.completion;

import io.github.jetkai.openai.api.impl.chat.CompletionBuilderImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * CompletionData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.1
 * {@code - 05/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@SuppressWarnings("unused")
public abstract class CompletionData {

    public CompletionData() { }

    public static CompletionData create() {
        return builder().build();
    }
    public static CompletionData.Builder builder() {
        return new CompletionBuilderImpl();
    }

    public static CompletionData create(String model, String prompt) {
        return builder().setModel(model)
                .setPrompt(prompt)
                .build();
    }

    public static CompletionData create(String model, List<String> prompt) {
        return builder().setModel(model)
                .setPrompt(prompt)
                .build();
    }

    public static CompletionData translation(String message, String toLanguage) {
        return builder()
                .setPrompt("Respond back to me in " + toLanguage + ":\n\n" + message)
                .setModel("text-davinci-003").build();
    }

    public static CompletionData translation(String message, String fromLanguage, String toLanguage) {
        return  builder()
                .setPrompt("Translate from " + fromLanguage + " to " + toLanguage + ":\n\n" + message)
                .setModel("text-davinci-003").build();
    }

    public interface Builder {
        Builder setEcho(boolean echo);
        Builder setLogprobs(String logprobs);
        Builder setMaxTokens(int maxTokens);
        Builder setModel(String model);
        Builder setN(int n);
        Builder setPrompt(List<String> prompt);
        Builder setPrompt(String prompt);
        Builder setStop(String stop);
        Builder setStop(List<String> stop);
        Builder setStream(boolean stream);
        Builder setTemperature(double temperature);
        Builder setTopP(double topP);
        Builder setSuffix(String suffix);
        Builder setFrequencyPenalty(double frequencyPenalty);
        Builder setPresencePenalty(double presencePenalty);
        Builder setBestOf(int bestOf);
        Builder setLogitBias(Map<Object, Object> logitBias);
        Builder setUser(String user);
        CompletionData build();
    }

    public abstract Boolean getEcho();
    public abstract String getLogprobs();
    public abstract Integer getMaxTokens();
    public abstract String getModel();
    public abstract Integer getN();
    public abstract List<String> getPrompt();
    public abstract List<String> getStop();
    public abstract Boolean isStream();
    public abstract Double getTemperature();
    public abstract Double getTopP();
    public abstract String getSuffix();
    public abstract Double getFrequencyPenalty();
    public abstract Double getPresencePenalty();
    public abstract Integer getBestOf();
    public abstract Map<Object, Object> getLogitBias();
    public abstract String getUser();

    public abstract Optional<Boolean> echo();
    public abstract Optional<String> logprobs();
    public abstract Optional<Integer> maxTokens();
    public abstract Optional<String> model();
    public abstract Optional<Integer> n();
    public abstract Optional<List<String>> prompt();
    public abstract Optional<List<String>> stop();
    public abstract Optional<Boolean> stream();
    public abstract Optional<Double> temperature();
    public abstract Optional<Double> topP();
    public abstract Optional<String> suffix();
    public abstract Optional<Double> frequencyPenalty();
    public abstract Optional<Double> presencePenalty();
    public abstract Optional<Integer> bestOf();
    public abstract Optional<Map<Object, Object>> logitBias();
    public abstract Optional<String> user();

}
