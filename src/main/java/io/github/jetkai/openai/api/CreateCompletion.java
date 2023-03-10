package io.github.jetkai.openai.api;

import io.github.jetkai.openai.api.data.completion.CompletionData;
import io.github.jetkai.openai.api.data.completion.choice.CompletionChoiceData;
import io.github.jetkai.openai.api.data.completion.message.CompletionMessageData;
import io.github.jetkai.openai.api.data.completion.response.CompletionResponseData;
import io.github.jetkai.openai.net.OpenAIEndpoints;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CreateCompletion
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
public class CreateCompletion extends OAPI {

    /**
     * CreateCompletion
     * @param completion - The completion data specified
     */
    public CreateCompletion(CompletionData completion) {
        super(JacksonJsonDeserializer.valuesAsString(completion),
                OpenAIEndpoints.CREATE_COMPLETION, HttpRequestType.POST);
    }

    public CreateCompletion(Object completion, OpenAIEndpoints endpoint) {
        super(completion, endpoint, HttpRequestType.POST);
    }

    /**
     * asSentences
     * @return - {@code List<String>} containing sentences
     */
    public List<String> asSentences() {
        List<String> sentences = new ArrayList<>();
        String text = asText();
        Objects.requireNonNull(text, "asText() can not be null");

        if(text.contains("\n")) {
            String[] words = text.split("\n");
            sentences.addAll(List.of(words));
        } else {
            sentences.add(text);
        }

        return sentences;
    }

    /**
     * asNormalizedSentences
     * @param maxCharactersPerLine - maximum length before adding new sentence to list
     * @return - {@code List<String>} containing sentences, replacing ascii
     */
    public List<String> asNormalizedSentences(int maxCharactersPerLine) {
        List<String> sentences = new ArrayList<>();
        StringBuilder sentenceBuilder = new StringBuilder();
        String normalizedResponse = asNormalizedText();

        if(normalizedResponse == null) {
            return null;
        }
        if(normalizedResponse.contains(" ")) {
            String[] words = normalizedResponse.split(" ");
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                if(i == words.length - 1) {
                    sentenceBuilder.append(word);
                    sentences.add(sentenceBuilder.toString());
                } else if (sentenceBuilder.length() < maxCharactersPerLine) {
                    sentenceBuilder.append(word).append(" ");
                } else {
                    sentences.add(sentenceBuilder.toString());
                    sentenceBuilder.setLength(0);
                    sentenceBuilder.append(word).append(" ");
                }
            }
        } else {
            sentences.add(normalizedResponse);
        }

        return sentences;
    }
    /**
     * asNormalizedText
     * @return - String with replaced ascii characters, and removes "\n"
     */
    public String asNormalizedText() {
        //Replaces any characters that do not match the regex
        String normalized = Normalizer.normalize(this.asText(), Normalizer.Form.NFD);
        return normalized
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll("\n", "");
    }

    /**
     * asText
     * @return - String with the raw text responded back from OpenAI
     */
    public String asText() {
        if(this.deserializedData == null) {
            this.deserializedData = deserialize(CompletionResponseData.class);
        }
        if (!(this.deserializedData instanceof CompletionResponseData)) {
            return null;
        }

        StringBuilder builder = new StringBuilder();
        List<CompletionChoiceData> choiceList = ((CompletionResponseData) this.deserializedData).getChoices();
        if(choiceList == null || choiceList.isEmpty()) {
            return null;
        }

        for (CompletionChoiceData choice : choiceList) {
            if (choice == null) {
                continue;
            }
            String text = choice.getText();
            if (text == null) {
                CompletionMessageData messageData = choice.getMessage();
                if(messageData == null) {
                    continue;
                }
                text = messageData.getContent();
            }
            if(text == null) {
                continue;
            }
            builder.append(text);
        }

        return builder.toString();
    }

    public String[] asStringArray() {
        if(this.deserializedData == null) {
            this.deserializedData = deserialize(CompletionResponseData.class);
        }
        if (!(this.deserializedData instanceof CompletionResponseData)) {
            return null;
        }
        List<CompletionChoiceData> choiceList = ((CompletionResponseData) this.deserializedData).getChoices();
        String[] choicesText = new String[choiceList.size()];

        for(int i = 0; i < choiceList.size(); i++) {
            CompletionChoiceData completionChoiceData = choiceList.get(i);
            if(completionChoiceData == null) {
                continue;
            }
            String choiceText = completionChoiceData.getText();
            if(choiceText == null || choiceText.isEmpty()) {
                CompletionMessageData messageData = completionChoiceData.getMessage();
                if(messageData == null) {
                    continue;
                }
                choiceText = messageData.getContent();
            }
            if(choiceText == null) {
                continue;
            }
            choicesText[i] = choiceText;
        }

        return choicesText;
    }

    /**
     * asData
     * @return CompletionResponseData
     */
    public CompletionResponseData asData() {
        if(this.deserializedData == null) {
            this.deserializedData = deserialize(CompletionResponseData.class);
        }
        if (!(this.deserializedData instanceof CompletionResponseData)) {
            return null;
        }
        return (CompletionResponseData) this.deserializedData;
    }


    /**
     * asJson
     * @return String (JSON)
     */
    @Override
    public String asJson() {
        if(this.deserializedData == null) {
            this.deserializedData = deserialize(CompletionResponseData.class);
        }
        if (!(this.deserializedData instanceof CompletionResponseData)) {
            return null;
        }
        return JacksonJsonDeserializer.valuesAsString(this.deserializedData);
    }

}
