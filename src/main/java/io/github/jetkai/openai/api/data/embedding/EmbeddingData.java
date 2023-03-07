package io.github.jetkai.openai.api.data.embedding;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

import java.util.ArrayList;
import java.util.List;

/**
 * EmbeddingData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@JsonSerialize
public class EmbeddingData {

    /**
     * model
     * string
     * Required
     * <p>
     * ID of the model to use. You can use the List models API to see all of your available models,
     * or see our Model overview for descriptions of them.
     */
    private String model;

    /**
     * input
     * string or array
     * Required
     * <p>
     * Input text to get embeddings for, encoded as a string or array of tokens.
     * To get embeddings for multiple inputs in a single request, pass an array of strings or array of token arrays.
     * Each input must not exceed 8192 tokens in length.
     */
    private List<String> input;

    /**
     * user
     * string
     * Optional
     * <p>
     * A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse.
     * <a href="https://platform.openai.com/docs/guides/safety-best-practices/end-user-ids">Learn more.</a>
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String user;

    public EmbeddingData() { }

    public EmbeddingData setUser(String user) {
        this.user = user;
        return this;
    }

    public EmbeddingData setInput(String input) {
        this.input = new ArrayList<>(List.of(input));
        return this;
    }

    public EmbeddingData setInput(List<String> input) {
        this.input = input;
        return this;
    }

    public EmbeddingData setModel(String model) {
        this.model = model;
        return this;
    }

    public String getUser() {
        return user;
    }

    public List<String> getInput() {
        return input;
    }

    public String getModel() {
        return model;
    }

    @JsonIgnore
    public String toJson() {
        return JacksonJsonDeserializer.valuesAsString(this);
    }

}
