package io.github.jetkai.openai.api;

import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.data.completion.CompletionData;
import io.github.jetkai.openai.api.data.completion.response.CompletionChoiceData;
import io.github.jetkai.openai.api.data.completion.response.CompletionResponseData;
import io.github.jetkai.openai.net.OpenAIEndpoints;
import io.github.jetkai.openai.net.RequestBuilder;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * CreateCompletion
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * {@code - 03/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
public class CreateCompletion implements ApiInterface {

    /**
     * HttpResponse from OpenAI
     */
    private final AtomicReference<HttpResponse<String>> response;

    /**
     * The OpenAI instance
     */
    private final OpenAI openAI;

    /**
     * The endpoint that handleHttpResponse calls
     */
    private final OpenAIEndpoints endpoint;

    /**
     * The data that we are going to be sending through the POST request
     */
    private final CompletionData completion;

    /**
     * Stored object of the data that has been deserialized from the OpenAI response
     */
    private CompletionResponseData data;

    /**
     * CreateCompletion
     * @param openAI - The OpenAI instance
     * @param completion - The completion data specified
     */
    public CreateCompletion(OpenAI openAI, CompletionData completion) {
        this.openAI = openAI;
        this.completion = completion;
        this.endpoint = OpenAIEndpoints.CREATE_COMPLETION;
        this.response = new AtomicReference<>();
        this.initialize();
    }

    /**
     * Initialize
     * Sends a HttpRequest & handles the response from OpenAI's API
     */
    private void initialize() {
        HttpResponse<String> httpResponse = response.get();
        if(httpResponse == null || httpResponse.body() == null || httpResponse.body().isEmpty()) {
            this.handleHttpResponse();
        }
    }

    /**
     * Reinitialize
     * If the HttpRequest/Response to OpenAI's API needs to be restarted, this will do that
     * @return CreateCompletion
     */
    public CreateCompletion reinitialize() {
        this.data = null;
        this.handleHttpResponse();
        return this;
    }

    /**
     * HandleHttpResponse
     * This method will update the HttpResponse<String> response field with data from OpenAI
     * response.get().body() can then be called to retrieve the JSON response from OpenAI
     */
    private void handleHttpResponse() {
        openAI.getHttpClientInstance().getResponse(completion, new RequestBuilder() {
            @Override
            public HttpRequest request(Object data) {
                return buildPostRequest(endpoint.uri(), ((CompletionData) data).toJson(),
                        openAI.getApiKey(), openAI.getOrganization());
            }
        }).thenAccept(this.response::set).join();
    }

    /**
     * asSentences
     * @return - {@code List<String>} containing sentences
     */
    @SuppressWarnings("unused")
    public List<String> asSentences() {
        List<String> sentences = new ArrayList<>();
        StringBuilder sentenceBuilder = new StringBuilder();
        String text = asText();

        if(text == null) {
            return null;
        }
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
    @SuppressWarnings("unused")
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
    @SuppressWarnings("unused")
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

        if(this.data == null) {
            CompletionResponseData responseData = deserialize();
            if (responseData == null) {
                return null;
            }
            this.data = responseData;
        }

        StringBuilder builder = new StringBuilder();
        List<CompletionChoiceData> choiceList = data.getChoices();
        if(choiceList == null || choiceList.isEmpty()) {
            return null;
        }

        for (CompletionChoiceData choice : choiceList) {
            if (choice == null) {
                continue;
            }
            String text = choice.getText();
            if (text == null) {
                continue;
            }
            builder.append(text);
        }

        return builder.toString();
    }

    /**
     * asData
     * @return CompletionResponseData
     */
    @SuppressWarnings("unused")
    public CompletionResponseData asData() {
        if(this.data == null) {
            CompletionResponseData completion = deserialize();
            if (completion == null) {
                return null;
            }
            this.data = completion;
        }
        return this.data;
    }

    /**
     * asJson
     * @return String (JSON)
     */
    @Override
    public String asJson() {
        if(this.data == null) {
            CompletionResponseData completion = deserialize();
            if (completion == null) {
                return null;
            }
            this.data = completion;
        }
        return this.data.toJson();
    }

    /**
     * deserializes
     * Parses the JSON response from OpenAI and deserializes the string to the below data structure
     * @return CompletionResponseData
     */
    private CompletionResponseData deserialize() {
        if(this.data == null) {
            CompletionResponseData completion = JacksonJsonDeserializer.parseData(
                    CompletionResponseData.class, this.getRawJsonResponse()
            );
            if (completion == null) {
                return null;
            }
            this.data = completion;
        }
        return this.data;
    }

    /**
     * getResponse
     * The response from OpenAI
     * @return {@code AtomicReference<HttpResponse<String>>}
     */
    @Override
    public AtomicReference<HttpResponse<String>> getHttpResponse() {
        return response;
    }

    /**
     * getBody
     * @return String (JSON from OpenAI response)
     */
    @Override
    public String getRawJsonResponse() {
        return String.valueOf(response.get().body());
    }

    /**
     * getEndpoint
     * @return OpenAIEndpoints (The endpoint that handleHttpResponse calls)
     */
    @Override
    public OpenAIEndpoints getEndpoint() {
        return endpoint;
    }

    @Override
    public CompletionData getRequestData() {
        return completion;
    }

}
