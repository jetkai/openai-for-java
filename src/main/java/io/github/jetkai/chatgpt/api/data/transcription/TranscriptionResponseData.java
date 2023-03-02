package io.github.jetkai.chatgpt.api.data.transcription;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@SuppressWarnings("unused")
public class TranscriptionResponseData {

    private String text;

    public TranscriptionResponseData() { }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
