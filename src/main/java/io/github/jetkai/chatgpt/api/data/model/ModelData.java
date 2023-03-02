package io.github.jetkai.chatgpt.api.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

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

    public ModelData(String id, String object, int created, String ownedBy, String root,
                     String parent, List<ModelPermissions> permission) {
        this.id = id;
        this.object = object;
        this.created = created;
        this.ownedBy = ownedBy;
        this.root = root;
        this.parent = parent;
        this.permission = permission;
    }

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
        ObjectMapper mapper = new ObjectMapper();
        String json;
        try {
            json = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

}