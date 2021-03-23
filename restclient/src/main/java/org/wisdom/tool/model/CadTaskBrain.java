package org.wisdom.tool.model;

import org.wisdom.tool.constant.RESTConst;
import org.wisdom.tool.model.CadJsonWrapper.*;
import org.wisdom.tool.util.RESTUtil;

import javax.swing.*;

public class CadTaskBrain {
    private PanelSetting panelSettingForServer = null;
    private PanelSetting panelSettingForInput = null;
    private PanelSetting panelSettingForOutput = null;

    String url = "";
    String method = "";
    CadTaskType cadTaskType = CadTaskType.HELLO;

    //JSON Obj to pass around
    private CadTaskJson cadTaskJsonObj = null;
    ECadServerTask eCadServerTask = null;
    InputFile inputFile = null;
    ServerContext serverContext = null;
    WebCenter webCenter = null;
    WCDocument wcDoc = null;

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public CadTaskType getCadTaskType() {
        return cadTaskType;
    }

    public void setCadTaskType(CadTaskType cadTaskType) {
        this.cadTaskType = cadTaskType;
    }

    //PanelSettings
    public PanelSetting getPanelSettingForServer() {
        return panelSettingForServer;
    }

    public void setPanelSettingForServer(PanelSetting panelSettingForServer) {
        this.panelSettingForServer = panelSettingForServer;
    }

    public PanelSetting getPanelSettingForInput() {
        return panelSettingForInput;
    }

    public PanelSetting getPanelSettingForOutput() {
        return panelSettingForOutput;
    }

    public CadTaskBrain() {
        cadTaskJsonObj = new CadTaskJson();
        eCadServerTask = new ECadServerTask();
        inputFile = new InputFile();
        serverContext = new ServerContext();
        webCenter = new WebCenter();
        wcDoc = new WCDocument();

        //construct panel Setting to pass
        panelSettingForServer = new PanelSetting("", "WebCenter", RESTConst.ID, RESTConst.PASSWORD, ConfigType.SERVER);
        panelSettingForServer.setProgressBar(true);
        panelSettingForInput = new PanelSetting(RESTConst.INPUT, "org.wisdom.tool.model.CadJsonWrapper.WCDocument", RESTConst.PROJECT, RESTConst.DOCUMENT, ConfigType.INPUT);
        panelSettingForOutput = new PanelSetting("Output", "Location", RESTConst.FOLDERPATH, RESTConst.FILENAME, ConfigType.OUTPUT);

        //init url to Hello task
        ChangeCadTask(CadTaskType.HELLO);
    }

    /**
     * @param task
     */
    public void ChangeCadTask(CadTaskType task) {
        this.method = task.getHttpMethod();
        System.out.print("CadTaskBrain : " + method + "\n");
        url = RESTConst.CLOUD_URL;
        url += task.getEnd();
        System.out.print("CadTaskBrain : " + url + "\n");

        setCadTaskType(task);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Below set and getter is for the json object.
    // Setting ECadServerTask
    public void setOutputFolderPath(String path) {
        eCadServerTask.setOutputFolder(path);
    }

    //Setting ServerContext
    public void setServerContextObj(ServerContext context) {
        eCadServerTask.setServerContext(context);
    }

    public void setWebCenterInServerContext(WebCenter wc) {
        serverContext.setWebCenter(wc);
    }

    public void setServerTypeInServerContext(String type) {
        serverContext.setType(type);
    }

    //Setting WC obj
    public void setWCUsername(String id) {
        webCenter.setUsername(id);
    }

    public void setWCURL(String url) {
        webCenter.setURL(url);
    }

    public void setWCUserPassword(String password) {
        webCenter.setPassword(password);
    }

    public void setSVGOption(Boolean option) {
        SVGOptions svgOptions = new SVGOptions();
        svgOptions.setESVG(option);
        eCadServerTask.setSVGOptions(svgOptions);
    }

    public Boolean getSVGOption() {
        Boolean esvgOption = false;
        if (eCadServerTask != null) {
            if (eCadServerTask.getSVGOptions() != null) {
                esvgOption = eCadServerTask.getSVGOptions().getESVG();
            }
        }
        return esvgOption;
    }

    public void setTaskType(String name) {
        eCadServerTask.setTaskType(name);
    }

    public void setOutputFilename(String name) {
        eCadServerTask.setOutputFileName(name);
    }

    public void setRetErrFilePath(String path) {
        eCadServerTask.setRetErrFilePath(path);
    }

    //Setting Input file obj
    public void setInputFileObj(InputFile inputFile) {
        eCadServerTask.setInputFile(inputFile);
    }

    public void setTypeInInputFileObj(String inputType) {
        inputFile.setType(inputType);
    }

    public void setWCDocInInputFileObj(WCDocument wcdoc) {
        inputFile.setWCDocument(wcdoc);
    }

    //Setting WC document
    public void setProjectNameInWCDoc(String wcProjName) {
        wcDoc.setProjectName(wcProjName);
    }

    public void setDocNameInWCDoc(String docname) {
        wcDoc.setDocumentName(docname);
    }

    public void setLocalPathInInputFileObj(String localPath) {
        inputFile.setLocalPath(localPath);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Get inputs from Server Panel in GUI and transfer the data to JSON Object(serverContext, webCenter)
     */
    public void processServerPanel() {
        setWCUsername(panelSettingForServer.wcID);
        setWCUserPassword(panelSettingForServer.wcPassword);
        setServerTypeInServerContext(panelSettingForServer.getServerType().toString());
        System.out.println("WC address is " + panelSettingForServer.getWcUrl());
        setWCURL(panelSettingForServer.getWcUrl());
        System.out.println("Server Panel passed to the Brain: "
                + panelSettingForServer.wcID + ", "
                + panelSettingForServer.wcPassword + ", "
                + panelSettingForServer.getServerType().toString());
    }

    /**
     * Get inputs from the Input Panel in GUI and transfer the data to JSON Object(InputFile, WcDoc)
     */
    public void processInputPanel() {
        setProjectNameInWCDoc(panelSettingForInput.wcProject);
        setDocNameInWCDoc(panelSettingForInput.wcDoc);
        setTypeInInputFileObj(panelSettingForInput.getCadInputType().toString());
        setLocalPathInInputFileObj(panelSettingForInput.localPath);
        System.out.println("Input Panel passed to the Brain: "
                + panelSettingForInput.wcProject + ", "
                + panelSettingForInput.wcDoc + ", "
                + panelSettingForInput.localPath);
    }

    /**
     * Get inputs from the Output Panel in GUI and transfer the data to JSON Object (eCadServerTask)
     */
    public void processOutputPanel() {
        setOutputFolderPath(panelSettingForOutput.outputFolderPath);
        setOutputFilename(panelSettingForOutput.outputFilename);
        //SetRetErrFilePath(panelSettingForInput.errorLogFilePath);
        System.out.println("* Output Panel passed to the Brain: "
                + panelSettingForOutput.outputFolderPath + ", "
                + panelSettingForOutput.outputFilename);
    }

    /**
     * @return String contains JSON elements
     */
    public String getJsonString() {
        buildJsonObj();

        // Creating Object of ObjectMapper define in Jakson Api
        String jsonStr = RESTUtil.tojsonText(cadTaskJsonObj);
        System.out.println(jsonStr);

        return jsonStr;
    }

    /**
     * You need to call this method to build a json object in the brain to use.
     *
     * @return json object that initialized and has information from the gui or etc.
     */
    public CadTaskJson
    buildJsonObj() {
        //Set Cad Task
        setTaskType(cadTaskType.toString());
        if(cadTaskType.equals(CadTaskType.GEN_SVG)) {

        }
        //Panel to corresponding cadTaskJson Obj
        processServerPanel();
        processInputPanel();
        processOutputPanel();

        //Set up Main CadJsonObj with sub objs.
        cadTaskJsonObj.setECadServerTask(eCadServerTask);
        eCadServerTask.setServerContext(serverContext);
        serverContext.setWebCenter(webCenter);
        eCadServerTask.setInputFile(inputFile);
        inputFile.setWCDocument(wcDoc);

        return cadTaskJsonObj;
    }

    /**
     * @param cadTaskJson new JSON object we want to use to set panelSettings.
     */
    public void setPanelsFromJSONObject(CadTaskJson cadTaskJson) {
        //Update each panel.
        CadTaskType newCadTaskType = (CadTaskType) verifyType(ConfigType.CADTASK, cadTaskJsonObj.getECadServerTask().getTaskType());
        panelSettingForServer.setCadTaskType(newCadTaskType);
        panelSettingForServer.wcID = cadTaskJsonObj.getECadServerTask().getServerContext().getWebCenter().getUsername();
        panelSettingForServer.wcPassword = cadTaskJsonObj.getECadServerTask().getServerContext().getWebCenter().getPassword();
        panelSettingForServer.serverType = (CadServerType) verifyType(ConfigType.SERVER, cadTaskJsonObj.getECadServerTask().getServerContext().getType());
        panelSettingForServer.setWcUrl(cadTaskJsonObj.getECadServerTask().getServerContext().getWebCenter().getURL());
        System.out.println("JSON obj to the Brain: "
                + panelSettingForServer.wcID + ", "
                + panelSettingForServer.wcPassword + ", "
                + panelSettingForServer.getServerType().toString() + ", "
                + panelSettingForServer.wcUrl);

        panelSettingForInput.setCadTaskType(newCadTaskType);
        panelSettingForInput.wcProject = cadTaskJsonObj.getECadServerTask().getInputFile().getWCDocument().getProjectName();
        panelSettingForInput.wcDoc = cadTaskJsonObj.getECadServerTask().getInputFile().getWCDocument().getDocumentName();
        panelSettingForInput.cadInputType = (CadInputType) verifyType(ConfigType.INPUT, cadTaskJsonObj.getECadServerTask().getInputFile().getType());
        //CadInputType.valueOf(cadTaskJsonObj.getECadServerTask().getInputFile().getType());
        panelSettingForInput.localPath = cadTaskJsonObj.getECadServerTask().getInputFile().getLocalPath();
        System.out.println("JSON obj to the Brain: "
                + panelSettingForInput.wcProject + ", "
                + panelSettingForInput.wcDoc + ", " + panelSettingForInput.type + ", "
                + panelSettingForInput.localPath);

        panelSettingForOutput.setCadTaskType(newCadTaskType);
        panelSettingForOutput.outputFolderPath = cadTaskJsonObj.getECadServerTask().getOutputFolder();
        panelSettingForOutput.outputFilename = cadTaskJsonObj.getECadServerTask().getOutputFileName();
        //SetRetErrFilePath(panelSettingForInput.errorLogFilePath);
        System.out.println("* JSON obj to the Brain: "
                + panelSettingForOutput.outputFolderPath + ", "
                + panelSettingForOutput.outputFilename);

        //Update others in the brain
        ChangeCadTask(CadTaskType.valueOf(cadTaskJsonObj.getECadServerTask().getTaskType()));

        System.out.println("Change task type to : " + cadTaskJsonObj.getECadServerTask().getTaskType());
    }

    /**
     * @param configType Area of type belonging to
     * @param typeStr    Type that we want to set in String format
     * @return Verified Type
     */
    public Object verifyType(ConfigType configType, String typeStr) {
        String wrongArea = "";
        Object validObj = null;
        try {
            if (configType.equals(ConfigType.INPUT)) {
                wrongArea = ConfigType.INPUT.toString();
                validObj = CadInputType.valueOf(typeStr);
            } else if (configType.equals(ConfigType.SERVER)) {
                wrongArea = ConfigType.SERVER.toString();
                validObj = CadServerType.valueOf(typeStr);
            } else if (configType.equals(ConfigType.CADTASK)) {
                wrongArea = ConfigType.CADTASK.toString();
                validObj = CadTaskType.valueOf(typeStr);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "JSON has a wrong Type.( " + wrongArea + " )");
        }

        return validObj;
    }

    public void setCadTaskJsonObj(CadTaskJson cadTaskJsonObj) {
        this.cadTaskJsonObj = cadTaskJsonObj;
        this.eCadServerTask = cadTaskJsonObj.getECadServerTask();
        this.inputFile = cadTaskJsonObj.getECadServerTask().getInputFile();
        this.serverContext = cadTaskJsonObj.getECadServerTask().getServerContext();
        this.webCenter = cadTaskJsonObj.getECadServerTask().getServerContext().getWebCenter();
        this.wcDoc = cadTaskJsonObj.getECadServerTask().getInputFile().getWCDocument();
    }

    public CadTaskJson getCadTaskJsonObj() {
        return cadTaskJsonObj;
    }
}