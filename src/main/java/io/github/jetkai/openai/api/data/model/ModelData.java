package io.github.jetkai.openai.api.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

import java.util.List;

/**
 * ModelData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * @created 02/03/2023
 * @last-update 03/03/2023
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@SuppressWarnings("unused")
public class ModelData {

    private String id; //text-babbage:001
    private String object; //model
    private int created; //1642018370
    @JsonProperty("owned_by")
    private String ownedBy; //openai
    private String root; //text-babbage:001
    private String parent; //null
    private List<ModelPermissionsData> permission;

    public ModelData() { }

    public ModelData setId(String id) {
        this.id = id;
        return this;
    }

    public ModelData setCreated(int created) {
        this.created = created;
        return this;
    }

    public ModelData setObject(String object) {
        this.object = object;
        return this;
    }

    public ModelData setParent(String parent) {
        this.parent = parent;
        return this;
    }

    public ModelData setOwnedBy(String ownedBy) {
        this.ownedBy = ownedBy;
        return this;
    }

    public ModelData setRoot(String root) {
        this.root = root;
        return this;
    }

    public ModelData setPermission(List<ModelPermissionsData> permission) {
        this.permission = permission;
        return this;
    }

    public String getId() {
        return id;
    }

    public String getObject() {
        return object;
    }

    public String getOwnedBy() {
        return ownedBy;
    }

    public int getCreated() {
        return created;
    }

    public String getParent() {
        return parent;
    }

    public String getRoot() {
        return root;
    }

    public List<ModelPermissionsData> getPermission() {
        return permission;
    }

    @JsonIgnore
    public String toJson() {
        return JacksonJsonDeserializer.valuesAsString(this);
    }

}