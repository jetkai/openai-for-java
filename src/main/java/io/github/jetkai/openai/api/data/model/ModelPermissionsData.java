package io.github.jetkai.openai.api.data.model;

import java.util.Optional;

/**
 * ModelPermissionsData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * {@code - 03/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@SuppressWarnings("unused")
public abstract class ModelPermissionsData {

    public ModelPermissionsData() { }

    public interface Builder {
        Builder setObject(String object);
        Builder setId(String id);
        Builder setCreated(int created);
        Builder setAllowCreateEngine(boolean allowCreateEngine);
        Builder setAllowFineTuning(boolean allowFineTuning);
        Builder setAllowLogprobs(boolean allowLogprobs);
        Builder setAllowSampling(boolean allowSampling);
        Builder setAllowSearchIndices(boolean allowSearchIndices);
        Builder setAllowView(boolean allowView);
        Builder setBlocking(boolean blocking);
        Builder setGroup(String group);
        Builder setOrganization(String organization);
        ModelPermissionsData build();
    }

    public abstract Optional<String> object();
    public abstract Optional<String> id();
    public abstract Optional<Integer> created();
    public abstract Optional<Boolean> allowCreationEngine();
    public abstract Optional<Boolean> allowFineTuning();
    public abstract Optional<Boolean> allowLogprobs();
    public abstract Optional<Boolean> allowSampling();
    public abstract Optional<Boolean> allowSearchIndices();
    public abstract Optional<Boolean> allowView();
    public abstract Optional<Boolean> blocking();
    public abstract Optional<String> group();
    public abstract Optional<String> organization();

}