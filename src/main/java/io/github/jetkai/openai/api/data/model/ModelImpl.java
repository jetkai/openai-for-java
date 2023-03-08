package io.github.jetkai.openai.api.data.model;

import io.github.jetkai.openai.api.data.model.permissions.ModelPermissionsData;

import java.util.List;
import java.util.Optional;

/**
 * ModelImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.1
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 07/03/2023}
 */
final class ModelImpl extends ModelData {

    private final String id; //text-babbage:001
    private final String object; //model
    private final int created; //1642018370
    private final String ownedBy; //openai
    private final String root; //text-babbage:001
    private final String parent; //null
    private final List<ModelPermissionsData> permission;

    static ModelImpl create(ModelBuilderImpl builder) {
        return new ModelImpl(builder);
    }

    private ModelImpl(ModelBuilderImpl builder) {
        this.created = builder.created;
        this.permission = builder.permission;
        this.id = builder.id;
        this.root = builder.root;
        this.object = builder.object;
        this.parent = builder.parent;
        this.ownedBy = builder.ownedBy;
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
    public String getObject() {
        return this.object;
    }

    @Override
    public String getParent() {
        return this.parent;
    }

    @Override
    public String getOwnedBy() {
        return this.ownedBy;
    }

    @Override
    public String getRoot() {
        return this.root;
    }

    @Override
    public List<ModelPermissionsData> getPermissions() {
        return this.permission;
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
    public Optional<String> object() {
        return Optional.ofNullable(this.object);
    }

    @Override
    public Optional<String> parent() {
        return Optional.ofNullable(this.parent);
    }

    @Override
    public Optional<String> ownedBy() {
        return Optional.ofNullable(this.ownedBy);
    }

    @Override
    public Optional<String> root() {
        return Optional.ofNullable(this.root);
    }

    @Override
    public Optional<List<ModelPermissionsData>> permissions() {
        return Optional.ofNullable(this.permission);
    }

}