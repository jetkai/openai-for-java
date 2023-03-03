package io.github.jetkai.openai.api;

import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.data.embedding.EmbeddingData;
import io.github.jetkai.openai.api.data.embedding.EmbeddingResponseData;
import io.github.jetkai.openai.api.data.embedding.EmbeddingResponseDataBlock;
import io.github.jetkai.openai.net.OpenAIEndpoints;
import io.github.jetkai.openai.net.RequestBuilder;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * CreateEmbedding
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * @created 02/03/2023
 * @last-update 03/03/2023
 */
public class CreateEmbedding implements ApiInterface {

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
    private final EmbeddingData embedding;

    /**
     * Stored object of the data that has been deserialized from the OpenAI response
     */
    private EmbeddingResponseData data;

    /**
     * CreateEmbedding
     * @param openAI - The OpenAI instance
     * @param embedding - The embedding data specified
     */
    public CreateEmbedding(OpenAI openAI, EmbeddingData embedding) {
        this.openAI = openAI;
        this.embedding = embedding;
        this.endpoint = OpenAIEndpoints.CREATE_EMBEDDING;
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
     * @return CreateEmbedding
     */
    public CreateEmbedding reinitialize() {
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
        openAI.getHttpClientInstance().getResponse(embedding, new RequestBuilder() {
            @Override
            public HttpRequest request(Object data) {
                return buildPostRequest(endpoint.uri(), ((EmbeddingData) data).toJson(),
                        openAI.getApiKey(), openAI.getOrganization()
                );
            }
        }).thenAccept(this.response::set).join();
    }


    /**
     * asFloatList
     * @return List<Float>
     */
    @SuppressWarnings("unused")
    public List<Float> asFloatList() {
        if(this.data == null) {
            EmbeddingResponseData embedding = deserialize();
            if (embedding == null) {
                return null;
            }
            this.data = embedding;
        }
        List<EmbeddingResponseDataBlock> block = this.data.getData();
        List<Float> embeddingFloat = null;
        if(block != null && !block.isEmpty()) {
            embeddingFloat = block.get(0).getEmbedding();
        }

        return embeddingFloat;
    }


    /**
     * asData
     * @return EmbeddingResponseData
     */
    @SuppressWarnings("unused")
    public EmbeddingResponseData asData() {
        if(this.data == null) {
            EmbeddingResponseData embedding = deserialize();
            if (embedding == null) {
                return null;
            }
            this.data = embedding;
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
            EmbeddingResponseData embedding = deserialize();
            if (embedding == null) {
                return null;
            }
            this.data = embedding;
        }
        return this.data.toJson();
    }

    /**
     * deserializes
     * Parses the JSON response from OpenAI and deserializes the string to the below data structure
     * @return EmbeddingResponseData
     */
    private EmbeddingResponseData deserialize() {
        if(this.data == null) {
            EmbeddingResponseData embedding = JacksonJsonDeserializer.parseData(
                    EmbeddingResponseData.class, this.getRawJsonResponse()
            );
            if (embedding == null) {
                return null;
            }
            this.data = embedding;
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

}
