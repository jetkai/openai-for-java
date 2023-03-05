package io.github.jetkai.openai.api;

import io.github.jetkai.openai.api.data.image.variation.ImageVariationData;
import io.github.jetkai.openai.net.OpenAIEndpoints;

/**
 * CreateImageVariation
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.1
 * {@code - 05/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
public class CreateImageVariation extends CreateImage {

    /**
     * CreateImageVariation
     * @param image - The image data specified
     */
    public CreateImageVariation(ImageVariationData image) {
        super(image, OpenAIEndpoints.CREATE_IMAGE_VARIATION, HttpRequestType.MULTI_DATA_POST);
    }

}
