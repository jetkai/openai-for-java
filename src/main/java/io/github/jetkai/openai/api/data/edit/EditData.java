package io.github.jetkai.openai.api.data.edit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jetkai.openai.api.impl.edit.EditDataBuilderImpl;

import java.util.Optional;

/**
 * EditData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@JsonSerialize
@SuppressWarnings("unused")
public abstract class EditData {

    public EditData() { }

    public static EditData.Builder builder() {
        return new EditDataBuilderImpl();
    }

    public static EditData create() {
        return builder().build();
    }

    public static EditData create(String input, String instruction) {
        return builder().setModel("text-davinci-edit-001")
                .setInput(input)
                .setInstruction(instruction)
                .build();
    }

    public static EditData create(String model, String input, String instruction) {
        return builder().setModel(model)
                .setInput(input)
                .setInstruction(instruction)
                .build();
    }

    public interface Builder {
        Builder setModel(String model);
        Builder setTopP(double topP);
        Builder setTemperature(double temperature);
        Builder setN(int n);
        Builder setInput(String input);
        Builder setInstruction(String instruction);
        EditData build();
    }

    /**
     * model
     * string
     * Required
     * <p>
     * ID of the model to use. You can use the text-davinci-edit-001 or
     * code-davinci-edit-001 model with this endpoint.
     */
    @JsonProperty("model")
    public abstract String getModel();

    /**
     * input
     * string
     * Optional
     * Defaults to ''
     * <p>
     * The input text to use as a starting point for the edit.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("input")
    public abstract String getInput();

    /**
     * instruction
     * string
     * Required
     * <p>
     * The instruction that tells the model how to edit the prompt.
     */
    @JsonProperty("instruction")
    public abstract String getInstruction();

    /**
     * n
     * integer
     * Optional
     * Defaults to 1
     * <p>
     * How many edits to generate for the input and instruction.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("n")
    public abstract int getN();


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
    public abstract double getTopP();

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
    @JsonProperty("temperature")
    public abstract double getTemperature();

    public abstract Optional<String> model();
    public abstract Optional<Double> topP();
    public abstract Optional<Double> temperature();
    public abstract Optional<Integer> n();
    public abstract Optional<String> input();
    public abstract Optional<String> instruction();

}
