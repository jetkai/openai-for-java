package io.github.jetkai.openai.api.data.translation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jetkai.openai.api.data.completion.CompletionData;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@SuppressWarnings("unused")
public class TranslationData extends CompletionData {

    public TranslationData() {
        super();
    }

    public static TranslationData simplified(String message, String toLanguage) {
        return (TranslationData) new TranslationData()
                .setPrompt("Respond back to me in " + toLanguage + ":\n\n" + message)
                .setModel("text-davinci-003");
    }

    public static TranslationData simplified(String message, String fromLanguage, String toLanguage) {
        return (TranslationData) new TranslationData()
                .setPrompt("Translate from " + fromLanguage + " to " + toLanguage + ":\n\n" + message)
                .setModel("text-davinci-003");
    }

}
