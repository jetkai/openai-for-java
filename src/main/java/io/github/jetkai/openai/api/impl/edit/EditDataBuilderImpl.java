package io.github.jetkai.openai.api.impl.edit;

import io.github.jetkai.openai.api.data.edit.EditData;

import static java.util.Objects.requireNonNull;

/**
 * EditDataBuilderImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 02/03/2023}
 */
public class EditDataBuilderImpl implements EditData.Builder {

    String model;
    String input;
    String instruction;
    int n;
    double temperature;
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
