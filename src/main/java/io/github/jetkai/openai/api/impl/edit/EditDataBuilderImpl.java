package io.github.jetkai.openai.api.impl.edit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jetkai.openai.api.data.edit.EditData;

import static java.util.Objects.requireNonNull;

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
public class EditDataBuilderImpl implements EditData.Builder {

    /**
     * model
     * string
     * Required
     * <p>
     * ID of the model to use. You can use the text-davinci-edit-001 or
     * code-davinci-edit-001 model with this endpoint.
     */
    String model;

    /**
     * input
     * string
     * Optional
     * Defaults to ''
     * <p>
     * The input text to use as a starting point for the edit.
     */
    String input;

    /**
     * instruction
     * string
     * Required
     * <p>
     * The instruction that tells the model how to edit the prompt.
     */
    String instruction;

    /**
     * n
     * integer
     * Optional
     * Defaults to 1
     * <p>
     * How many edits to generate for the input and instruction.
     */
    int n;

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
    double temperature;

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
    double topP;

    @Override
    public EditData.Builder setModel(String model) {
        requireNonNull(model);
        this.model = model;
        return this;
    }

    @Override
    public EditData.Builder setTopP(double topP) {
        this.topP = topP;
        return this;
    }

    @Override
    public EditData.Builder setTemperature(double temperature) {
        this.temperature = temperature;
        return this;
    }

    @Override
    public EditData.Builder setN(int n) {
        this.n = n;
        return this;
    }

    @Override
    public EditData.Builder setInput(String input) {
        requireNonNull(input);
        this.input = input;
        return this;
    }

    @Override
    public EditData.Builder setInstruction(String instruction) {
        requireNonNull(instruction);
        this.instruction = instruction;
        return this;
    }

    @Override
    public EditData build() {
        return EditDataImpl.create(this);
    }

}
