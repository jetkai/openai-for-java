package io.github.jetkai.openai.api.impl.model;

import io.github.jetkai.openai.api.data.model.ModelPermissionsData;

import static java.util.Objects.requireNonNull;

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
public class ModelPermissionsBuilderImpl implements ModelPermissionsData.Builder {

    String id;
    String object;
    int created;
    boolean allowCreateEngine;
    boolean allowSampling;
    boolean allowLogprobs;
    boolean allowSearchIndices;
    boolean allowView;
    boolean allowFineTuning;
    String organization;
    String group;
    boolean isBlocking;

    public ModelPermissionsBuilderImpl() { }

    @Override
    public ModelPermissionsData.Builder setObject(String object) {
        requireNonNull(object);
        this.object = object;
        return this;
    }

    @Override
    public ModelPermissionsData.Builder setId(String id) {
        requireNonNull(id);
        this.id = id;
        return this;
    }

    @Override
    public ModelPermissionsData.Builder setCreated(int created) {
        this.created = created;
        return this;
    }

    @Override
    public ModelPermissionsData.Builder setAllowCreateEngine(boolean allowCreateEngine) {
        this.allowCreateEngine = allowCreateEngine;
        return this;
    }

    @Override
    public ModelPermissionsData.Builder setAllowFineTuning(boolean allowFineTuning) {
        this.allowFineTuning = allowFineTuning;
        return this;
    }

    @Override
    public ModelPermissionsData.Builder setAllowLogprobs(boolean allowLogprobs) {
        this.allowLogprobs = allowLogprobs;
        return this;
    }

    @Override
    public ModelPermissionsData.Builder setAllowSampling(boolean allowSampling) {
        this.allowSampling = allowSampling;
        return this;
    }

    @Override
    public ModelPermissionsData.Builder setAllowSearchIndices(boolean allowSearchIndices) {
        this.allowSearchIndices = allowSearchIndices;
        return this;
    }

    @Override
    public ModelPermissionsData.Builder setAllowView(boolean allowView) {
        this.allowView = allowView;
        return this;
    }

    @Override
    public ModelPermissionsData.Builder setBlocking(boolean blocking) {
        this.isBlocking = blocking;
        return this;
    }

    @Override
    public ModelPermissionsData.Builder setGroup(String group) {
        requireNonNull(group);
        this.group = group;
        return this;
    }

    @Override
    public ModelPermissionsData.Builder setOrganization(String organization) {
        requireNonNull(organization);
        this.organization = organization;
        return this;
    }

    @Override
    public ModelPermissionsData build() {
        return ModelPermissionsImpl.create(this);
    }
}