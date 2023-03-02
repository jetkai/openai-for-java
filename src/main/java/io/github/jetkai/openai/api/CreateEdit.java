package io.github.jetkai.openai.api;

import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.data.edit.EditData;
import io.github.jetkai.openai.net.OpenAIEndpoints;
import io.github.jetkai.openai.net.RequestBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.atomic.AtomicReference;

public class CreateEdit {

    private final AtomicReference<HttpResponse<String>> response = new AtomicReference<>();
    private final OpenAI openAI;

    private final EditData edit;

    public CreateEdit(OpenAI openAI, EditData edit) {
        this.openAI = openAI;
        this.edit = edit;
        this.initialize();
    }

    public void initialize() {
        openAI.getHttpClientInstance().getResponse(edit, new RequestBuilder() {
                    @Override
                    public HttpRequest request(Object data) {
                        if(data instanceof EditData) {
                            return post(OpenAIEndpoints.CREATE_EDIT.uri(),
                                    ((EditData) data).toJson(),
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
