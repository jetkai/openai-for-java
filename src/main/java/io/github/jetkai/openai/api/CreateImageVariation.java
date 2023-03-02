package io.github.jetkai.openai.api;

import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.data.image.variation.ImageVariationData;
import io.github.jetkai.openai.net.OpenAIEndpoints;
import io.github.jetkai.openai.net.RequestBuilder;
import io.github.jetkai.openai.util.ConvertImageFormat;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class CreateImageVariation {

    private final AtomicReference<HttpResponse<String>> response = new AtomicReference<>();
    private final OpenAI openAI;

    private final ImageVariationData image;

    public CreateImageVariation(OpenAI openAI, ImageVariationData image) {
        this.openAI = openAI;
        this.image = image;
        this.initialize();
    }

    public void initialize() {
        openAI.getHttpClientInstance().getResponse(image, new RequestBuilder() {
                    @Override
                    public HttpRequest request(Object data) {
                        if(data instanceof ImageVariationData) {
                            return postMultiPartFormData(OpenAIEndpoints.CREATE_IMAGE_VARIATION.uri(),
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

    public static void populateMap(ImageVariationData data, Map<Object, Object> map) {
        Path image = data.getImage();
        if(image != null) {
            Path newImagePath = ConvertImageFormat.convertPngToRGBA(image);
            //Required
            map.put("image", newImagePath);
        }

        //Optional
        int n = data.getN();
        if(n > 0 && n < 11) {
            map.put("n", n);
        }

        //Optional
        String size = data.getSize();
        if(size != null && !size.isEmpty()) {
            map.put("size", size);
        }

        String responseFormat = data.getResponseFormat();
        if(responseFormat != null && !responseFormat.isEmpty()) {
            map.put("response_format", responseFormat);
        }

        String user = data.getUser();
        if(user != null && !user.isEmpty()) {
            map.put("user", user);
        }
    }

    public AtomicReference<HttpResponse<String>> getResponse() {
        return response;
    }

    public String getBody() {
        return String.valueOf(response.get().body());
    }

}
