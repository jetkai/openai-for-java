package io.github.jetkai.openai.api.data.edit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jetkai.openai.api.impl.edit.EditDataBuilderImpl;

import java.util.Optional;

/**
 * EditData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.1
 * {@code - 05/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@SuppressWarnings("unused")
public abstract class EditData {

    public EditData() { }

    public static EditData newAudioData() {
        return builder().build();
    }

    public static EditData.Builder builder() {
        return new EditDataBuilderImpl();
    }

    public static EditData create() {
        return builder().build();
    }

    public static EditData create(String input, String instruction) {
        return builder().setModel("text-davinci-edit-001")
                .setInput(input)
                .setInstruction(instruction).build();
    }

    public static EditData create(String model, String input, String instruction) {
        return builder().setModel(model)
                .setInput(input)
                .setInstruction(instruction).build();
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

    public abstract Optional<String> model();
    public abstract Optional<Double> topP();
    public abstract Optional<Double> temperature();
    public abstract Optional<Integer> n();
    public abstract Optional<String> input();
    public abstract Optional<String> instruction();

}
