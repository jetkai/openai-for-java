package io.github.jetkai.openai.api.impl.edit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jetkai.openai.api.data.edit.EditData;

import java.util.Optional;

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
public class EditDataImpl extends EditData {

    /**
     * model
     * string
     * Required
     * <p>
     * ID of the model to use. You can use the text-davinci-edit-001 or
     * code-davinci-edit-001 model with this endpoint.
     */
    @JsonProperty("model")
    private final String model;

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
    private final String input;

    /**
     * instruction
     * string
     * Required
     * <p>
     * The instruction that tells the model how to edit the prompt.
     */
    @JsonProperty("instruction")
    private final String instruction;

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
    private final int n;

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
    private final double temperature;

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
    private final double topP;

    static EditDataImpl create(EditDataBuilderImpl builder) {
        return new EditDataImpl(builder);
    }

    private EditDataImpl(EditDataBuilderImpl builder) {
        this.model = builder.model;
        this.n = builder.n;
        this.temperature = builder.temperature;
        this.instruction = builder.instruction;
        this.topP = builder.topP;
        this.input = builder.input;
    }

    @Override
    public Optional<String> model() {
        return Optional.ofNullable(this.model);
    }

    @Override
    public Optional<Double> topP() {
        return Optional.of(this.topP);
    }

    @Override
    public Optional<Double> temperature() {
        return Optional.of(this.temperature);
    }

    @Override
    public Optional<Integer> n() {
        return Optional.of(this.n);
    }

    @Override
    public Optional<String> input() {
        return Optional.ofNullable(this.input);
    }

    @Override
    public Optional<String> instruction() {
        return Optional.ofNullable(this.instruction);
    }

}
