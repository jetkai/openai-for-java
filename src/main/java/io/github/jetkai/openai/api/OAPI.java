package io.github.jetkai.openai.api;

import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.net.HttpClientInstance;
import io.github.jetkai.openai.net.OpenAIEndpoints;
import io.github.jetkai.openai.net.RequestBuilder;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

public abstract class OAPI {

    /**
     * HttpResponse from OpenAI
     */
    protected AtomicReference<HttpResponse<String>> response;

    /**
     * The OpenAI instance
     */
    protected OpenAI openAI;

    /**
     * The endpoint that handleHttpResponse calls
     */
    protected OpenAIEndpoints endpoint;

    /**
     * Stored object of the data that has been deserialized from the OpenAI response
     */
    protected Object deserializedData;

    protected Object requestData;

    /**
     * The type of request we are making - POST, GET or MULTI_DATA_POST
     */
    protected HttpRequestType requestType;

    protected OAPI() { }

    /**
     * Initialize
     * Sends a HttpRequest and handles the response from OpenAI's API
     */
    public void initialize() {
        HttpResponse<String> httpResponse = response.get();
        if(httpResponse == null || httpResponse.body() == null || httpResponse.body().isEmpty()) {
            this.handleHttpResponse().thenAccept(this.response::set).join();
        }
    }

    protected enum HttpRequestType { GET, POST, MULTI_DATA_POST }

    /**
     * HandleHttpResponse
     * This method will update the {@code HttpResponse<String>} response field with data from OpenAI
     * response.get().body() can then be called to retrieve the JSON response from OpenAI
     * @return {@code CompletableFuture<HttpResponse<String>>} the HttpResponse from the API
     */
    protected CompletableFuture<HttpResponse<String>> handleHttpResponse() {
        Optional<HttpClientInstance> optionalHttpClient = openAI.httpClientInstance();
        Optional<String> optionalKey = openAI.apiKey();
        Optional<String> optionalOrg = openAI.organization();
        if (optionalHttpClient.isEmpty() || optionalKey.isEmpty() || optionalOrg.isEmpty()) {
            return null;
        }
        HttpClientInstance httpClient = optionalHttpClient.get();
        return httpClient.sendAsync(requestData, new RequestBuilder() {
            @Override
            public HttpRequest request(Object data) {
                URI uri = requestType == HttpRequestType.GET && data != null
                        ? URI.create(endpoint.uri().toString() + "/" + data)
                        : endpoint.uri();
                if(requestType == HttpRequestType.GET) {
                    return createGetRequest(uri, optionalKey.get(), optionalOrg.get());
                } else if(requestType == HttpRequestType.POST) {
                    return createPostRequest(uri, data, optionalKey.get(), optionalOrg.get());
                } else if(requestType == HttpRequestType.MULTI_DATA_POST) {
                    return createMultiDataPost(uri, data, optionalKey.get(), optionalOrg.get());
                }
                return null;
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
    protected synchronized <T> T deserialize(Class<T> clazz) {
        if (this.deserializedData != null) {
            return (T) this.deserializedData;
        }
        String jsonResponse = this.getRawJsonResponse();
        if(jsonResponse == null) {
            return null;
        }
        this.deserializedData = JacksonJsonDeserializer.parseData(clazz, jsonResponse);
        return (T) this.deserializedData;
    }

    //public abstract Object[] asDataArray();

    //public abstract Object asDataList();

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
        if(httpResponse == null || httpResponse.body() == null) {
            return null;
        }
        return String.valueOf(httpResponse.body());
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
