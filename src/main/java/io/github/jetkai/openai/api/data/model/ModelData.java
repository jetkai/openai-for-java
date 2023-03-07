package io.github.jetkai.openai.api.data.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jetkai.openai.api.impl.model.ModelBuilderImpl;

import java.util.List;
import java.util.Optional;

/**
 * ModelData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@JsonSerialize
@JsonDeserialize(builder = ModelData.Builder.class)
public abstract class ModelData {

    public ModelData() { }

    public static ModelData create() {
        return builder().build();
    }

    public static ModelData.Builder builder() {
        return new ModelBuilderImpl();
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public interface Builder {
        @JsonProperty("id")
        Builder setId(String id);
        @JsonProperty("created")
        Builder setCreated(int created);
        @JsonProperty("object")
        Builder setObject(String object);
        @JsonProperty("parent")
        Builder setParent(String parent);
        @JsonProperty("owned_by")
        Builder setOwnedBy(String ownedBy);
        @JsonProperty("root")
        Builder setRoot(String root);
        @JsonProperty("permission")
        Builder setPermissions(List<ModelPermissionsData> permission);
        ModelData build();

        @JsonCreator
        static Builder create() {
            return new ModelBuilderImpl();
        }
    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("id")
    public abstract String getId();

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("created")
    public abstract int getCreated();

    @JsonProperty("object")
    public abstract String getObject();

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("parent")
    public abstract String getParent();

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("owned_by")
    public abstract String getOwnedBy();

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("root")
    public abstract String getRoot();

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("permission")
    public abstract List<ModelPermissionsData> getPermissions();

    public abstract Optional<String> id();
    public abstract Optional<Integer> created();
    public abstract Optional<String> object();
    public abstract Optional<String> parent();
    public abstract Optional<String> ownedBy();
    public abstract Optional<String> root();
    public abstract Optional<List<ModelPermissionsData>> permissions();

}