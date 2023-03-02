package io.github.jetkai.chatgpt.api.data.translation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@SuppressWarnings("unused")
public class TranslationResponseData {

    private String text;

    public TranslationResponseData() { }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
