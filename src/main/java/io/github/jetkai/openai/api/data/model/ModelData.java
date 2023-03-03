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
    private List<ModelPermissions> permission;

    public ModelData() { }

    public void setId(String id) {
        this.id = id;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public void setOwnedBy(String ownedBy) {
        this.ownedBy = ownedBy;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public void setPermission(List<ModelPermissions> permission) {
        this.permission = permission;
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

    public List<ModelPermissions> getPermission() {
        return permission;
    }

    @JsonIgnore
    public String toJson() {
        return JacksonJsonDeserializer.valuesAsString(this);
    }

}