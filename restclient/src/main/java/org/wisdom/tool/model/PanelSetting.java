package org.wisdom.tool.model;

import org.wisdom.tool.constant.RESTConst;

public class PanelSetting {
    String subTitle = "";
    String mainTitle = "";
    String component1 = "";
    String component2 = "";
    ConfigType type = ConfigType.DEFAULT;
    boolean bNeedProgressBar = false;

    CadTaskType cadTaskType;
    CadInputType cadInputType;
    CadServerType serverType;

    //hmmm..
    String wcProject = "";
    String wcDoc = "";
    String outputFolderPath = "";
    String outputFilename = "";
    String errorLogFilePath = "";
    String wcUrl = "";
    String wcID = "";
    String wcPassword = "";
    String localPath = "";

    public CadTaskType getCadTaskType() {
        return cadTaskType;
    }

    public void setCadTaskType(CadTaskType cadTaskType) {
        this.cadTaskType = cadTaskType;
    }

    public CadInputType getCadInputType() {
        return cadInputType;
    }

    public void setCadInputType(CadInputType cadInputType) {
        this.cadInputType = cadInputType;
    }

    public CadServerType getServerType() {
        return serverType;
    }

    public void setServerType(CadServerType serverType) {
        this.serverType = serverType;
    }

    public String getWcProject() {
        return wcProject;
    }

    public void setWcProject(String wcProject) {
        this.wcProject = wcProject;
    }

    public String getWcDoc() {
        return wcDoc;
    }

    public void setWcDoc(String wcDoc) {
        this.wcDoc = wcDoc;
    }

    public String getOutputFolderPath() {
        return outputFolderPath;
    }

    public void setOutputFolderPath(String outputFolderPath) {
        this.outputFolderPath = outputFolderPath;
    }

    public String getOutputFilename() {
        return outputFilename;
    }

    public void setOutputFilename(String outputFilename) {
        this.outputFilename = outputFilename;
    }

    public String getErrorLogFilePath() {
        return errorLogFilePath;
    }

    public void setErrorLogFilePath(String errorLogFilePath) {
        this.errorLogFilePath = errorLogFilePath;
    }

    public String getWcUrl() {
        return wcUrl;
    }

    public void setWcUrl(String wcUrl) {
        this.wcUrl = wcUrl;
    }

    public String getWcID() {
        return wcID;
    }

    public void setWcID(String wcID) {
        this.wcID = wcID;
    }

    public String getWcPassword() {
        return wcPassword;
    }

    public void setWcPassword(String wcPassword) {
        this.wcPassword = wcPassword;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public PanelSetting(String mainTitle, String subTitle, String comp1, String comp2, ConfigType type){
        this.mainTitle = mainTitle;
        this.subTitle = subTitle;
        this.component1 = comp1;
        this.component2 = comp2;
        this.type = type;
        this.cadInputType = CadInputType.WCDocument;
        this.cadTaskType = CadTaskType.HELLO;
        this.serverType = CadServerType.WebCenter;
    }

    //Getter
    public String GetMainTitle(){
        return this.mainTitle;
    }
    public String GetSubTitle(){
        return this.subTitle;
    }
    public String GetComponent1(){
        return this.component1;
    }
    public String GetComponent2(){
        return this.component2;
    }
    public ConfigType GetConfigType(){return this.type;}
    public boolean GetNeedProgressBar() { return this.bNeedProgressBar;}

    //Setter
    public void SetProgressBar(boolean bNeed){this.bNeedProgressBar = bNeed; }

    public void UpdatePanelSetting(String tooltipStr, String val){
        if(tooltipStr.equals(RESTConst.PROJECT)){
            setWcProject(val);
            System.out.print("Pass to PanelSetting: " + tooltipStr + " :" + val + ".\n");
        } else if(tooltipStr.equals(RESTConst.DOCUMENT)){
            setWcDoc(val);
            System.out.print("Pass to PanelSetting: " + tooltipStr + " :" + val + ".\n");
        } else if(tooltipStr.equals(RESTConst.FOLDERPATH)){
            setOutputFolderPath(val);
            System.out.print("Pass to PanelSetting: " + tooltipStr + " :" + val + ".\n");
        } else if(tooltipStr.equals(RESTConst.FILENAME)){
            setOutputFilename(val);
            System.out.print("Pass to PanelSetting: " + tooltipStr + " :" + val + ".\n");
        } else if(tooltipStr.equals(RESTConst.ERRORLOGPATH)){
            setErrorLogFilePath(val);
            System.out.print("Pass to PanelSetting: " + tooltipStr + " :" + val + ".\n");
        } else if(tooltipStr.equals(RESTConst.ID)){
            setWcID(val);
            System.out.print("Pass to PanelSetting: " + tooltipStr + " :" + val + ".\n");
        } else if(tooltipStr.equals(RESTConst.PASSWORD)){
            setWcPassword(val);
            System.out.print("Pass to PanelSetting: " + tooltipStr + " :" + val + ".\n");
        } else if(tooltipStr.equals(RESTConst.URL)){
            setWcUrl(val);
            System.out.print("Pass to PanelSetting: " + tooltipStr + " :" + val + ".\n");
        }
    }

    public String toString() {
        return "\nPanelSetting info: \n"
                + "cadTaskType: " + cadTaskType.toString() + "\n  "
                + "InputType: " + cadInputType.toString() + "\n  "
                + "LocalPath: " + localPath + "\n "
                + "ServerType: " + serverType.toString() + "\n  "
                + "WC Project: " +wcProject + "\n "
                + "WC Document: " +wcDoc + "\n "
                + "Output Folder: " +outputFolderPath + "\n  "
                + "Output Filename: " +outputFilename + "\n "
                + "WC info: " + wcUrl + "\n  "
                + wcID + "\n  "
                + wcPassword + "\n";

    }
}
