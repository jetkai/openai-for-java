package io.github.jetkai.openai.net.request;

import io.github.jetkai.openai.api.data.image.variation.ImageVariationData;

import java.util.Map;

/**
 * ImageVariationDataHandler
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 07/03/2023}
 */
final class ImageVariationDataHandler implements RequestDataHandler {
    public void populateMap(Object data, Map<Object, Object> map) {
        // Required
        ((ImageVariationData) data).image().ifPresent(img -> map.put("image", img));
        // Optional
        ((ImageVariationData) data).n().ifPresent(val -> map.put("n", val));
        // Optional
        ((ImageVariationData) data).size().ifPresent(str -> map.put("size", str));
        //Optional
        ((ImageVariationData) data).responseFormat().ifPresent(str -> map.put("response_format", str));
        //Optional
        ((ImageVariationData) data).user().ifPresent(str -> map.put("user", str));
    }

}
