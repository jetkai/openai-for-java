package io.github.jetkai.openai.api;

import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.data.transcription.TranscriptionData;
import io.github.jetkai.openai.net.OpenAIEndpoints;
import io.github.jetkai.openai.net.RequestBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class CreateTranscription {

    private final AtomicReference<HttpResponse<String>> response = new AtomicReference<>();
    private final OpenAI openAI;

    private final TranscriptionData transcription;

    public CreateTranscription(OpenAI openAI, TranscriptionData transcription) {
        this.openAI = openAI;
        this.transcription = transcription;
        this.initialize();
    }

    public void initialize() {
        openAI.getHttpClientInstance().getResponse(transcription, new RequestBuilder() {
                    @Override
                    public HttpRequest request(Object data) {
                        if(data instanceof TranscriptionData) {
                            return postMultiPartFormData(OpenAIEndpoints.CREATE_TRANSCRIPTION.uri(),
                                    data,
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
