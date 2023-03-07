package io.github.jetkai.openai.net.request;

import io.github.jetkai.openai.api.data.image.edit.ImageEditData;
import io.github.jetkai.openai.util.ConvertImageFormat;

import java.util.Map;

/**
 * ImageEditDataHandler
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 07/03/2023}
 */
final class ImageEditDataHandler implements RequestDataHandler {
    public void populateMap(Object data, Map<Object, Object> map) {
        //Required
       ((ImageEditData) data).image().map(ConvertImageFormat::convertPngToRGBA)
               .ifPresent(path -> map.put("image", path));
        //Required
        ((ImageEditData) data).prompt().ifPresent(str -> map.put("prompt", str));
        //Optional
        ((ImageEditData) data).mask().map(ConvertImageFormat::convertPngToRGBA)
                .ifPresent(path -> map.put("mask", path));
        //Optional
        ((ImageEditData) data).n().filter(val -> val > 0 && val < 11).ifPresent(val -> map.put("n", val));
        //Optional
        ((ImageEditData) data).size().filter(str -> !str.isEmpty()).ifPresent(str -> map.put("size", str));
        //Optional
        ((ImageEditData) data).responseFormat().filter(str -> !str.isEmpty())
                .ifPresent(str -> map.put("response_format", str));
        //Optional
        ((ImageEditData) data).user().ifPresent(str -> map.put("user", str));
    }
}
