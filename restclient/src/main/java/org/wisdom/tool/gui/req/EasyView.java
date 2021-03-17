package org.wisdom.tool.gui.req;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.wisdom.tool.constant.RESTConst;
import org.wisdom.tool.gui.util.BoxLayoutTemplate;
import org.wisdom.tool.gui.util.CadTaskBoxLayoutPanel;
import org.wisdom.tool.gui.util.UIUtil;
import org.wisdom.tool.model.*;
import org.wisdom.tool.model.CadJsonWrapper.CadTaskJson;
import org.wisdom.tool.thread.RESTThd;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EasyView extends JPanel implements ActionListener {
    private static final long serialVersionUID = -1299418241312495718L;

    private static Logger log = LogManager.getLogger(EasyView.class);

    private ImageIcon iconStart = null;
    private ImageIcon iconStop = null;

    private JComboBox<CadTaskType> cbTask = null;
    private JComboBox<CadServerType> cbServerType = null;

    private Panel pnlTask = null;
    private JPanel pnlTaskOpt = null;

    //Brain to handle JSON
    CadTaskBrain cadTaskBrain = null;
    private RESTThd reqThd = null;

    private JButton btnStart = null;
    private JTextField txtfldWcPath = null;
    private JTextField txtfldWcID = null;
    private JTextField txtfldWcPassword = null;

    private BoxLayoutTemplate pnlServer = null;
    private BoxLayoutTemplate pnlInput = null;
    private BoxLayoutTemplate pnlOutput = null;
    private CadTaskBoxLayoutPanel pnlCadTask = null;
    private PanelSetting serverPnlSetting;
    private PanelSetting inputPnlSetting;
    private PanelSetting outputPnlSetting;

    //Getter and Setter
    public EasyView() {  this.init(); }
    public ImageIcon getIconStart() { return iconStart; }
    public ImageIcon getIconStop() {
        return iconStop;
    }

    public JComboBox<CadTaskType> getCbTask() {
        return cbTask;
    }
    public JComboBox<CadServerType> getCbServerType() {
        return cbServerType;
    }

    public JButton getBtnStart() {
        return btnStart;
    }
    public Panel getPnlTask() {
        return pnlTask;
    }
    public JPanel getPnlTaskOpt() {
        return pnlTaskOpt;
    }

    public CadTaskBrain getCadTaskBrain() { return cadTaskBrain; }


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
        btnStart = new JButton(iconStart);
        btnStart.setName(RESTConst.START);
        btnStart.setToolTipText(RESTConst.START);
        btnStart.addActionListener(this);
        //TASK - COMBOBOX
        cbTask = new JComboBox<CadTaskType>(CadTaskType.values());

        cadTaskBrain =  new CadTaskBrain();
        cadTaskBrain.ChangeCadTask((CadTaskType) cbTask.getSelectedItem());

        //TASK LABEL
        JLabel lbCadTask = new JLabel(RESTConst.CADTASK + ": ");
        JLabel lbCadTaskOpt = new JLabel(RESTConst.OPTIONAL);
        JCheckBox optESVG = new JCheckBox(RESTConst.ESVG);

        //Task panel
        pnlCadTask = new CadTaskBoxLayoutPanel(this);
        //        pnlTaskType.setLayout(new FlowLayout(FlowLayout.CENTER));
        //        pnlTaskType.add(lbCadTask);
        //        pnlTaskType.add(cbTask);

        pnlTaskOpt = new JPanel();
        pnlTaskOpt.setLayout(new FlowLayout(FlowLayout.CENTER));

        pnlTaskOpt.add(lbCadTaskOpt);
        pnlTaskOpt.add(optESVG);
        pnlTaskOpt.setVisible(false);

        pnlTask.add(pnlCadTask, BorderLayout.WEST);
        pnlTask.add(pnlTaskOpt, BorderLayout.CENTER);
        pnlTask.add(btnStart, BorderLayout.EAST);

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
//        if (null == req)
//        {
//            return;
//        }
//
//        String ctype = StringUtils.EMPTY;
//        String charset = StringUtils.EMPTY;
//
//        String typeHdr = req.getHeaders().get(RESTConst.CONTENT_TYPE);
//        if (StringUtils.isNotBlank(typeHdr))
//        {
//            ctype = StringUtils.substringBefore(typeHdr, ";");
//            charset = StringUtils.substringAfter(typeHdr, "=");
//        }
//
//        cbUrl.setSelectedItem(req.getUrl());
//        cbMtd.setSelectedIndex(req.getMethod().getMid());
//
//        pnlBody.getCbBodyType().setSelectedItem(0);
//        pnlBody.getTxtAraBody().setText(req.getBody());
//        pnlBody.getCbContentType().setSelectedItem(ctype);
//        pnlBody.getCbCharset().setSelectedItem(charset);
//
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
//        // Reset URL
//        cbMtd.setSelectedIndex(0);
//        cbUrl.setSelectedItem(StringUtils.EMPTY);
//
//        // Reset body
//        pnlBody.getCbBodyType().setSelectedItem(0);
//        pnlBody.getTxtAraBody().setText(StringUtils.EMPTY);
//        pnlBody.getCbContentType().setSelectedIndex(0);
//        pnlBody.getCbCharset().setSelectedIndex(0);
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
    private void btnStartPerformed(Object src) {
        if (!(src instanceof JButton))
        {
            return;
        }
        //ask brain to create json for the upcoming process.
        String jsonStr = cadTaskBrain.GetJsonString();

        JButton btn = (JButton) src;
        if (this.iconStop.equals(btn.getIcon()))
        {
            if (null == this.reqThd)
            {
                return;
            }

            this.reqThd.interrupt();

            this.btnStart.setIcon(this.iconStart);
            this.btnStart.setToolTipText(RESTConst.START);
            this.btnStart.setEnabled(true);

            pnlServer.getProgressBar().setVisible(false);
            pnlServer.getProgressBar().setIndeterminate(false);
            return;
        }

        try
        {
            this.btnStart.setIcon(this.iconStop);
            this.btnStart.setToolTipText(RESTConst.STOP);
            this.btnStart.setEnabled(false);

            this.reqThd = new RESTThd();
            this.reqThd.SetRequestMode(1);
            this.reqThd.setName(RESTConst.REQ_THREAD);
            this.reqThd.start();

            this.btnStart.setEnabled(true);

            pnlServer.getProgressBar().setVisible(true);
            pnlServer.getProgressBar().setIndeterminate(true);
        }
        catch(Throwable e)
        {
            log.error("Failed to submit HTTP request.", e);
        }
    }

    public void actionPerformed(ActionEvent e) {
        System.out.print("EasyView : Action Triggered\n");
//        this.bdyPerformed(e.getSource());
        System.out.print("EasyView : ProgressBar initiated\n");
        this.btnStartPerformed(e.getSource());
    }

    public void UpdateGUI() {
        cbTask.setSelectedItem(cadTaskBrain.getCadTaskType());
        System.out.println("UPDATE TASK Combo to: "+ cadTaskBrain.getCadTaskType().toString());
        serverPnlSetting = cadTaskBrain.getPanelSettingForServer();
        inputPnlSetting = cadTaskBrain.getPanelSettingForInput();
        outputPnlSetting = cadTaskBrain.getPanelSettingForOutput();
        System.out.println("Server setting: " + serverPnlSetting.toString() + ", "
                + "Input setting: " + inputPnlSetting.toString() + ", "
                + "Output setting: " + outputPnlSetting.toString());
    }
}
