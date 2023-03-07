package io.github.jetkai.openai.api.data.model.permissions;

import java.util.Optional;

/**
 * ModelPermissionsImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 02/03/2023}
 */
final class ModelPermissionsImpl extends ModelPermissionsData {

    private final String id;
    private final String object;
    private final int created;
    private final boolean allowCreateEngine;
    private final boolean allowSampling;
    private final boolean allowLogprobs;
    private final boolean allowSearchIndices;
    private final boolean allowView;
    private final boolean allowFineTuning;
    private final String organization;
    private final String group;
    private final boolean blocking;

    static ModelPermissionsImpl create(ModelPermissionsBuilderImpl builder) {
        return new ModelPermissionsImpl(builder);
    }

    private ModelPermissionsImpl(ModelPermissionsBuilderImpl builder) {
        this.allowCreateEngine = builder.allowCreateEngine;
        this.allowFineTuning = builder.allowFineTuning;
        this.id = builder.id;
        this.organization = builder.organization;
        this.created = builder.created;
        this.allowLogprobs = builder.allowLogprobs;
        this.allowSampling = builder.allowSampling;
        this.allowSearchIndices = builder.allowSearchIndices;
        this.allowView = builder.allowView;
        this.group = builder.group;
        this.blocking = builder.isBlocking;
        this.object = builder.object;
    }

    @Override
    public String getObject() {
        return this.object;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getCreated() {
        return this.created;
    }

    @Override
    public boolean isAllowCreationEngine() {
        return this.allowCreateEngine;
    }

    @Override
    public boolean isAllowFineTuning() {
        return this.allowFineTuning;
    }

    @Override
    public boolean isAllowLogprobs() {
        return this.allowLogprobs;
    }

    @Override
    public boolean isAllowSampling() {
        return this.allowSampling;
    }

    @Override
    public boolean isAllowSearchIndices() {
        return this.allowSearchIndices;
    }

    @Override
    public boolean isAllowView() {
        return this.allowView;
    }

    @Override
    public boolean isBlocking() {
        return this.blocking;
    }

    @Override
    public String getGroup() {
        return this.group;
    }

    @Override
    public String getOrganization() {
        return this.organization;
    }

    @Override
    public Optional<String> object() {
        return Optional.ofNullable(this.object);
    }

    @Override
    public Optional<String> id() {
        return Optional.ofNullable(this.id);
    }

    @Override
    public Optional<Integer> created() {
        return Optional.of(this.created);
    }

    @Override
    public Optional<Boolean> allowCreationEngine() {
        return Optional.of(this.allowCreateEngine);
    }

    @Override
    public Optional<Boolean> allowFineTuning() {
        return Optional.of(this.allowFineTuning);
    }

    @Override
    public Optional<Boolean> allowLogprobs() {
        return Optional.of(this.allowLogprobs);
    }

    @Override
    public Optional<Boolean> allowSampling() {
        return Optional.of(this.allowSampling);
    }

    @Override
    public Optional<Boolean> allowSearchIndices() {
        return Optional.of(this.allowSearchIndices);
    }

    @Override
    public Optional<Boolean> allowView() {
        return Optional.of(this.allowView);
    }

    @Override
    public Optional<Boolean> blocking() {
        return Optional.of(this.blocking);
    }

    @Override
    public Optional<String> group() {
        return Optional.ofNullable(this.group);
    }

    @Override
    public Optional<String> organization() {
        return Optional.ofNullable(this.organization);
    }

}