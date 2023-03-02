package io.github.jetkai.chatgpt.api;

import io.github.jetkai.chatgpt.ChatGPT;
import io.github.jetkai.chatgpt.net.ChatGPTEndpoints;
import io.github.jetkai.chatgpt.net.RequestBuilder;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.atomic.AtomicReference;

public class GetModel {

    private final AtomicReference<HttpResponse<String>> response = new AtomicReference<>();
    private final ChatGPT gpt;
    private final String model;

    public GetModel(ChatGPT gpt, String model) {
        this.gpt = gpt;
        this.model = model;
        this.initialize();
    }

    public void initialize() {
        gpt.getHttpClientInstance().getResponse(model, new RequestBuilder() {
                    @Override
                    public HttpRequest request(Object data) {
                        URI uri = URI.create(ChatGPTEndpoints.GET_MODEL.uri().toString() + "/" + data);
                        return get(uri,
                                gpt.getApiKey(),
                                gpt.getOrganization()
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
