package io.github.jetkai.openai.api;

import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.net.OpenAIEndpoints;
import io.github.jetkai.openai.net.RequestBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.atomic.AtomicReference;

public class GetModels {

    private final AtomicReference<HttpResponse<String>> response = new AtomicReference<>();
    private final OpenAI openAI;

    public GetModels(OpenAI openAI) {
        this.openAI = openAI;
        this.initialize();
    }

    public void initialize() {
        openAI.getHttpClientInstance().getResponse(null, new RequestBuilder() {
                    @Override
                    public HttpRequest request(Object data) {
                        return get(OpenAIEndpoints.GET_MODELS.uri(),
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
