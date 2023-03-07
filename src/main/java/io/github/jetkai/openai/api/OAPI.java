package io.github.jetkai.openai.api;

import io.github.jetkai.openai.net.HttpClientInstance;
import io.github.jetkai.openai.net.OpenAIEndpoints;
import io.github.jetkai.openai.net.request.RequestBuilder;
import io.github.jetkai.openai.openai.OpenAI;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * OAPI
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 05/03/2023}
 * @since 1.0.1
 * {@code - 02/03/2023}
 */
public abstract class OAPI {

    /**
     * HttpResponse from OpenAI
     */
    protected final AtomicReference<HttpResponse<String>> response;

    /**
     * The OpenAI instance
     */
    protected  OpenAI openAI;

    /**
     * The endpoint that handleHttpResponse calls
     */
    protected final OpenAIEndpoints endpoint;

    /**
     * Stored object of the data that has been deserialized from the OpenAI response
     */
    protected Object deserializedData;

    protected final Object requestData;

    /**
     * The type of request we are making - POST, GET or MULTI_DATA_POST
     */
    protected final HttpRequestType requestType;

    public OAPI(Object data, OpenAIEndpoints endpoint, HttpRequestType requestType) {
        this.requestData = data;
        this.endpoint = endpoint;
        this.requestType = requestType;
        this.response = new AtomicReference<>();
    }

    /**
     * Initialize
     * Sends a HttpRequest and handles the response from OpenAI's API
     */
    public void initialize() {
        HttpResponse<String> httpResponse = response.get();
        if (httpResponse == null || httpResponse.body() == null || httpResponse.body().isEmpty()) {
            response.getAndUpdate(r -> {
                try {
                    return handleHttpResponse().toCompletableFuture().get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    protected enum HttpRequestType { GET, POST, MULTI_DATA_POST }

    /**
     * HandleHttpResponse
     * This method will update the {@code HttpResponse<String>} response field with data from OpenAI
     * response.get().body() can then be called to retrieve the JSON response from OpenAI
     * @return {@code CompletableFuture<HttpResponse<String>>} the HttpResponse from the API
     */
    protected CompletionStage<HttpResponse<String>> handleHttpResponse() {
        HttpClientInstance httpClient = openAI.httpClientInstance()
                .orElseThrow(() -> new IllegalStateException("HttpClientInstance is missing"));
        String apiKey = openAI.apiKey().orElseThrow(() -> new IllegalStateException("API Key is missing"));
        String organization = openAI.organization().orElseThrow(() -> new IllegalStateException("Organization is missing"));

        return httpClient.sendAsync(requestData, new RequestBuilder() {
            @Override
            public HttpRequest request(Object data) {
                URI uri = requestType == HttpRequestType.GET && data != null
                        ? URI.create(endpoint.uri().toString() + "/" + data)
                        : endpoint.uri();
                switch (requestType) {
                    case GET:
                        return createGetRequest(uri, apiKey, organization);
                    case POST:
                        return createPostRequest(uri, data, apiKey, organization);
                    case MULTI_DATA_POST:
                        return createMultiDataPost(uri, data, apiKey, organization);
                    default:
                        throw new IllegalStateException("HttpRequestType is missing");
                }
            }
        });
    }

    /**
     * deserializes
     * Parses the JSON response from OpenAI and deserializes the string to the below data structure
     * @param clazz is the class we wish the json data to be deserialized to
     * @param <T> is the class we wish the json data to be deserialized to
     * @return data structure
     */
    @SuppressWarnings("unchecked")
    protected synchronized <T> T deserialize(Class<T> clazz) {
        if (this.deserializedData != null) {
            return (T) this.deserializedData;
        }
        String jsonResponse = this.getRawJsonResponse();
        Objects.requireNonNull(jsonResponse);

        this.deserializedData = JacksonJsonDeserializer.parseData(clazz, jsonResponse);
        return (T) this.deserializedData;
    }

    public abstract String asJson();

    /**
     * setOpenAI
     * @param openAI - The OpenAI instance
     * @return - This sets the OpenAI instance so that we can access the API Key, Org and HttpClient in memory
     */
    public OAPI setOpenAI(OpenAI openAI) {
        this.openAI = openAI;
        return this;
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
        HttpResponse<String> httpResponse = response.get();
        try {
            Objects.requireNonNull(httpResponse, "HttpResponse is null");

            String body = httpResponse.body();
            Objects.requireNonNull(body, "No data received from OpenAI");

            int statusCode = httpResponse.statusCode();
            if (statusCode != 200) {
                throw new IOException(body);
            }
            return body;
        } catch (NullPointerException | IOException e) {
            throw new IllegalStateException("Error getting raw JSON response from OpenAI API", e);
        }
    }

    /**
     * getEndpoint
     * @return OpenAIEndpoints (The endpoint that handleHttpResponse calls)
     */
    public OpenAIEndpoints getEndpoint() {
        return endpoint;
    }

    /**
     * getRequestData
     * @return This is the data we are sending to OpenAI's API
     */
    public Object getRequestData() {
        return requestData;
    }

}
