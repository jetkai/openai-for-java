package io.github.jetkai.openai.api;

import io.github.jetkai.openai.api.data.image.ImageData;
import io.github.jetkai.openai.api.data.image.response.ImageResponseData;
import io.github.jetkai.openai.api.data.image.response.url.ImageResponseUrlData;
import io.github.jetkai.openai.net.OpenAIEndpoints;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CreateImage
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
public class CreateImage extends OAPI {

    /**
     * CreateImage
     * @param image - The image data specified
     */
    public CreateImage(ImageData image) {
        super(JacksonJsonDeserializer.valuesAsString(image), OpenAIEndpoints.CREATE_IMAGE, HttpRequestType.POST);
    }

    public CreateImage(Object image, OpenAIEndpoints endpoint, HttpRequestType requestType) {
        super(image, endpoint, requestType);
    }

    /**
     * asImage
     * @return as Image
     */
    public Image asImage() {
        if(this.deserializedData == null) {
            this.deserializedData = deserialize(ImageResponseData.class);
        }
        if (!(this.deserializedData instanceof ImageResponseData)) {
            return null;
        }

        List<ImageResponseUrlData> response = ((ImageResponseData) this.deserializedData).getData();
        if(response != null && !response.isEmpty()) {
            String imageUrlAsString = response.get(0).getUrl();
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
        if(this.deserializedData == null) {
            this.deserializedData = deserialize(ImageResponseData.class);
        }
        if (!(this.deserializedData instanceof ImageResponseData)) {
            return null;
        }

        List<ImageResponseUrlData> imageList = ((ImageResponseData) this.deserializedData).getData();
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
    public List<String> asStringList() {
        if(this.deserializedData == null) {
            this.deserializedData = deserialize(ImageResponseData.class);
        }
        if (!(this.deserializedData instanceof ImageResponseData)) {
            return null;
        }
        return ((ImageResponseData) this.deserializedData).getData()
                .stream()
                .map(ImageResponseUrlData::getUrl)
                .collect(Collectors.toList());
    }

    /**
     * asUriArray
     * @return {@code List<String>} of URLs
     */
    public URI[] asUriArray() {
        if(this.deserializedData == null) {
            this.deserializedData = deserialize(ImageResponseData.class);
        }
        if (!(this.deserializedData instanceof ImageResponseData)) {
            return null;
        }
        int length = ((ImageResponseData) this.deserializedData).getData().size();

        URI[] uris = new URI[length];
        for(int i = 0; i < length; i++) {
            String url = ((ImageResponseData) this.deserializedData).getData().get(i).getUrl();
            if(url == null) {
                continue;
            }
            uris[i] = URI.create(url);
        }
        return uris;
    }

    /**
     * asData
     * @return ImageResponseData
     */
    public ImageResponseData asData() {
        if(this.deserializedData == null) {
            this.deserializedData = deserialize(ImageResponseData.class);
        }
        if (!(this.deserializedData instanceof ImageResponseData)) {
            return null;
        }
        return (ImageResponseData) this.deserializedData;
    }

    /**
     * asJson
     * @return String (JSON)
     */
    @Override
    public String asJson() {
        if(this.deserializedData == null) {
            this.deserializedData = deserialize(ImageResponseData.class);
        }
        if (!(this.deserializedData instanceof ImageResponseData)) {
            return null;
        }
        return JacksonJsonDeserializer.valuesAsString((ImageResponseData) this.deserializedData);
    }

}
