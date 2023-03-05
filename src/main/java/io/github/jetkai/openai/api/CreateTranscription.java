package io.github.jetkai.openai.api;

import io.github.jetkai.openai.api.data.audio.AudioData;
import io.github.jetkai.openai.api.data.audio.AudioResponseData;
import io.github.jetkai.openai.net.OpenAIEndpoints;

import java.text.Normalizer;
import java.util.concurrent.atomic.AtomicReference;

/**
 * CreateTranscription
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.1
 * {@code - 05/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
public class CreateTranscription extends OAPI {

    /**
     * CreateTranscription
     * @param transcription - The translation data specified
     */
    public CreateTranscription(AudioData transcription) {
        super();
        this.requestData = transcription;
        this.endpoint = OpenAIEndpoints.CREATE_AUDIO_TRANSCRIPTION;
        this.requestType = HttpRequestType.MULTI_DATA_POST;
        this.response = new AtomicReference<>();
    }

    /**
     * CreateTranscription
     * @param transcription - The translation data specified
     * @param endpoint - The OpenAI endpoint URI
     */
    public CreateTranscription(AudioData transcription, OpenAIEndpoints endpoint) {
        super();
        this.requestData = transcription;
        this.endpoint = endpoint;
        this.requestType = HttpRequestType.MULTI_DATA_POST;
        this.response = new AtomicReference<>();
    }

    /**
     * asNormalizedText
     * @return - String with replaced ascii characters, and removes "\n"
     */
    @SuppressWarnings("unused")
    public String asNormalizedText() {
        //Replaces any characters that do not match the regex
        String normalized = Normalizer.normalize(this.asText(), Normalizer.Form.NFD);
        return normalized
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll("\n", "");
    }

    /**
     * asText
     * @return - String with the raw text responded back from OpenAI
     */
    public String asText() {
        if(this.deserializedData == null) {
            this.deserializedData = deserialize(AudioResponseData.class);
        }
        if (!(this.deserializedData instanceof AudioResponseData)) {
            return null;
        }
        return ((AudioResponseData) this.deserializedData).getText();
    }

    /**
     * asData
     * @return ModelData
     */
    public AudioResponseData asData() {
        if(this.deserializedData == null) {
            this.deserializedData = deserialize(AudioResponseData.class);
        }
        if (!(this.deserializedData instanceof AudioResponseData)) {
            return null;
        }
        return (AudioResponseData) this.deserializedData;
    }

    /**
     * asJson
     * @return String (JSON)
     */
    @Override
    public String asJson() {
        if(this.deserializedData == null) {
            this.deserializedData = deserialize(AudioResponseData.class);
        }
        if (!(this.deserializedData instanceof AudioResponseData)) {
            return null;
        }
        return ((AudioResponseData) this.deserializedData).toJson();
    }

}
