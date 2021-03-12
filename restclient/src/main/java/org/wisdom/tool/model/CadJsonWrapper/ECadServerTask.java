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
        "OutputFolder",
        "ServerContext",
        "SVGOptions",
        "TaskType",
        "OutputFileName",
        "RetErrFilePath",
        "InputFile"
})
public class ECadServerTask {

    @JsonProperty("OutputFolder")
    private String outputFolder;
    @JsonProperty("ServerContext")
    private ServerContext serverContext;
    @JsonProperty("SVGOptions")
    private SVGOptions sVGOptions;
    @JsonProperty("TaskType")
    private String taskType;
    @JsonProperty("OutputFileName")
    private String outputFileName;
    @JsonProperty("RetErrFilePath")
    private String retErrFilePath;
    @JsonProperty("InputFile")
    private InputFile inputFile;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("OutputFolder")
    public String getOutputFolder() {
        return outputFolder;
    }

    @JsonProperty("OutputFolder")
    public void setOutputFolder(String outputFolder) {
        this.outputFolder = outputFolder;
    }

    @JsonProperty("ServerContext")
    public ServerContext getServerContext() {
        return serverContext;
    }

    @JsonProperty("ServerContext")
    public void setServerContext(ServerContext serverContext) {
        this.serverContext = serverContext;
    }

    @JsonProperty("SVGOptions")
    public SVGOptions getSVGOptions() {
        return sVGOptions;
    }

    @JsonProperty("SVGOptions")
    public void setSVGOptions(SVGOptions sVGOptions) {
        this.sVGOptions = sVGOptions;
    }

    @JsonProperty("TaskType")
    public String getTaskType() {
        return taskType;
    }

    @JsonProperty("TaskType")
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    @JsonProperty("OutputFileName")
    public String getOutputFileName() {
        return outputFileName;
    }

    @JsonProperty("OutputFileName")
    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    @JsonProperty("RetErrFilePath")
    public String getRetErrFilePath() {
        return retErrFilePath;
    }

    @JsonProperty("RetErrFilePath")
    public void setRetErrFilePath(String retErrFilePath) {
        this.retErrFilePath = retErrFilePath;
    }

    @JsonProperty("InputFile")
    public InputFile getInputFile() {
        return inputFile;
    }

    @JsonProperty("InputFile")
    public void setInputFile(InputFile inputFile) {
        this.inputFile = inputFile;
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