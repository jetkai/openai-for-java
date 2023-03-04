package io.github.jetkai.openai.api;

import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.data.image.ImageResponseData;
import io.github.jetkai.openai.api.data.image.ImageResponseUrlData;
import io.github.jetkai.openai.api.data.image.edit.ImageEditData;
import io.github.jetkai.openai.net.OpenAIEndpoints;
import io.github.jetkai.openai.net.RequestBuilder;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * CreateImageEdit
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * {@code - 03/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
public class CreateImageEdit implements ApiInterface {

    /**
     * HttpResponse from OpenAI
     */
    private final AtomicReference<HttpResponse<String>> response;

    /**
     * The OpenAI instance
     */
    private final OpenAI openAI;

    /**
     * The endpoint that handleHttpResponse calls
     */
    private final OpenAIEndpoints endpoint;

    /**
     * The data that we are going to be sending through the POST request
     */
    private final ImageEditData image;

    /**
     * Stored object of the data that has been deserialized from the OpenAI response
     */
    private ImageResponseData data;

    /**
     * CreateImageEdit
     * @param openAI - The OpenAI instance
     * @param image - The image data specified
     */
    public CreateImageEdit(OpenAI openAI, ImageEditData image) {
        this.openAI = openAI;
        this.image = image;
        this.endpoint = OpenAIEndpoints.CREATE_IMAGE_EDIT;
        this.response = new AtomicReference<>();
        this.initialize();
    }

    /**
     * Initialize
     * Sends a HttpRequest & handles the response from OpenAI's API
     */
    private void initialize() {
        HttpResponse<String> httpResponse = response.get();
        if(httpResponse == null || httpResponse.body() == null || httpResponse.body().isEmpty()) {
            this.handleHttpResponse();
        }
    }

    /**
     * Reinitialize
     * If the HttpRequest/Response to OpenAI's API needs to be restarted, this will do that
     * @return CreateImageEdit
     */
    public CreateImageEdit reinitialize() {
        this.data = null;
        this.handleHttpResponse();
        return this;
    }

    /**
     * HandleHttpResponse
     * This method will update the HttpResponse<String> response field with data from OpenAI
     * response.get().body() can then be called to retrieve the JSON response from OpenAI
     */
    private void handleHttpResponse() {
        openAI.getHttpClientInstance().getResponse(image, new RequestBuilder() {
            @Override
            public HttpRequest request(Object data) {
                return buildMultiDataPost(endpoint.uri(), data, openAI.getApiKey(), openAI.getOrganization());
            }
        }).thenAccept(this.response::set).join();
    }

    /**
     * asImage
     * @return as Image
     */
    public Image asImage() {
        if(this.data == null) {
            ImageResponseData imageResponse = deserialize();
            if (imageResponse == null) {
                return null;
            }
            this.data = imageResponse;
        }

        List<ImageResponseUrlData> response = data.getData();
        if(response != null && !response.isEmpty()) {
            String imageUrlAsString = data.getData().get(0).getUrl();
            if(imageUrlAsString == null || imageUrlAsString.isEmpty()) {
                return null;
            }
            try {
                URL imageUrl = new URL(imageUrlAsString);
                return ImageIO.read(imageUrl);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    /**
     * asImageArray
     * @return - Return all the images as Image[]
     */
    public Image[] asImageArray() {

        if(this.data == null) {
            ImageResponseData imageResponse = deserialize();
            if (imageResponse == null) {
                return null;
            }
            this.data = imageResponse;
        }

        List<ImageResponseUrlData> imageList = data.getData();
        Image[] images = new Image[imageList.size()];

        for(int i = 0; i < imageList.size(); i++) {
            ImageResponseUrlData imageResponse = imageList.get(i);
            if(imageResponse == null) {
                continue;
            }
            String imageResponseUrl = imageResponse.getUrl();
            if(imageResponseUrl == null || imageResponseUrl.isEmpty()) {
                continue;
            }
            URL imageUrl;
            try {
                imageUrl = new URL(imageResponseUrl);
                images[i] = ImageIO.read(imageUrl);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return images;
    }

    /**
     * asLinks
     * @return {@code List<String>} of URLs
     */
    @SuppressWarnings("unused")
    public List<String> asStringList() {
        if(this.data == null) {
            ImageResponseData translation = deserialize();
            if (translation == null) {
                return null;
            }
            this.data = translation;
        }
        List<String> links = new ArrayList<>();
        for(ImageResponseUrlData response : this.data.getData()) {
            links.add(response.getUrl());
        }
        return links;
    }

    /**
     * asUriArray
     * @return {@code List<String>} of URLs
     */
    @SuppressWarnings("unused")
    public URI[] asUriArray() {
        if(this.data == null) {
            ImageResponseData translation = deserialize();
            if (translation == null) {
                return null;
            }
            this.data = translation;
        }
        int dataLength = this.data.getData().size();
        URI[] uris = new URI[dataLength];
        for(int i = 0; i < dataLength; i++) {
            uris[i] = URI.create(this.data.getData().get(i).getUrl());
        }
        return uris;
    }

    /**
     * asData
     * @return ImageResponseData
     */
    @SuppressWarnings("unused")
    public ImageResponseData asData() {
        if(this.data == null) {
            ImageResponseData image = deserialize();
            if (image == null) {
                return null;
            }
            this.data = image;
        }
        return this.data;
    }

    /**
     * asJson
     * @return String (JSON)
     */
    @Override
    public String asJson() {
        if(this.data == null) {
            ImageResponseData image = deserialize();
            if (image == null) {
                return null;
            }
            this.data = image;
        }
        return this.data.toJson();
    }

    /**
     * deserializes
     * Parses the JSON response from OpenAI and deserializes the string to the below data structure
     * @return ImageResponseData
     */
    private ImageResponseData deserialize() {
        if(this.data == null) {
            ImageResponseData image = JacksonJsonDeserializer.parseData(
                    ImageResponseData.class, this.getRawJsonResponse()
            );
            if (image == null) {
                return null;
            }
            this.data = image;
        }
        return this.data;
    }

    /**
     * getResponse
     * The response from OpenAI
     * @return {@code AtomicReference<HttpResponse<String>>}
     */
    @Override
    public AtomicReference<HttpResponse<String>> getHttpResponse() {
        return response;
    }

    /**
     * getBody
     * @return String (JSON from OpenAI response)
     */
    @Override
    public String getRawJsonResponse() {
        return String.valueOf(response.get().body());
    }

    /**
     * getEndpoint
     * @return OpenAIEndpoints (The endpoint that handleHttpResponse calls)
     */
    @Override
    public OpenAIEndpoints getEndpoint() {
        return endpoint;
    }

    @Override
    public ImageEditData getRequestData() {
        return image;
    }

}
