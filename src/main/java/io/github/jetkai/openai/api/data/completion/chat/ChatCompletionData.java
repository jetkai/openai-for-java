package io.github.jetkai.openai.api.data.completion.chat;

import io.github.jetkai.openai.api.impl.chat.ChatCompletionDataBuilderImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * ChatCompletionData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.1
 * {@code - 05/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@SuppressWarnings("unused")
public abstract class ChatCompletionData {
    public ChatCompletionData() { }

    public static ChatCompletionData newAudioData() {
        return builder().build();
    }

    public static ChatCompletionData create() {
        return builder().build();
    }
    public static ChatCompletionData.Builder builder() {
        return new ChatCompletionDataBuilderImpl();
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

    public abstract String getModel();
    public abstract List<ChatCompletionMessageData> getMessages();
    public abstract Double getTemperature();
    public abstract String getUser();
    public abstract Integer getN();
    public abstract Map<Object, Object> getLogitBias();
    public abstract Integer getFrequencyPenalty();
    public abstract Integer getTopP();
    public abstract Boolean isStream();
    public abstract List<String> getStop();
    public abstract Integer getMaxTokens();
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