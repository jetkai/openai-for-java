package org.gpt.api.data.edit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@SuppressWarnings("unused")
public class EditData {

    /*
     * model
     * string
     * Required
     * ID of the model to use. You can use the text-davinci-edit-001 or
     * code-davinci-edit-001 model with this endpoint.
     */
    private String model;

    /*
     * input
     * string
     * Optional
     * Defaults to ''
     * The input text to use as a starting point for the edit.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String input;

    /*
     * instruction
     * string
     * Required
     * The instruction that tells the model how to edit the prompt.
     */
    private String instruction;

    /*
     * n
     * integer
     * Optional
     * Defaults to 1
     * How many edits to generate for the input and instruction.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int n;

    /*
     * temperature
     * number
     * Optional
     * Defaults to 1
     * What sampling temperature to use, between 0 and 2. Higher values like 0.8 will make the
     * output more random, while lower values like 0.2 will make it more focused and deterministic.
     *
     * We generally recommend altering this or top_p but not both.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private double temperature;

    /*
     * top_p
     * number
     * Optional
     * Defaults to 1
     * An alternative to sampling with temperature, called nucleus sampling, where the model considers
     * the results of the tokens with top_p probability mass. So 0.1 means only the tokens comprising
     * the top 10% probability mass are considered.
     *
     * We generally recommend altering this or temperature but not both.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("top_p")
    private double topP;

    public EditData() { }

    public void setModel(String model) {
        this.model = model;
    }

    public void setTopP(double topP) {
        this.topP = topP;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
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
