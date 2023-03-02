package io.github.jetkai.openai.api;

import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.data.completion.CompletionData;
import io.github.jetkai.openai.net.OpenAIEndpoints;
import io.github.jetkai.openai.net.RequestBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.atomic.AtomicReference;

public class CreateCompletion {

    private final AtomicReference<HttpResponse<String>> response = new AtomicReference<>();
    private final OpenAI openAI;

    private final CompletionData completion;

    public CreateCompletion(OpenAI openAI, CompletionData completion) {
        this.openAI = openAI;
        this.completion = completion;
        this.initialize();
    }

    public void initialize() {
        openAI.getHttpClientInstance().getResponse(completion, new RequestBuilder() {
                    @Override
                    public HttpRequest request(Object data) {
                        if(data instanceof CompletionData) {
                            return post(OpenAIEndpoints.CREATE_COMPLETION.uri(),
                                    ((CompletionData) data).toJson(),
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
