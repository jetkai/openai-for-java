package io.github.jetkai.openai.api;

import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.data.completion.chat.ChatCompletionData;
import io.github.jetkai.openai.net.OpenAIEndpoints;
import io.github.jetkai.openai.net.RequestBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.atomic.AtomicReference;

public class CreateChatCompletion {

    private final AtomicReference<HttpResponse<String>> response = new AtomicReference<>();
    private final OpenAI openAI;

    private final ChatCompletionData completion;

    public CreateChatCompletion(OpenAI openAI, ChatCompletionData completion) {
        this.openAI = openAI;
        this.completion = completion;
        this.initialize();
    }

    public void initialize() {
        openAI.getHttpClientInstance().getResponse(completion, new RequestBuilder() {
                    @Override
                    public HttpRequest request(Object data) {
                        if(data instanceof ChatCompletionData) {
                            return post(OpenAIEndpoints.CREATE_CHAT_COMPLETION.uri(),
                                    ((ChatCompletionData) data).toJson(),
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
