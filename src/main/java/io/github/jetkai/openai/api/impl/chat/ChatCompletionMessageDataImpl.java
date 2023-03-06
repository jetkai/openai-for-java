package io.github.jetkai.openai.api.impl.chat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jetkai.openai.api.data.completion.chat.ChatCompletionMessageData;

import java.util.Optional;

/**
 * ChatCompletionMessageData
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
public class ChatCompletionMessageDataImpl extends ChatCompletionMessageData {

    @JsonProperty("role")
    private final String role;
    @JsonProperty("content")
    private final String content;

    static ChatCompletionMessageDataImpl create(ChatCompletionMessageDataBuilderImpl builder) {
        return new ChatCompletionMessageDataImpl(builder);
    }

    private ChatCompletionMessageDataImpl(ChatCompletionMessageDataBuilderImpl builder) {
        this.role = builder.role;
        this.content = builder.content;
    }

    @Override
    public String getContent() {
        return this.content;
    }

    @Override
    public String getRole() {
        return this.role;
    }

    @Override
    public Optional<String> content() {
        return Optional.ofNullable(this.content);
    }

    @Override
    public Optional<String> role() {
        return Optional.ofNullable(this.role);
    }

}