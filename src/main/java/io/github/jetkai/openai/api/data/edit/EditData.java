package io.github.jetkai.openai.api.data.edit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

/**
 * EditData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * {@code - 03/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@SuppressWarnings("unused")
public class EditData {

    /**
     * model
     * string
     * Required
     * <p>
     * ID of the model to use. You can use the text-davinci-edit-001 or
     * code-davinci-edit-001 model with this endpoint.
     */
    private String model;

    /**
     * input
     * string
     * Optional
     * Defaults to ''
     * <p>
     * The input text to use as a starting point for the edit.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String input;

    /**
     * instruction
     * string
     * Required
     * <p>
     * The instruction that tells the model how to edit the prompt.
     */
    private String instruction;

    /**
     * n
     * integer
     * Optional
     * Defaults to 1
     * <p>
     * How many edits to generate for the input and instruction.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int n;

    /**
     * temperature
     * number
     * Optional
     * Defaults to 1
     * <p>
     * What sampling temperature to use, between 0 and 2. Higher values like 0.8 will make the
     * output more random, while lower values like 0.2 will make it more focused and deterministic.
     * <p>
     * We generally recommend altering this or top_p but not both.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private double temperature;

    /**
     * top_p
     * number
     * Optional
     * Defaults to 1
     * <p>
     * An alternative to sampling with temperature, called nucleus sampling, where the model considers
     * the results of the tokens with top_p probability mass. So 0.1 means only the tokens comprising
     * the top 10% probability mass are considered.
     * <p>
     * We generally recommend altering this or temperature but not both.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("top_p")
    private double topP;

    public EditData() { }

    public EditData setModel(String model) {
        this.model = model;
        return this;
    }

    public EditData setTopP(double topP) {
        this.topP = topP;
        return this;
    }

    public EditData setTemperature(double temperature) {
        this.temperature = temperature;
        return this;
    }

    public EditData setN(int n) {
        this.n = n;
        return this;
    }

    public EditData setInput(String input) {
        this.input = input;
        return this;
    }

    public EditData setInstruction(String instruction) {
        this.instruction = instruction;
        return this;
    }

    public String getModel() {
        return model;
    }

    public double getTopP() {
        return topP;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getN() {
        return n;
    }

    public String getInput() {
        return input;
    }

    public String getInstruction() {
        return instruction;
    }

    @JsonIgnore
    public String toJson() {
        return JacksonJsonDeserializer.valuesAsString(this);
    }

}
