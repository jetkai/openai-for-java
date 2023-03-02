package io.github.jetkai.openai.api;

import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.data.image.ImageData;
import io.github.jetkai.openai.net.OpenAIEndpoints;
import io.github.jetkai.openai.net.RequestBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.atomic.AtomicReference;

public class CreateImage {

    private final AtomicReference<HttpResponse<String>> response = new AtomicReference<>();
    private final OpenAI openAI;

    private final ImageData image;

    public CreateImage(OpenAI openAI, ImageData image) {
        this.openAI = openAI;
        this.image = image;
        this.initialize();
    }

    public void initialize() {
        openAI.getHttpClientInstance().getResponse(image, new RequestBuilder() {
                    @Override
                    public HttpRequest request(Object data) {
                        if(data instanceof ImageData) {
                            return post(OpenAIEndpoints.CREATE_IMAGE.uri(),
                                    ((ImageData) data).toJson(),
                                    openAI.getApiKey(),
                                    openAI.getOrganization()
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
