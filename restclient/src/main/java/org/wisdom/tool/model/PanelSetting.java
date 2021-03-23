package org.wisdom.tool.model;
import org.wisdom.tool.constant.RESTConst;

public class PanelSetting {
    String subTitle = "";
    String mainTitle = "";
    String component1 = "";
    String component2 = "";
    String wcProject = "";
    String wcDoc = "";
    String outputFolderPath = "";
    String outputFilename = "";
    String errorLogFilePath = "";
    String wcUrl = "";
    String wcID = "";
    String wcPassword = "";
    String localPath = "";
    ConfigType type = ConfigType.DEFAULT;
    CadTaskType cadTaskType;
    CadInputType cadInputType;
    CadServerType serverType;
    boolean bNeedProgressBar = false;

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
        System.out.println("* setOutputFolderPath" + outputFolderPath);
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
    public String getMainTitle(){
        return this.mainTitle;
    }
    public String getSubTitle(){
        return this.subTitle;
    }
    public String getComponent1(){
        return this.component1;
    }
    public String getComponent2(){
        return this.component2;
    }
    public ConfigType getConfigType(){return this.type;}
    public boolean getNeedProgressBar() { return this.bNeedProgressBar;}

    //Setter
    public void setProgressBar(boolean bNeed){this.bNeedProgressBar = bNeed; }

    public void updatePanelSetting(String tooltipStr, String val){
        System.out.print("Pass to PanelSetting: " + tooltipStr + " :" + val + ".\n");

        if(tooltipStr.equals(RESTConst.PROJECT)){
            setWcProject(val);
        } else if(tooltipStr.equals(RESTConst.DOCUMENT)){
            setWcDoc(val);
        }else if(tooltipStr.equals(RESTConst.LOCALPATH)){
            setLocalPath(val);
        } else if(tooltipStr.equals(RESTConst.FOLDERPATH)){
            setOutputFolderPath(val);
        } else if(tooltipStr.equals(RESTConst.FILENAME)){
            setOutputFilename(val);
        } else if(tooltipStr.equals(RESTConst.ERRORLOGPATH)){
            setErrorLogFilePath(val);
        } else if(tooltipStr.equals(RESTConst.ID)){
            setWcID(val);
        } else if(tooltipStr.equals(RESTConst.PASSWORD)){
            setWcPassword(val);
        } else if(tooltipStr.equals(RESTConst.URL)){
            setWcUrl(val);
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

    /**
     * reset the member variables
     */
    public void reset(){
        this.subTitle = "";
        this.mainTitle = "";
        this.component1 = "";
        this.component2 = "";
        this.wcProject = "";
        this.wcDoc = "";
        this.outputFolderPath = "";
        this.outputFilename = "";
        this.errorLogFilePath = "";
        this.wcUrl = "";
        this.wcID = "";
        this.wcPassword = "";
        this.localPath = "";
    }
}
