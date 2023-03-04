package io.github.jetkai.openai.api;

import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.data.transcription.TranscriptionData;
import io.github.jetkai.openai.api.data.transcription.TranscriptionResponseData;
import io.github.jetkai.openai.net.OpenAIEndpoints;
import io.github.jetkai.openai.net.RequestBuilder;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.atomic.AtomicReference;

/**
 * CreateTranscription
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * @created 02/03/2023
 * @last-update 03/03/2023
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
    private final TranscriptionData transcription;

    /**
     * Stored object of the data that has been deserialized from the OpenAI response
     */
    private TranscriptionResponseData data;

    /**
     * CreateTranscription
     * @param openAI - The OpenAI instance
     * @param transcription - The translation data specified
     */
    public CreateTranscription(OpenAI openAI, TranscriptionData transcription) {
        this.openAI = openAI;
        this.transcription = transcription;
        this.endpoint = OpenAIEndpoints.CREATE_TRANSCRIPTION;
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
     * asText
     * @return String (Transcription)
     */
    public String asText() {
        if(this.data == null) {
            TranscriptionResponseData transcription = deserialize();
            if (transcription == null) {
                return null;
            }
            this.data = transcription;
        }
        return this.data.getText();
    }

    /**
     * asData
     * @return TranscriptionResponseData
     */
    public TranscriptionResponseData asData() {
        if(this.data == null) {
            TranscriptionResponseData transcription = deserialize();
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
            TranscriptionResponseData transcription = deserialize();
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
     * @return TranscriptionResponseData
     */
    private TranscriptionResponseData deserialize() {
        if(this.data == null) {
            TranscriptionResponseData translation = JacksonJsonDeserializer.parseData(
                    TranscriptionResponseData.class, this.getRawJsonResponse()
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
     * @return AtomicReference<HttpResponse<String>>
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
    public TranscriptionData getRequestData() {
        return transcription;
    }

}
