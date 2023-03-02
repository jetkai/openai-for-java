package io.github.jetkai.chatgpt.api;

import io.github.jetkai.chatgpt.ChatGPT;
import io.github.jetkai.chatgpt.api.data.translation.TranslationData;
import io.github.jetkai.chatgpt.net.ChatGPTEndpoints;
import io.github.jetkai.chatgpt.net.RequestBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class CreateTranslation {

    private final AtomicReference<HttpResponse<String>> response = new AtomicReference<>();
    private final ChatGPT gpt;

    private final TranslationData translation;

    public CreateTranslation(ChatGPT gpt, TranslationData translation) {
        this.gpt = gpt;
        this.translation = translation;
        this.initialize();
    }

    public void initialize() {
        gpt.getHttpClientInstance().getResponse(translation, new RequestBuilder() {
                    @Override
                    public HttpRequest request(Object data) {
                        if(data instanceof TranslationData) {
                            return postMultiPartFormData(ChatGPTEndpoints.CREATE_TRANSLATION.uri(),
                                    data,
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

    public static void populateMap(TranslationData data, Map<Object, Object> map) {
        //Required
        Path audioFile = data.getFile();
        if(audioFile != null) {
            map.put("file", audioFile);
        }

        //Required
        String model = data.getModel();
        if(model != null) {
            map.put("model", model);
        }

        //Optional
        String prompt = data.getPrompt();
        if(prompt != null && !prompt.isEmpty()) {
            map.put("prompt", prompt);
        }

        //Optional
        double temperature = data.getTemperature();
        map.put("temperature", temperature);

        //Optional
        String responseFormat = data.getResponseFormat();
        if(responseFormat != null && !responseFormat.isEmpty()) {
            map.put("response_format", responseFormat);
        }

        String language = data.getLanguage();
        if(language != null && !language.isEmpty()) {
            map.put("language", language);
        }

    }

    public AtomicReference<HttpResponse<String>> getResponse() {
        return response;
    }

    public String getBody() {
        return String.valueOf(response.get().body());
    }

}
