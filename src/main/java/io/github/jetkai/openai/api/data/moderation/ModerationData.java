package io.github.jetkai.openai.api.data.moderation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Optional;

/**
 * ModelsResponseData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.3
 * {@code - 12/03/2023}
 * @since 1.1.3
 * {@code - 12/03/2023}
 */
@JsonSerialize
public abstract class ModerationData {

    public ModerationData() { }

    public static ModerationData.Builder builder() {
        return new ModerationBuilderImpl();
    }

    public interface Builder {
        Builder setInput(String input);
        Builder setModel(String model);
        ModerationData build();
    }

    @JsonProperty("input")
    public abstract String getInput();

    @JsonProperty("model")
    public abstract String getModel();

    public abstract Optional<String> input();
    public abstract Optional<String> model();


}
