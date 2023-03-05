package io.github.jetkai.openai.api;

import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.data.audio.AudioData;
import io.github.jetkai.openai.api.data.audio.AudioResponseData;
import io.github.jetkai.openai.net.OpenAIEndpoints;
import io.github.jetkai.openai.net.RequestBuilder;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.Normalizer;
import java.util.concurrent.atomic.AtomicReference;

/**
 * CreateTranscription
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * {@code - 03/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
public class CreateTranscription implements ApiInterface {

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
    private final AudioData transcription;

    /**
     * Stored object of the data that has been deserialized from the OpenAI response
     */
    private AudioResponseData data;

    /**
     * CreateTranscription
     * @param openAI - The OpenAI instance
     * @param transcription - The translation data specified
     */
    public CreateTranscription(OpenAI openAI, AudioData transcription) {
        this.openAI = openAI;
        this.transcription = transcription;
        this.endpoint = OpenAIEndpoints.CREATE_AUDIO_TRANSCRIPTION;
        this.response = new AtomicReference<>();
        this.initialize();
    }

    /**
     * CreateTranscription
     * @param openAI - The OpenAI instance
     * @param transcription - The translation data specified
     * @param endpoint - The OpenAI endpoint URI
     */
    public CreateTranscription(OpenAI openAI, AudioData transcription, OpenAIEndpoints endpoint) {
        this.openAI = openAI;
        this.transcription = transcription;
        this.endpoint = endpoint;
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
     * @return CreateTranscription
     */
    public CreateTranscription reinitialize() {
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
        openAI.getHttpClientInstance().getResponse(transcription, new RequestBuilder() {
            @Override
            public HttpRequest request(Object data) {
                return buildMultiDataPost(endpoint.uri(), data, openAI.getApiKey(), openAI.getOrganization());
            }
        }).thenAccept(this.response::set).join();
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
            AudioResponseData transcription = deserialize();
            if (transcription == null) {
                return null;
            }
            this.data = transcription;
        }
        return this.data.getText();
    }

    /**
     * asData
     * @return AudioTranscriptionResponseData
     */
    public AudioResponseData asData() {
        if(this.data == null) {
            AudioResponseData transcription = deserialize();
            if (transcription == null) {
                return null;
            }
            this.data = transcription;
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
            AudioResponseData transcription = deserialize();
            if (transcription == null) {
                return null;
            }
            this.data = transcription;
        }
        return this.data.toJson();
    }

    /**
     * deserializes
     * Parses the JSON response from OpenAI and deserializes the string to the below data structure
     * @return AudioTranscriptionResponseData
     */
    private AudioResponseData deserialize() {
        if(this.data == null) {
            AudioResponseData translation = JacksonJsonDeserializer.parseData(
                    AudioResponseData.class, this.getRawJsonResponse()
            );
            if (translation == null) {
                return null;
            }
            this.data = translation;
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
    public AudioData getRequestData() {
        return transcription;
    }

}
