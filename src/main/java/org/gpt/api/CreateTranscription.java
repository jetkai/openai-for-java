package org.gpt.api;

import org.gpt.ChatGPT;
import org.gpt.api.data.transcription.TranscriptionData;
import org.gpt.net.ChatGPTEndpoints;
import org.gpt.net.RequestBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class CreateTranscription {

    private final AtomicReference<HttpResponse<String>> response = new AtomicReference<>();
    private final ChatGPT gpt;

    private final TranscriptionData image;

    public CreateTranscription(ChatGPT gpt, TranscriptionData image) {
        this.gpt = gpt;
        this.image = image;
        this.initialize();
    }

    public void initialize() {
        gpt.getHttpClientInstance().getResponse(image, new RequestBuilder() {
                    @Override
                    public HttpRequest request(Object data) {
                        if(data instanceof TranscriptionData) {
                            return postMultiPartFormData(ChatGPTEndpoints.CREATE_TRANSCRIPTION.uri(),
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

    public static void populateMap(TranscriptionData data, Map<Object, Object> map) {
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
        String language = data.getLanguage();
        if(language != null && !language.isEmpty()) {
            map.put("language", language);
        }

        //Optional
        String responseFormat = data.getResponseFormat();
        if(responseFormat != null && !responseFormat.isEmpty()) {
            map.put("response_format", responseFormat);
        }

    }

    public AtomicReference<HttpResponse<String>> getResponse() {
        return response;
    }

    public String getBody() {
        return String.valueOf(response.get().body());
    }

}
