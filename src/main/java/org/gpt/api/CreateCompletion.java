package org.gpt.api;

import org.gpt.ChatGPT;
import org.gpt.api.data.completion.CompletionData;
import org.gpt.net.ChatGPTEndpoints;
import org.gpt.net.RequestBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.atomic.AtomicReference;

public class CreateCompletion {

    private final AtomicReference<HttpResponse<String>> response = new AtomicReference<>();
    private final ChatGPT gpt;

    private final CompletionData completion;

    public CreateCompletion(ChatGPT gpt, CompletionData completion) {
        this.gpt = gpt;
        this.completion = completion;
        this.initialize();
    }

    public void initialize() {
        gpt.getHttpClientInstance().getResponse(completion, new RequestBuilder() {
                    @Override
                    public HttpRequest request(Object data) {
                        if(data instanceof CompletionData) {
                            return post(ChatGPTEndpoints.CREATE_COMPLETION_URI.uri(),
                                    ((CompletionData) data).toJson(),
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
