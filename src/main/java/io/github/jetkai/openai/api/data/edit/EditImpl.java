package io.github.jetkai.openai.api.data.edit;

import java.util.Optional;

/**
 * EditImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 02/03/2023}
 */
final class EditImpl extends EditData {

    private final String model;
    private final String input;
    private final String instruction;
    private final int n;
    private final double temperature;
    private final double topP;

    static EditImpl create(EditBuilderImpl builder) {
        return new EditImpl(builder);
    }

    private EditImpl(EditBuilderImpl builder) {
        this.model = builder.model;
        this.n = builder.n;
        this.temperature = builder.temperature;
        this.instruction = builder.instruction;
        this.topP = builder.topP;
        this.input = builder.input;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String getInput() {
        return this.input;
    }

    @Override
    public String getInstruction() {
        return this.instruction;
    }

    @Override
    public int getN() {
        return this.n;
    }

    @Override
    public double getTopP() {
        return this.topP;
    }

    @Override
    public double getTemperature() {
        return this.temperature;
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
