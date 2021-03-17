package org.wisdom.tool.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.wisdom.tool.constant.RESTConst;
import org.wisdom.tool.model.CadJsonWrapper.*;

import java.io.IOException;

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

    public void setPanelSettingForServer(PanelSetting panelSettingForServer) { this.panelSettingForServer = panelSettingForServer; }

    public PanelSetting getPanelSettingForInput() {
        return panelSettingForInput;
    }

    public void setPanelSettingForInput(PanelSetting panelSettingForInput) { this.panelSettingForInput = panelSettingForInput; }

    public PanelSetting getPanelSettingForOutput() {
        return panelSettingForOutput;
    }

    public void setPanelSettingForOutput(PanelSetting panelSettingForOutput) { this.panelSettingForOutput = panelSettingForOutput; }

    public CadTaskBrain() {
        cadTaskJsonObj = new CadTaskJson();
        eCadServerTask = new ECadServerTask();
        inputFile = new InputFile();
        serverContext = new ServerContext();
        webCenter = new WebCenter();
        wcDoc = new WCDocument();

        //construct panel Setting to pass
        panelSettingForServer = new PanelSetting("", "WebCenter", RESTConst.ID, RESTConst.PASSWORD, ConfigType.SERVER);
        panelSettingForServer.SetProgressBar(true);
        panelSettingForInput = new PanelSetting(RESTConst.INPUT, "org.wisdom.tool.model.CadJsonWrapper.WCDocument", RESTConst.PROJECT, RESTConst.DOCUMENT, ConfigType.INPUT);
        panelSettingForOutput = new PanelSetting("Output", "Location", RESTConst.FOLDERPATH, RESTConst.FILENAME, ConfigType.OUTPUT);

    }

    public void ChangeCadTask(CadTaskType task) {
        this.method = task.getHttpMethod();
        System.out.print("CadTaskBrain : " + method + "\n");
        url = RESTConst.LOCAL_URL;
        url += task.getEnd();
        System.out.print("CadTaskBrain : " + url + "\n");

        setCadTaskType(task);
        //System.out.print("Starting to build json for the task : " + task.toString() + "\n");
    }

    // Setting ECadServerTask
    public void SetOutputFolderPath(String path) {
        eCadServerTask.setOutputFolder(path);
    }

    //Setting ServerContext
    public void SetServerContextObj(ServerContext context) {
        eCadServerTask.setServerContext(context);
    }

    public void SetWebCenterInServerContext(WebCenter wc) {
        serverContext.setWebCenter(wc);
    }

    public void SetServerTypeInServerContext(String type) {
        serverContext.setType(type);
    }

    //Setting WC obj
    public void SetWCUsername(String id) {
        webCenter.setUsername(id);
    }

    public void SetWCURL(String url) {
        webCenter.setURL(url);
    }

    public void SetWCUserPassword(String password) {
        webCenter.setPassword(password);
    }

    public void SetSVGOption(Boolean option) {
        SVGOptions svgOptions = new SVGOptions();
        svgOptions.setESVG(option);
        eCadServerTask.setSVGOptions(svgOptions);
    }

    public void SetTaskType(String name) {
        eCadServerTask.setTaskType(name);
    }

    public void SetOutputFilename(String name) {
        eCadServerTask.setOutputFileName(name);
    }

    public void SetRetErrFilePath(String path) {
        eCadServerTask.setRetErrFilePath(path);
    }

    //Setting Input file obj
    public void SetInputFileObj(InputFile inputFile) {
        eCadServerTask.setInputFile(inputFile);
    }

    public void SetTypeInInputFileObj(String inputType) {
        inputFile.setType(inputType);
    }

    public void SetWCDocInInputFileObj(WCDocument wcdoc) {
        inputFile.setWCDocument(wcdoc);
    }

    //Setting WC document
    public void SetProjectNameinWCDoc(String wcProjName) {
        wcDoc.setProjectName(wcProjName);
    }

    public void SetDocNameinWCDoc(String docname) {
        wcDoc.setDocumentName(docname);
    }

    public void SetLocalPathInInputFileObj(String localPath) {
        inputFile.setLocalPath(localPath);
    }


    public void ProcessServerPanel() {
        SetWCUsername(panelSettingForServer.wcID);
        SetWCUserPassword(panelSettingForServer.wcPassword);
        SetServerTypeInServerContext(panelSettingForServer.getServerType().toString());
        //todo yopa need to set WC Address
        SetWCURL(RESTConst.WC04);
        System.out.println("Server Panel passed to the Brain: "
                + panelSettingForServer.wcID + ", "
                + panelSettingForServer.wcPassword + ", "
                + panelSettingForServer.getServerType().toString());
    }

    public void ProcessInputPanel() {
        SetProjectNameinWCDoc(panelSettingForInput.wcProject);
        SetDocNameinWCDoc(panelSettingForInput.wcDoc);
        SetTypeInInputFileObj(panelSettingForInput.getCadInputType().toString());
        SetLocalPathInInputFileObj(panelSettingForInput.localPath);
        System.out.println("Input Panel passed to the Brain: "
                + panelSettingForInput.wcProject + ", "
                + panelSettingForInput.wcDoc + ", "
                + panelSettingForInput.localPath);
    }

    public void ProcessOutputPanel() {
        SetOutputFolderPath(panelSettingForOutput.outputFolderPath);
        SetOutputFilename(panelSettingForOutput.outputFilename);
        //SetRetErrFilePath(panelSettingForInput.errorLogFilePath);
        System.out.println("Input Panel passed to the Brain: "
                + panelSettingForOutput.outputFolderPath + ", "
                + panelSettingForOutput.outputFilename);
    }

    public String GetJsonString() {

        ProcessServerPanel();
        ProcessInputPanel();
        ProcessOutputPanel();

        cadTaskJsonObj.setECadServerTask(eCadServerTask);
        eCadServerTask.setServerContext(serverContext);
        serverContext.setWebCenter(webCenter);
        eCadServerTask.setInputFile(inputFile);
        inputFile.setWCDocument(wcDoc);

        // Creating Object of ObjectMapper define in Jakson Api
        ObjectMapper Obj = new ObjectMapper();
        String ret = "";
        try {
            // get cadTaskJsonObj object as a json string
            ret = Obj.writeValueAsString(cadTaskJsonObj);
            // Displaying JSON String
            System.out.println(ret);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }

    public void SetPanelsFromJSONObject(CadTaskJson cadTaskJson) {
        cadTaskJsonObj = null;
        cadTaskJsonObj = cadTaskJson;

        //Update each panel.
        {
            panelSettingForServer.wcID = cadTaskJsonObj.getECadServerTask().getServerContext().getWebCenter().getUsername();
            panelSettingForServer.wcPassword = cadTaskJsonObj.getECadServerTask().getServerContext().getWebCenter().getPassword();
            panelSettingForServer.serverType = CadServerType.valueOf(cadTaskJsonObj.getECadServerTask().getServerContext().getType());
            //todo yopa need to set WC Address
            panelSettingForServer.setWcUrl(cadTaskJsonObj.getECadServerTask().getServerContext().getWebCenter().getURL());
            System.out.println("JSON obj to the Brain: "
                    + panelSettingForServer.wcID + ", "
                    + panelSettingForServer.wcPassword + ", "
                    + panelSettingForServer.getServerType().toString() + ", "
                    + panelSettingForServer.wcUrl);
        }

        {
            panelSettingForInput.wcProject = cadTaskJsonObj.getECadServerTask().getInputFile().getWCDocument().getProjectName();
            panelSettingForInput.wcDoc = cadTaskJsonObj.getECadServerTask().getInputFile().getWCDocument().getDocumentName();
            panelSettingForInput.cadInputType = CadInputType.valueOf(cadTaskJsonObj.getECadServerTask().getInputFile().getType());
            panelSettingForInput.localPath = cadTaskJsonObj.getECadServerTask().getInputFile().getLocalPath();
            System.out.println("JSON obj to the Brain: "
                    + panelSettingForInput.wcProject + ", "
                    + panelSettingForInput.wcDoc + ", "+ panelSettingForInput.type + ", "
                    + panelSettingForInput.localPath);
        }

        {
            panelSettingForOutput.outputFolderPath = cadTaskJsonObj.getECadServerTask().getOutputFolder();
            panelSettingForOutput.outputFilename = cadTaskJsonObj.getECadServerTask().getOutputFileName();
            //SetRetErrFilePath(panelSettingForInput.errorLogFilePath);
            System.out.println("JSON obj to the Brain: "
                    + panelSettingForOutput.outputFolderPath + ", "
                    + panelSettingForOutput.outputFilename);
        }

        //Update others in the brain
        ChangeCadTask(CadTaskType.valueOf(cadTaskJsonObj.getECadServerTask().getTaskType()));
        System.out.println("Change task type to : "+ cadTaskJsonObj.getECadServerTask().getTaskType());
    }
}
