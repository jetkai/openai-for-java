package io.github.jetkai.openai.net.request;

import io.github.jetkai.openai.api.data.image.variation.ImageVariationData;
import io.github.jetkai.openai.util.ConvertImageFormat;

import java.util.Map;
import java.util.Optional;

final class ImageVariationDataHandler implements RequestDataHandler {
    public void populateMap(Object data, Map<Object, Object> map) {
        // Required
        Optional.ofNullable(((ImageVariationData) data).getImage())
                .map(ConvertImageFormat::convertPngToRGBA)
                .ifPresent(img -> map.put("image", img));

        // Optional
        Optional.of(((ImageVariationData) data).getN())
                .filter(val -> val > 0 && val < 11)
                .ifPresent(val -> map.put("n", val));

        // Optional
        Optional.ofNullable(((ImageVariationData) data).getSize())
                .filter(str -> !str.isEmpty())
                .ifPresent(str -> map.put("size", str));

        //Optional
        Optional.ofNullable(((ImageVariationData) data).getResponseFormat())
                .filter(str -> !str.isEmpty())
                .ifPresent(str -> map.put("response_format", str));

        //Optional
        Optional.ofNullable(((ImageVariationData) data).getUser())
                .filter(str -> !str.isEmpty())
                .ifPresent(str -> map.put("user", str));
    }
}
