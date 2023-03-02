package org.gpt.api;

import org.gpt.ChatGPT;
import org.gpt.net.ChatGPTURI;
import org.gpt.net.RequestBuilder;

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
                        return get(ChatGPTURI.GET_MODELS_URI.uri(),
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
