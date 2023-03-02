package io.github.jetkai.chatgpt.api;

import io.github.jetkai.chatgpt.ChatGPT;
import io.github.jetkai.chatgpt.api.data.edit.EditData;
import io.github.jetkai.chatgpt.net.ChatGPTEndpoints;
import io.github.jetkai.chatgpt.net.RequestBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.atomic.AtomicReference;

public class CreateEdit {

    private final AtomicReference<HttpResponse<String>> response = new AtomicReference<>();
    private final ChatGPT gpt;

    private final EditData edit;

    public CreateEdit(ChatGPT gpt, EditData edit) {
        this.gpt = gpt;
        this.edit = edit;
        this.initialize();
    }

    public void initialize() {
        gpt.getHttpClientInstance().getResponse(edit, new RequestBuilder() {
                    @Override
                    public HttpRequest request(Object data) {
                        if(data instanceof EditData) {
                            return post(ChatGPTEndpoints.CREATE_EDIT.uri(),
                                    ((EditData) data).toJson(),
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
