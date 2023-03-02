package io.github.jetkai.openai.api;

import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.data.embedding.EmbeddingData;
import io.github.jetkai.openai.net.OpenAIEndpoints;
import io.github.jetkai.openai.net.RequestBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.atomic.AtomicReference;

public class CreateEmbedding {

    private final AtomicReference<HttpResponse<String>> response = new AtomicReference<>();
    private final OpenAI openAI;

    private final EmbeddingData embedding;

    public CreateEmbedding(OpenAI openAI, EmbeddingData embedding) {
        this.openAI = openAI;
        this.embedding = embedding;
        this.initialize();
    }

    public void initialize() {
        openAI.getHttpClientInstance().getResponse(embedding, new RequestBuilder() {
                    @Override
                    public HttpRequest request(Object data) {
                        if(data instanceof EmbeddingData) {
                            return post(OpenAIEndpoints.CREATE_EMBEDDING.uri(),
                                    ((EmbeddingData) data).toJson(),
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
