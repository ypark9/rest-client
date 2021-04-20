package org.wisdom.tool.gui.req;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.wisdom.tool.constant.RESTConst;
import org.wisdom.tool.gui.util.BoxLayoutTemplate;
import org.wisdom.tool.gui.util.CadTaskBoxLayoutPanel;
import org.wisdom.tool.gui.util.UIUtil;
import org.wisdom.tool.model.*;
import org.wisdom.tool.thread.RESTThd;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

public class EasyView extends JPanel implements ActionListener {
    private static final long serialVersionUID = -1299418241312495718L;

    private static Logger log = LogManager.getLogger(EasyView.class);

    private ImageIcon iconStart = null;
    private ImageIcon iconStop = null;

    private JComboBox<CadServerType> cbServerType = null;
    ArrayList<JCheckBox> optCheckboxes = new ArrayList<JCheckBox>();

    private Panel pnlTask = null;
    private JPanel pnlTaskOpt = null;

    //Brain to handle JSON
    CadTaskBrain cadTaskBrain = null;
    private RESTThd reqThd = null;

    private JButton btnEasyStart = null;

    private BoxLayoutTemplate pnlServer = null;
    private BoxLayoutTemplate pnlInput = null;
    private BoxLayoutTemplate pnlOutput = null;
    private CadTaskBoxLayoutPanel pnlCadTask = null;
    private PanelSetting serverPnlSetting;
    private PanelSetting inputPnlSetting;
    private PanelSetting outputPnlSetting;

    Map<String, String> headers;
    Map<String, String> cookies;

    //Getter and Setter
    public EasyView() {
        this.init();
    }

    public ImageIcon getIconStart() {
        return iconStart;
    }

    public ImageIcon getIconStop() {
        return iconStop;
    }

    public JComboBox<CadServerType> getCbServerType() {
        return cbServerType;
    }

    public JButton getBtnEasyStart() {
        return btnEasyStart;
    }

    public BoxLayoutTemplate getPnlServer() {
        return pnlServer;
    }

    public Map<String, String> getHeader() {
        return headers;
    }
    public void setHeaders(Map<String, String> rvHeaders) {
        this.headers = rvHeaders;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }
    public void setCookies(Map<String, String> rvCookies) {
        this.cookies = rvCookies;
    }

    public JPanel getPnlTaskOpt() {
        return pnlTaskOpt;
    }
    public CadTaskBrain getCadTaskBrain() {
        return cadTaskBrain;
    }

    /**
     * @param
     * @return void
     * @throws
     * @Title: init
     * @Description: Component Initialization
     */
    private void init() {
        this.setLayout(new BorderLayout(RESTConst.BORDER_WIDTH, RESTConst.BORDER_WIDTH));
        this.setBorder(BorderFactory.createEmptyBorder(RESTConst.BORDER_WIDTH, RESTConst.BORDER_WIDTH, RESTConst.BORDER_WIDTH, RESTConst.BORDER_WIDTH));

        //TOP TASK PANEL
        pnlTask = new Panel(new BorderLayout(RESTConst.BORDER_WIDTH, RESTConst.BORDER_WIDTH));

        //TASK PANEL COMPONENTS
        iconStart = UIUtil.getIcon(RESTConst.ICON_START);
        iconStop = UIUtil.getIcon(RESTConst.ICON_STOP);
        //TASK - START BUTTON
        btnEasyStart = new JButton(iconStart);
        btnEasyStart.setName(RESTConst.START);
        btnEasyStart.setToolTipText(RESTConst.START);
        btnEasyStart.addActionListener(this);

        cadTaskBrain = new CadTaskBrain();

        //TASK LABEL
        //JLabel lbCadTask = new JLabel(RESTConst.CADTASK + ": ");
        JLabel lbCadTaskOpt = new JLabel(RESTConst.OPTIONAL);

        //Task panel
        pnlCadTask = new CadTaskBoxLayoutPanel(this);

        pnlTaskOpt = new JPanel();
        pnlTaskOpt.setLayout(new FlowLayout(FlowLayout.CENTER));

        pnlTaskOpt.add(lbCadTaskOpt);

        //Add options to the panel.
        JCheckBox optESVG = new JCheckBox(RESTConst.ESVG);
        optESVG.setToolTipText(RESTConst.ESVG);
        optESVG.addActionListener(this);
        optCheckboxes.add(optESVG);
        pnlTaskOpt.add(optESVG);
        pnlTaskOpt.setVisible(false);

        pnlTask.add(pnlCadTask, BorderLayout.WEST);
        pnlTask.add(pnlTaskOpt, BorderLayout.CENTER);
        pnlTask.add(btnEasyStart, BorderLayout.EAST);

        //Server setting panel
        serverPnlSetting = cadTaskBrain.getPanelSettingForServer();
        inputPnlSetting = cadTaskBrain.getPanelSettingForInput();
        outputPnlSetting = cadTaskBrain.getPanelSettingForOutput();
        pnlServer = new BoxLayoutTemplate(serverPnlSetting);
        //Input panel
        pnlInput = new BoxLayoutTemplate(inputPnlSetting);
        //output panel
        pnlOutput = new BoxLayoutTemplate(outputPnlSetting);

        this.add(pnlTask, BorderLayout.NORTH);
        this.add(pnlInput, BorderLayout.WEST);
        this.add(pnlOutput, BorderLayout.EAST);
        this.add(pnlServer, BorderLayout.SOUTH);

        // Set the window to be visible as the default to be false
        this.setBorder(BorderFactory.createTitledBorder(null, RESTConst.HTTP_REQUEST, TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
    }

    /**
     * @param @param req
     * @return void
     * @throws
     * @Title: setReqView
     * @Description: Set HTTP request panel view
     */
    public void setReqView(HttpReq req) {

//        // Set headers
//        pnlHdr.getTabMdl().clear();
//        Map<String, String> hdrs = req.getHeaders();
//        if (MapUtils.isNotEmpty(hdrs))
//        {
//            for (Map.Entry<String, String> e : hdrs.entrySet())
//            {
//                if (RESTConst.CONTENT_TYPE.equalsIgnoreCase(e.getKey()))
//                {
//                    continue;
//                }
//
//                if (RESTConst.COOKIE.equalsIgnoreCase(e.getKey()))
//                {
//                    continue;
//                }
//
//                pnlHdr.getTabMdl().insertRow(e.getKey(), e.getValue());
//            }
//        }
//
//        // Set cookies
//        pnlCookie.getTabMdl().clear();
//        Map<String, String> cks = req.getCookies();
//        if (MapUtils.isNotEmpty(cks))
//        {
//            for (Map.Entry<String, String> e : cks.entrySet())
//            {
//                pnlCookie.getTabMdl().insertRow(e.getKey(), e.getValue());
//            }
//        }
    }

    /**
     * @param
     * @return void
     * @throws
     * @Title: reset
     * @Description: reset request view
     */
    public void reset() {
        pnlCadTask.changeCadTaskCombo(CadTaskType.HELLO);

        ArrayList<JTextField> inputTxtfldList = pnlInput.getTxtfldList();
        for (int i = 0; i < inputTxtfldList.size(); i++) {
            inputTxtfldList.get(i).setText("");
        }

        ArrayList<JTextField> outputTxtfldList = pnlOutput.getTxtfldList();
        for (int i = 0; i < outputTxtfldList.size(); i++) {
            outputTxtfldList.get(i).setText("");
        }

        ArrayList<JTextField> serverTxtfields = pnlServer.getTxtfldList();
        for (int i = 0; i < serverTxtfields.size(); i++) {
            serverTxtfields.get(i).setText("");
        }

        this.cadTaskBrain.getPanelSettingForServer().reset();
        this.cadTaskBrain.getPanelSettingForInput().reset();
        this.cadTaskBrain.getPanelSettingForOutput().reset();
//
//        // Reset header and cookie
//        pnlHdr.getTabMdl().clear();
//        pnlCookie.getTabMdl().clear();
    }

    /**
     * @param @param src
     * @return void
     * @throws
     * @Title: bdyPerformed
     * @Description: Performed body panel
     */
    private void bdyPerformed(Object src) {
//        if (!(src instanceof JComboBox))
//        {
//            return;
//        }
//
//        @SuppressWarnings("unchecked")
//        //JComboBox<HttpMethod> cb = (JComboBox<HttpMethod>) src;
//        //First one is Hello (GET)
//        HttpMethod mthd = HttpMethod.GET;
//        if (cbTask.getSelectedIndex() == 0) {
//            mthd = HttpMethod.GET;
//        }
//
//        if (HttpMethod.POST.equals(mthd) || HttpMethod.PUT.equals(mthd))
//        {
//            pnlBody.getCbBodyType().setSelectedIndex(0);
//            pnlBody.getCbBodyType().setEnabled(true);
//            pnlBody.getTxtAraBody().setEnabled(true);
//            pnlBody.getTxtAraBody().setBackground(Color.white);
//            pnlBody.getTxtFldPath().setEnabled(true);
//            pnlBody.getBtnLoadFile().setEnabled(true);
//        }
//        else
//        {
//            pnlBody.getCbBodyType().setSelectedIndex(0);
//            pnlBody.getCbBodyType().setEnabled(false);
//            pnlBody.getTxtAraBody().setEnabled(false);
//            pnlBody.getTxtAraBody().setText(StringUtils.EMPTY);
//            pnlBody.getTxtAraBody().setBackground(UIUtil.lightGray());
//            pnlBody.getTxtFldPath().setEnabled(false);
//            pnlBody.getBtnLoadFile().setEnabled(false);
//        }
//        cbUrl.requestFocus();
    }

    /**
     * @param @param src
     * @return void
     * @throws
     * @Title: btnStartPerformed
     * @Description: Performed start button
     */
    private void btnEasyStartPerformed(Object src) {
        if (!(src instanceof JButton)) {
            return;
        }
        //ask brain to create json for the upcoming process.
        String jsonStr = cadTaskBrain.getJsonString();

        JButton btn = (JButton) src;
        if (this.iconStop.equals(btn.getIcon())) {
            if (null == this.reqThd) {
                return;
            }

            this.reqThd.interrupt();

            this.btnEasyStart.setIcon(this.iconStart);
            this.btnEasyStart.setToolTipText(RESTConst.START);
            this.btnEasyStart.setEnabled(true);

            pnlServer.getProgressBar().setVisible(false);
            pnlServer.getProgressBar().setIndeterminate(false);
            return;
        }

        try {
            this.btnEasyStart.setIcon(this.iconStop);
            this.btnEasyStart.setToolTipText(RESTConst.STOP);
            this.btnEasyStart.setEnabled(false);

            this.reqThd = new RESTThd();
            this.reqThd.SetRequestMode(RESTConst.EASY_MODE);
            this.reqThd.setName(RESTConst.REQ_THREAD);
            this.reqThd.start();

            this.btnEasyStart.setEnabled(true);

            pnlServer.getProgressBar().setVisible(true);
            pnlServer.getProgressBar().setIndeterminate(true);
        } catch (Throwable e) {
            log.error("Failed to submit HTTP request.", e);
        }
    }

    /**
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        System.out.print("EasyView : Action Triggered\n");
        System.out.print("EasyView : ProgressBar initiated\n");
        Object src = e.getSource();
        if ((src instanceof JButton)) {
            this.btnEasyStartPerformed(e.getSource());
        } else if (src instanceof JCheckBox) {
            JCheckBox optCheckbox = (JCheckBox) src;
            cadTaskBrain.setSVGOption(optCheckbox.isSelected());
        }
    }

    /**
     *
     */
    public void UpdateGUI() {
        pnlCadTask.changeCadTaskCombo(cadTaskBrain.getCadTaskType());
        for (int i = 0; i < optCheckboxes.size(); i++) {
            if (optCheckboxes.get(i).getToolTipText().equals(RESTConst.ESVG)) {
                optCheckboxes.get(i).setSelected(cadTaskBrain.getSVGOption());
            }
        }
        System.out.println("UPDATE TASK Combo to: " + cadTaskBrain.getCadTaskType().toString());
        serverPnlSetting = cadTaskBrain.getPanelSettingForServer();
        inputPnlSetting = cadTaskBrain.getPanelSettingForInput();
        outputPnlSetting = cadTaskBrain.getPanelSettingForOutput();
        System.out.println("Server setting: " + serverPnlSetting.toString() + ", "
                + "Input setting: " + inputPnlSetting.toString() + ", "
                + "Output setting: " + outputPnlSetting.toString());

        pnlServer.ShowPanelSettingOnGUI();
        pnlInput.ShowPanelSettingOnGUI();
        pnlOutput.ShowPanelSettingOnGUI();
    }
}
