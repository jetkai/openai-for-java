package io.github.jetkai.openai.api;

import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.data.model.ModelData;
import io.github.jetkai.openai.api.data.model.ModelsData;
import io.github.jetkai.openai.net.OpenAIEndpoints;
import io.github.jetkai.openai.net.RequestBuilder;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * GetModels
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * {@code - 03/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
public class GetModels implements ApiInterface {

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
     * Stored object of the data that has been deserialized from the OpenAI response
     */
    private ModelsData data;

    /**
     * GetModel
     * @param openAI - The OpenAI instance
     */
    public GetModels(OpenAI openAI) {
        this.openAI = openAI;
        this.endpoint = OpenAIEndpoints.GET_MODELS;
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
     * @return GetModel
     */
    public GetModels reinitialize() {
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
        openAI.getHttpClientInstance().getResponse(null, new RequestBuilder() {
            @Override
            public HttpRequest request(Object data) {
                return buildGetRequest(endpoint.uri(), openAI.getApiKey(), openAI.getOrganization());
            }
        }).thenAccept(this.response::set).join();
    }

    /**
     * asDataArray
     * @return - ModelData[] which contains all the available OpenAI models
     */
    @SuppressWarnings("unused")
    public ModelData[] asDataArray() {
        if(this.data == null) {
            ModelsData models = deserialize();
            if (models == null) {
                return null;
            }
            this.data = models;
        }
        return this.data.getData();
    }

    /**
     * asDataList
     * @return - {@code List<ModelData>} which contains all the available OpenAI models
     */
    @SuppressWarnings("unused")
    public List<ModelData> asDataList() {
        if(this.data == null) {
            ModelsData models = deserialize();
            if (models == null) {
                return null;
            }
            this.data = models;
        }
        return new ArrayList<>(Arrays.asList(this.data.getData()));
    }

    /**
     * asJson
     * @return String (JSON)
     */
    @Override
    public String asJson() {
        ModelsData models = deserialize();
        if(models == null) {
            return null;
        }
        return models.toJson();
    }

    /**
     * deserializes
     * Parses the JSON response from OpenAI and deserializes the string to the below data structure
     * @return data structure
     */
    private ModelsData deserialize() {
        if(this.data == null) {
            ModelsData data = JacksonJsonDeserializer.parseData(ModelsData.class, this.getRawJsonResponse());
            if (data == null) {
                return null;
            }
            this.data = data;
        }
        return this.data;
    }

    /**
     * getResponse
     * The response from OpenAI
     * @return {@code AtomicReference<HttpResponse<String>>}
     */
    public AtomicReference<HttpResponse<String>> getHttpResponse() {
        return response;
    }

    /**
     * getBody
     * @return String (JSON from OpenAI response)
     */
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
    public Object getRequestData() {
        return null;
    }

}
