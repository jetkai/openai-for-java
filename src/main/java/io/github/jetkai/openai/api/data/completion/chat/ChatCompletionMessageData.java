package io.github.jetkai.openai.api.data.completion.chat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

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
public class ChatCompletionMessageData {
    private String role;
    private String content;

    public ChatCompletionMessageData() { }

    public ChatCompletionMessageData setContent(String content) {
        this.content = content;
        return this;
    }

    public ChatCompletionMessageData setRole(String role) {
        this.role = role;
        return this;
    }

    public String getRole() {
        return role;
    }

    public String getContent() {
        return content;
    }

    @JsonIgnore
    public String toJson() {
        return JacksonJsonDeserializer.valuesAsString(this);
    }

}