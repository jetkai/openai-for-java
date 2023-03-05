package io.github.jetkai.openai.api;

import io.github.jetkai.openai.api.data.image.edit.ImageEditData;
import io.github.jetkai.openai.net.OpenAIEndpoints;

/**
 * CreateImageEdit
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.1
 * {@code - 05/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
public class CreateImageEdit extends CreateImage {

    /**
     * CreateImageEdit
     * @param image - The image data specified
     */
    public CreateImageEdit(ImageEditData image) {
        super(image, OpenAIEndpoints.CREATE_IMAGE_EDIT, HttpRequestType.MULTI_DATA_POST);
    }

}
