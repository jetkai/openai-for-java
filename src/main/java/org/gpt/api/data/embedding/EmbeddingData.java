package org.gpt.api.data.embedding;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@SuppressWarnings("unused")
public class EmbeddingData {

    /*
     * model
     * string
     * Required
     *
     * ID of the model to use. You can use the List models API to see all of your available models,
     * or see our Model overview for descriptions of them.
     */
    private String model;

    /*
     * input
     * string or array
     * Required
     *
     * Input text to get embeddings for, encoded as a string or array of tokens.
     * To get embeddings for multiple inputs in a single request, pass an array of strings or array of token arrays.
     * Each input must not exceed 8192 tokens in length.
     */
    private String input;

    /*
     * user
     * string
     * Optional
     *
     * A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse.
     * Learn more. (https://platform.openai.com/docs/guides/safety-best-practices/end-user-ids)
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String user;

    public EmbeddingData() { }

    public void setUser(String user) {
        this.user = user;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUser() {
        return user;
    }

    public String getInput() {
        return input;
    }

    public String getModel() {
        return model;
    }

    @JsonIgnore
    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        String json;
        try {
            json = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

}
