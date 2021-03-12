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
        "ECadServerTask"
})
public class CadTaskJson {

    @JsonProperty("ECadServerTask")
    private ECadServerTask eCadServerTask;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ECadServerTask")
    public ECadServerTask getECadServerTask() {
        return eCadServerTask;
    }

    @JsonProperty("ECadServerTask")
    public void setECadServerTask(ECadServerTask eCadServerTask) {
        this.eCadServerTask = eCadServerTask;
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