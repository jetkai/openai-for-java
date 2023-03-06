package io.github.jetkai.openai.api.data.model;

import io.github.jetkai.openai.api.impl.model.ModelBuilderImpl;
import io.github.jetkai.openai.api.impl.model.ModelPermissionsImpl;

import java.util.List;
import java.util.Optional;

/**
 * ModelData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.1
 * {@code - 05/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@SuppressWarnings("unused")
public abstract class ModelData {

    public ModelData() { }

    public static ModelData newModelData() {
        return builder().build();
    }
    public static ModelData.Builder builder() {
        return new ModelBuilderImpl();
    }

    public interface Builder {
        Builder setId(String id);
        Builder setCreated(int created);
        Builder setObject(String object);
        Builder setParent(String parent);
        Builder setOwnedBy(String ownedBy);
        Builder setRoot(String root);
        Builder setPermissions(List<ModelPermissionsImpl> permission);

        ModelData build();
    }

    public abstract String getId();
    public abstract int getCreated();
    public abstract String getObject();
    public abstract String getParent();
    public abstract String getOwnedBy();
    public abstract String getRoot();
    public abstract List<ModelPermissionsImpl> getPermissions();


    public abstract Optional<String> id();
    public abstract Optional<Integer> created();
    public abstract Optional<String> object();
    public abstract Optional<String> parent();
    public abstract Optional<String> ownedBy();
    public abstract Optional<String> root();
    public abstract Optional<List<ModelPermissionsImpl>> permissions();

}