package io.github.jetkai.openai.api;

import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.data.audio.AudioData;
import io.github.jetkai.openai.net.OpenAIEndpoints;

/**
 * CreateTranscriptionTranslation
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * {@code - 03/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
public class CreateTranscriptionTranslation extends CreateTranscription implements ApiInterface {

    /**
     * CreateTranslation
     * @param openAI - The OpenAI instance
     * @param translation - The translation data specified
     */
    public CreateTranscriptionTranslation(OpenAI openAI, AudioData translation) {
        super(openAI, translation, OpenAIEndpoints.CREATE_AUDIO_TRANSLATION);
    }

}
