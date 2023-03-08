package io.github.jetkai.openai.api.data.embedding;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;
import java.util.Optional;

/**
 * EmbeddingData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.1
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@JsonSerialize
public abstract class EmbeddingData {

    public EmbeddingData() { }

    public static EmbeddingData.Builder builder() {
        return new EmbeddingBuilderImpl();
    }

    public interface Builder {
        Builder setUser(String user);
        Builder setInput(String input);
        Builder setInput(List<String> input);
        Builder setModel(String model);
        EmbeddingData build();
    }

    /**
     * user
     * string
     * Optional
     * <p>
     * A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse.
     * <a href="https://platform.openai.com/docs/guides/safety-best-practices/end-user-ids">Learn more.</a>
     * @return getUser
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("user")
    public abstract String getUser();

    /**
     * input
     * string or array
     * Required
     * <p>
     * Input text to get embeddings for, encoded as a string or array of tokens.
     * To get embeddings for multiple inputs in a single request, pass an array of strings or array of token arrays.
     * Each input must not exceed 8192 tokens in length.
     * @return getInput
     */
    @JsonProperty("input")
    public abstract List<String> getInput();

    /**
     * model
     * string
     * Required
     * <p>
     * ID of the model to use. You can use the List models API to see all of your available models,
     * or see our Model overview for descriptions of them.
     * @return getModel
     */
    @JsonProperty("model")
    public abstract String getModel();

    public abstract Optional<String> model();
    public abstract Optional<List<String>> input();
    public abstract Optional<String> user();

}
