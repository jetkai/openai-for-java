package io.github.jetkai.openai.api;

import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.data.translation.TranslationData;
import io.github.jetkai.openai.api.data.translation.TranslationResponseData;
import io.github.jetkai.openai.net.OpenAIEndpoints;
import io.github.jetkai.openai.net.RequestBuilder;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.atomic.AtomicReference;

/**
 * CreateTranslation
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * {@code - 03/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
public class CreateTranslation implements ApiInterface {

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
    private final TranslationData requestData;

    /**
     * Stored object of the data that has been deserialized from the OpenAI response
     */
    private TranslationResponseData data;

    /**
     * CreateTranslation
     * @param openAI - The OpenAI instance
     * @param translation - The translation data specified
     */
    public CreateTranslation(OpenAI openAI, TranslationData translation) {
        this.openAI = openAI;
        this.requestData = translation;
        this.endpoint = OpenAIEndpoints.CREATE_TRANSLATION;
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
     * @return CreateTranslation
     */
    public CreateTranslation reinitialize() {
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
        openAI.getHttpClientInstance().getResponse(requestData, new RequestBuilder() {
            @Override
            public HttpRequest request(Object data) {
                return buildMultiDataPost(endpoint.uri(), data, openAI.getApiKey(), openAI.getOrganization());
            }
        }).thenAccept(this.response::set).join();
    }

    /**
     * asText
     * @return String (Translation from language X to language Y)
     */
    public String asText() {
        if(this.data == null) {
            TranslationResponseData translation = deserialize();
            if (translation == null) {
                return null;
            }
            this.data = translation;
        }
        return this.data.getText();
    }

    /**
     * asData
     * @return TranslationData
     */
    public TranslationResponseData asData() {
        if(this.data == null) {
            TranslationResponseData translation = deserialize();
            if (translation == null) {
                return null;
            }
            this.data = translation;
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
            TranslationResponseData translation = deserialize();
            if (translation == null) {
                return null;
            }
            this.data = translation;
        }
        return this.data.toJson();
    }

    /**
     * deserializes
     * Parses the JSON response from OpenAI and deserializes the string to the below data structure
     * @return TranslationResponseData
     */
    private TranslationResponseData deserialize() {
        if(this.data == null) {
            TranslationResponseData translation = JacksonJsonDeserializer.parseData(
                    TranslationResponseData.class, this.getRawJsonResponse()
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
    public TranslationData getRequestData() {
        return requestData;
    }

}
