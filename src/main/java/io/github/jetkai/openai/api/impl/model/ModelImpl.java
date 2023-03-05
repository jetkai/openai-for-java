package io.github.jetkai.openai.api.impl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jetkai.openai.api.data.model.ModelData;

import java.util.List;
import java.util.Optional;

/**
 * ModelImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.1
 * {@code - 05/03/2023}
 * @since 1.0.0
 * {@code - 05/03/2023}
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@SuppressWarnings("unused")
public class ModelImpl extends ModelData {

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("id")
    private String id; //text-babbage:001
    @JsonProperty("object")
    private String object; //model
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("created")
    private int created; //1642018370
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("owned_by")
    private String ownedBy; //openai
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("root")
    private String root; //text-babbage:001
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("parent")
    private String parent; //null
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("permission")
    private List<ModelPermissionsImpl> permission;

    public ModelImpl() { }

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
    public Optional<List<ModelPermissionsImpl>> permissions() {
        return Optional.ofNullable(this.permission);
    }

}