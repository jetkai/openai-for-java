package io.github.jetkai.openai.api;

import io.github.jetkai.openai.api.data.edit.EditData;
import io.github.jetkai.openai.net.OpenAIEndpoints;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

/**
 * CreateEdit
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.1
 * {@code - 05/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
public class CreateEdit extends CreateCompletion {

    /**
     * CreateEdit
     * @param edit - The edit data specified
     */
    public CreateEdit(EditData edit) {
        super(JacksonJsonDeserializer.valuesAsString(edit), OpenAIEndpoints.CREATE_EDIT);
    }

}
