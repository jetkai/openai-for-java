package io.github.jetkai.openai.api.data.completion.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

/**
 * CompletionMessageData
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
public class CompletionMessageData {

    private String role;
    private String content;

    public CompletionMessageData() { }

    public CompletionMessageData setContent(String content) {
        this.content = content;
        return this;
    }

    public CompletionMessageData setRole(String role) {
        this.role = role;
        return this;
    }

    public String getContent() {
        return content;
    }

    public String getRole() {
        return role;
    }

    @JsonIgnore
    public String toJson() {
        return JacksonJsonDeserializer.valuesAsString(this);
    }

}