package io.github.jetkai.chatgpt.api;

import io.github.jetkai.chatgpt.ChatGPT;
import io.github.jetkai.chatgpt.api.data.image.ImageData;
import io.github.jetkai.chatgpt.net.ChatGPTEndpoints;
import io.github.jetkai.chatgpt.net.RequestBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.atomic.AtomicReference;

public class CreateImage {

    private final AtomicReference<HttpResponse<String>> response = new AtomicReference<>();
    private final ChatGPT gpt;

    private final ImageData image;

    public CreateImage(ChatGPT gpt, ImageData image) {
        this.gpt = gpt;
        this.image = image;
        this.initialize();
    }

    public void initialize() {
        gpt.getHttpClientInstance().getResponse(image, new RequestBuilder() {
                    @Override
                    public HttpRequest request(Object data) {
                        if(data instanceof ImageData) {
                            return post(ChatGPTEndpoints.CREATE_IMAGE.uri(),
                                    ((ImageData) data).toJson(),
                                    gpt.getApiKey(),
                                    gpt.getOrganization()
                            );
                        }
                        return null;
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
