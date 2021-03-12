package org.wisdom.tool.model.CadJsonWrapper;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Type",
        "WCDocument",
        "LocalPath"
})
public class InputFile {

    @JsonProperty("Type")
    private String type;
    @JsonProperty("WCDocument")
    private WCDocument wCDocument;
    @JsonProperty("LocalPath")
    private String localPath;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Type")
    public String getType() {
        return type;
    }

    @JsonProperty("Type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("WCDocument")
    public WCDocument getWCDocument() {
        return wCDocument;
    }

    @JsonProperty("WCDocument")
    public void setWCDocument(WCDocument wCDocument) {
        this.wCDocument = wCDocument;
    }

    @JsonProperty("LocalPath")
    public String getLocalPath() {
        return localPath;
    }

    @JsonProperty("LocalPath")
    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}