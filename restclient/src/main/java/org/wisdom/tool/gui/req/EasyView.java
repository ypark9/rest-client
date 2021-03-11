package org.wisdom.tool.gui.req;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.wisdom.tool.constant.RESTConst;
import org.wisdom.tool.gui.util.BoxLayoutTemplate;
import org.wisdom.tool.gui.util.UIUtil;
import org.wisdom.tool.model.*;
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

    private JComboBox<CadTask> cbTask = null;

    private JComboBox<ServerType> cbServerType = null;

    private JButton btnStart = null;

    private JProgressBar pb = null;
    
    private Panel pnlTask = null;
    private Panel pnlSeverConfig = null;
    private Panel pnlInputConfig = null;
    private JPanel pnlOutputConfig = null;

    private RESTThd reqThd = null;

    private JTextField wcPath = null;
    private JTextField wcID = null;
    private JTextField wcPassword = null;
    private BoxLayoutTemplate pnlServer = null;
    private BoxLayoutTemplate pnlInput = null;
    private BoxLayoutTemplate pnlOutput = null;

    public EasyView()
    {
        this.init();
    }

    public ImageIcon getIconStart()
    {
        return iconStart;
    }

    public ImageIcon getIconStop()
    {
        return iconStop;
    }

    //public JComboBox<String> getCbUrl()
//    {
//        return cbUrl;
//    }

    public JComboBox<CadTask> getCbTask()
    {
        return cbTask;
    }

    public JComboBox<ServerType> getCbServerType()
    {
        return cbServerType;
    }

    public JButton getBtnStart()
    {
        return btnStart;
    }

    public Panel getPnlTask()
    {
        return pnlTask;
    }

    public JProgressBar getProgressBar()
    {
        return pb;
    }

    /**
     *
     * @Title: init
     * @Description: Component Initialization
     * @param
     * @return void
     * @throws
     */
    private void init()
    {
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
        cbTask = new JComboBox<CadTask>(CadTask.values());
        cbTask.setToolTipText(RESTConst.CADTASK);
        cbTask.addActionListener(this);
        //TASK LABEL
        JLabel lbCadTask = new JLabel(RESTConst.CADTASK + ": ");
        JLabel lbCadTaskOpt = new JLabel(RESTConst.OPTIONAL);
        JCheckBox optESVG = new JCheckBox(RESTConst.ESVG);
//        cbUrl = new JComboBox<String>();
//        cbUrl.setEditable(true);
//        cbUrl.setToolTipText(RESTConst.URL);
//        cbUrl.requestFocus();

        JPanel taskTypePanel = new JPanel();
        taskTypePanel.setLayout(new FlowLayout(FlowLayout.CENTER));;
        taskTypePanel.add(lbCadTask);
        taskTypePanel.add(cbTask);

        JPanel taskOptPanel = new JPanel();
        taskOptPanel.setLayout(new FlowLayout(FlowLayout.CENTER));;
        taskOptPanel.add(lbCadTaskOpt);
        taskOptPanel.add(optESVG);

        pnlTask.add(taskTypePanel, BorderLayout.WEST);
        pnlTask.add(taskOptPanel, BorderLayout.CENTER);
        pnlTask.add(btnStart, BorderLayout.EAST);

        //construct panal Setting to pass
        PanelSetting serverPanelSetup = new PanelSetting( "", "WebCenter", "ID","password", ConfigType.SERVER);
        PanelSetting inputPanelSetup = new PanelSetting( "Input", "WCDocument", "Project","Document", ConfigType.INPUT);
        PanelSetting outputPanelSetup = new PanelSetting( "Output", "Location", "Folder Path","Filename", ConfigType.OUTPUT);

        //Server setting panel
        pnlServer = new BoxLayoutTemplate(serverPanelSetup);
        //Input panel
        pnlInput = new BoxLayoutTemplate(inputPanelSetup);
        //output panel
        pnlOutput = new BoxLayoutTemplate(outputPanelSetup);

        this.add(pnlTask, BorderLayout.NORTH);
        this.add(pnlInput, BorderLayout.WEST);
        this.add(pnlOutput, BorderLayout.EAST);
        this.add(pnlServer, BorderLayout.SOUTH);

        // Set the window to be visible as the default to be false
        this.setBorder(BorderFactory.createTitledBorder(null, RESTConst.HTTP_REQUEST, TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
    }

    /**
     *
     * @Title: setReqView
     * @Description: Set HTTP request panel view
     * @param @param req
     * @return void
     * @throws
     */
    public void setReqView(HttpReq req)
    {
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
     *
     * @Title: reset
     * @Description: reset request view
     * @param
     * @return void
     * @throws
     */
    public void reset()
    {
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
     *
     * @Title: bdyPerformed
     * @Description: Performed body panel
     * @param @param src
     * @return void
     * @throws
     */
    private void bdyPerformed(Object src)
    {
//        if (!(src instanceof JComboBox))
//        {
//            return;
//        }
//
//        @SuppressWarnings("unchecked")
//        JComboBox<HttpMethod> cb = (JComboBox<HttpMethod>) src;
//        HttpMethod mthd = (HttpMethod) cb.getSelectedItem();
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
     *
     * @Title: btnStartPerformed
     * @Description: Performed start button
     * @param @param src
     * @return void
     * @throws
     */
    private void btnStartPerformed(Object src)
    {
//        if (!(src instanceof JButton))
//        {
//            return;
//        }
//
//        JButton btn = (JButton) src;
//        if (this.iconStop.equals(btn.getIcon()))
//        {
//            if (null == this.reqThd)
//            {
//                return;
//            }
//
//            this.reqThd.interrupt();
//
//            this.btnStart.setIcon(this.iconStart);
//            this.btnStart.setToolTipText(RESTConst.START);
//            this.btnStart.setEnabled(true);
//
//            this.pb.setVisible(false);
//            this.pb.setIndeterminate(false);
//            return;
//        }
//
//        try
//        {
//            this.btnStart.setIcon(this.iconStop);
//            this.btnStart.setToolTipText(RESTConst.STOP);
//            this.btnStart.setEnabled(false);
//
//            this.reqThd = new RESTThd();
//            this.reqThd.setName(RESTConst.REQ_THREAD);
//            this.reqThd.start();
//
//            this.btnStart.setEnabled(true);
//
//            this.pb.setVisible(true);
//            this.pb.setIndeterminate(true);
//        }
//        catch(Throwable e)
//        {
//            log.error("Failed to submit HTTP request.", e);
//        }
    }

    public void actionPerformed(ActionEvent e)
    {
//        this.bdyPerformed(e.getSource());
//        this.btnStartPerformed(e.getSource());
    }
}
