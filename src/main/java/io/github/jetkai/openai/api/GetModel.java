package io.github.jetkai.openai.api;

import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.net.OpenAIEndpoints;
import io.github.jetkai.openai.net.RequestBuilder;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.atomic.AtomicReference;

public class GetModel {

    private final AtomicReference<HttpResponse<String>> response = new AtomicReference<>();
    private final OpenAI openAI;
    private final String model;

    public GetModel(OpenAI openAI, String model) {
        this.openAI = openAI;
        this.model = model;
        this.initialize();
    }

    public void initialize() {
        openAI.getHttpClientInstance().getResponse(model, new RequestBuilder() {
                    @Override
                    public HttpRequest request(Object data) {
                        URI uri = URI.create(OpenAIEndpoints.GET_MODEL.uri().toString() + "/" + data);
                        return get(uri,
                                openAI.getApiKey(),
                                openAI.getOrganization()
                        );
                    }
                })
                .thenAccept(this.response::set)
                .join();
    }

    public AtomicReference<HttpResponse<String>> getResponse() {
        return response;
    }

    public String getBody() {
        return String.valueOf(response.get().body());
    }

}
