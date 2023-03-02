package io.github.jetkai.chatgpt.api;

import io.github.jetkai.chatgpt.ChatGPT;
import io.github.jetkai.chatgpt.net.ChatGPTEndpoints;
import io.github.jetkai.chatgpt.net.RequestBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.atomic.AtomicReference;

public class GetModels {

    private final AtomicReference<HttpResponse<String>> response = new AtomicReference<>();
    private final ChatGPT gpt;

    public GetModels(ChatGPT gpt) {
        this.gpt = gpt;
        this.initialize();
    }

    public void initialize() {
        gpt.getHttpClientInstance().getResponse(null, new RequestBuilder() {
                    @Override
                    public HttpRequest request(Object data) {
                        return get(ChatGPTEndpoints.GET_MODELS.uri(),
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