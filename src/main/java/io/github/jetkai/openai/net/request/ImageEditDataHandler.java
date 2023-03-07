package io.github.jetkai.openai.net.request;

import io.github.jetkai.openai.api.data.image.edit.ImageEditData;
import io.github.jetkai.openai.util.ConvertImageFormat;

import java.util.Map;
import java.util.Optional;

final class ImageEditDataHandler implements RequestDataHandler {
    public void populateMap(Object data, Map<Object, Object> map) {
        //Required
        Optional.ofNullable(((ImageEditData) data).getImage())
                .map(ConvertImageFormat::convertPngToRGBA)
                .ifPresent(path -> map.put("image", path));

        //Required
        Optional.ofNullable(((ImageEditData) data).getPrompt())
                .filter(str -> !str.isEmpty())
                .ifPresent(str -> map.put("prompt", str));

        //Optional
        Optional.ofNullable(((ImageEditData) data).getMask())
                .map(ConvertImageFormat::convertPngToRGBA)
                .ifPresent(path -> map.put("mask", path));

        //Optional
        Optional.of(((ImageEditData) data).getN())
                .filter(val -> val > 0 && val < 11)
                .ifPresent(val -> map.put("n", val));

        //Optional
        Optional.ofNullable(((ImageEditData) data).getSize())
                .filter(str -> !str.isEmpty())
                .ifPresent(str -> map.put("size", str));

        //Optional
        Optional.ofNullable(((ImageEditData) data).getResponseFormat())
                .filter(str -> !str.isEmpty())
                .ifPresent(str -> map.put("response_format", str));

        //Optional
        Optional.ofNullable(((ImageEditData) data).getUser())
                .filter(str -> !str.isEmpty())
                .ifPresent(str -> map.put("user", str));
    }
}
