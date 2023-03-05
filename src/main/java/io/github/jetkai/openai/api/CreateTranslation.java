package io.github.jetkai.openai.api;

import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.data.translation.TranslationData;

/**
 * CreateTranslation
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * {@code - 05/03/2023}
 * @since 1.0.0
 * {@code - 05/03/2023}
 */
public class CreateTranslation extends CreateCompletion implements ApiInterface {

    /**
     * CreateTranslation
     * @param openAI - The OpenAI instance
     * @param translation - The translation data specified
     */
    public CreateTranslation(OpenAI openAI, TranslationData translation) {
        super(openAI, translation);
    }

}
