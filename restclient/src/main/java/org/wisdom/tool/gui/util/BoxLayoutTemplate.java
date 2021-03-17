package org.wisdom.tool.gui.util;

import org.wisdom.tool.constant.RESTConst;
import org.wisdom.tool.model.CadInputType;
import org.wisdom.tool.model.ConfigType;
import org.wisdom.tool.model.PanelSetting;
import org.wisdom.tool.model.CadServerType;


import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BoxLayoutTemplate extends JPanel implements ActionListener {

    PanelSetting panelSetting = null;

    public PanelSetting getPanelSetting() {
        return panelSetting;
    }

    public void setPanelSetting(PanelSetting panelSetting) {
        this.panelSetting = panelSetting;
    }

    String mainTitle = "";
    String subTitle = "";
    String component1 = "";
    String component2 = "";

    private JProgressBar pb = null;
    private JLabel subTitleLabel = null;
    public JTextField txtfldComp1;
    public JTextField txtfldComp2;
    ArrayList<JTextField> txtfldList = new ArrayList<JTextField>();
    ArrayList<JComboBox> comboBoxes = new ArrayList<JComboBox>();
    private int width = 400;
    private int height = 300;
    private JComboBox<CadServerType> cbServerType = null;
    private JComboBox<CadInputType> cbInputType = null;

    public String GetComponent1Input() {
        return txtfldComp1.getText();
    }

    public String GetComponent2Input() {
        return txtfldComp2.getText();
    }

    public JProgressBar getProgressBar() {
        return pb;
    }

    private JPanel GetMainPanel() {
        return this;
    }

    public BoxLayoutTemplate(PanelSetting pSetting) {
        this.init(pSetting);
    }

    public void init(final PanelSetting pSetting) {
        setPanelSetting(pSetting);
        this.setLayout(new BorderLayout(RESTConst.BORDER_WIDTH, RESTConst.BORDER_WIDTH));
        this.setBorder(BorderFactory.createEmptyBorder(RESTConst.BORDER_WIDTH, RESTConst.BORDER_WIDTH, RESTConst.BORDER_WIDTH, RESTConst.BORDER_WIDTH));
        JPanel global = new JPanel();
        global.setLayout(new BoxLayout(global, BoxLayout.Y_AXIS));
        global.setPreferredSize(new Dimension(width, height));
        global.setSize(width, height);
        global.setBounds(8, 5, width, height);
        global.setBorder(BorderFactory.createLineBorder(Color.black));
        global.setBackground(Color.white);

        // Instantiate a FocusListener ONCE
        java.awt.event.FocusListener myFocusListener = new java.awt.event.FocusListener() {
            public void focusGained(java.awt.event.FocusEvent focusEvent) {
                try {
                    JTextField src = (JTextField) focusEvent.getSource();
                    if (src.getText().equals("Text here!")) {
                        src.setText("");
                    }
                    String tooltip = src.getToolTipText();
                    System.out.print("TextField tooltip is " + tooltip + "\n");
                } catch (ClassCastException ignored) {
                    /* I only listen to JTextFields */
                }
            }

            public void focusLost(java.awt.event.FocusEvent focusEvent) {
                try {
                    JTextField src = (JTextField) focusEvent.getSource();
                    if (src.getText().equals("")) {
                        src.setText("Text here!");
                    } else {
                        pSetting.UpdatePanelSetting(src.getToolTipText(), src.getText());
                    }
                } catch (ClassCastException ignored) {
                    /* I only listen to JTextFields */
                }
            }
        };

        // Elements of global
        JPanel additionalPanel = makePanelForSettings(pSetting.GetConfigType());
        if (additionalPanel != null) {
            global.add(additionalPanel);
        }
        mainTitle = pSetting.GetMainTitle();
        subTitle = pSetting.GetSubTitle();
        component1 = pSetting.GetComponent1();
        component2 = pSetting.GetComponent2();

        subTitleLabel = new JLabel(subTitle);
        subTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subTitleLabel.setFont(new Font("tahoma", Font.BOLD, 12));
        global.add(subTitleLabel);
        JSeparator sep = new JSeparator();
        sep.setMaximumSize(new Dimension((int) sep.getMaximumSize().getWidth(), 50));
        global.add(sep);

        // Name Field
        JPanel c = new JPanel();
        c.setMaximumSize(new Dimension((int) c.getMaximumSize().getWidth(), 50));
        JLabel label = new JLabel(component1);
        txtfldComp1 = new JTextField(20);
        txtfldComp1.setPreferredSize(new Dimension(20, 25));
        txtfldComp1.addActionListener(this);
        txtfldComp1.addFocusListener(myFocusListener);
        txtfldComp1.setToolTipText(component1);
        c.add(label);
        c.add(txtfldComp1);
        global.add(c);

        // Hostname Field
        JPanel c1 = new JPanel();
        c1.setMaximumSize(new Dimension((int) c1.getMaximumSize().getWidth(), 50));
        JLabel label2 = new JLabel(component2);
        txtfldComp2 = new JTextField(20);
        txtfldComp2.setPreferredSize(new Dimension(20, 25));
        txtfldComp2.addActionListener(this);
        txtfldComp2.addFocusListener(myFocusListener);
        txtfldComp2.setToolTipText(component2);
        c1.add(label2);
        c1.add(txtfldComp2);
        global.add(c1);

        if (pSetting.GetConfigType() == ConfigType.SERVER) {
            JPanel panel = makeAdditionalComponentPanel(RESTConst.URL);
            global.add(panel);
        } else if (pSetting.GetConfigType() == ConfigType.INPUT) {
            JPanel panel = makeAdditionalComponentPanel(RESTConst.LOCALPATH);
            global.add(panel);
        } else if (pSetting.GetConfigType() == ConfigType.OUTPUT) {
//            JPanel panel = makeAdditionalComponentPanel(RESTConst.ERRORLOGPATH);
//            global.add(panel);
        }
        global.add(Box.createVerticalGlue());

        if (txtfldList.size() > 0) {
            for (int i = 0; i < txtfldList.size(); i++) {
                txtfldList.get(i).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // code what you want this field to do
                        System.out.print("textfield action triggered.\n");
                    }
                });

                txtfldList.get(i).addFocusListener(myFocusListener);
            }


        }
        if (comboBoxes.size() > 0) {
            for (int i = 0; i < comboBoxes.size(); i++)
                comboBoxes.get(i).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // code what you want this field to do
                        JComboBox src = (JComboBox) e.getSource();
                        String newItemname = src.getSelectedItem().toString();
                        System.out.print("Combobox action triggered. Selected item : " + newItemname + "\n");
                        subTitleLabel.setText(newItemname);
                        UpdateTypes();
                    }
                });
        }

        if (pSetting.GetNeedProgressBar()) {
            pb = new JProgressBar();
            pb.setVisible(false);
            global.add(pb);
        }

        this.add(global);
        this.setBorder(BorderFactory.createTitledBorder(null, mainTitle, TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
    }

    // Configure the gui based on the setting passed in.
    public JPanel makePanelForSettings(ConfigType type) {
        JPanel b1 = null;
        if (type.equals(ConfigType.INPUT)) {
            b1 = new JPanel();
            b1.setMaximumSize(new Dimension((int) b1.getMaximumSize().getWidth(), 25));
            JLabel lbServerType = new JLabel(type.name());
            JComboBox<CadInputType> cbInputType = new JComboBox<CadInputType>(CadInputType.values());
            cbInputType.setToolTipText(type.name());
            cbInputType.setPreferredSize(new Dimension(150, 20));
            comboBoxes.add(cbInputType);
            b1.add(lbServerType);
            b1.add(cbInputType);
        } else if (type.equals(ConfigType.SERVER)) {
            b1 = new JPanel();
            b1.setMaximumSize(new Dimension((int) b1.getMaximumSize().getWidth(), 25));
            JLabel lbServerType = new JLabel(type.name());
            JComboBox<CadServerType> cbServerType = new JComboBox<CadServerType>(CadServerType.values());
            cbServerType.setToolTipText(type.name());
            cbServerType.setPreferredSize(new Dimension(150, 20));
            comboBoxes.add(cbServerType);
            b1.add(lbServerType);
            b1.add(cbServerType);
        }

        return b1;
    }

    public JPanel makeAdditionalComponentPanel(String componentName) {
        JPanel panel = new JPanel();
        panel.setMaximumSize(new Dimension((int) panel.getMaximumSize().getWidth(), 50));
        JLabel label2 = new JLabel(componentName);
        JTextField jTextField = new JTextField(20);
        jTextField.setPreferredSize(new Dimension(20, 25));
        jTextField.setToolTipText(componentName);
        jTextField.addActionListener(this);
        panel.add(label2);
        panel.add(jTextField);
        txtfldList.add(jTextField);
        return panel;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.print("Global Action triggered.\n");
    }

//    public void UpdatePanelSetting(String tooltipStr, String val) {
//        if (tooltipStr.equals(RESTConst.PROJECT)) {
//            panelSetting.setWcProject(val);
//            System.out.print("Pass to PanelSetting: " + tooltipStr + " :" + val + ".\n");
//        } else if (tooltipStr.equals(RESTConst.DOCUMENT)) {
//            panelSetting.setWcDoc(val);
//            System.out.print("Pass to PanelSetting: " + tooltipStr + " :" + val + ".\n");
//        } else if (tooltipStr.equals(RESTConst.FOLDERPATH)) {
//            panelSetting.setOutputFolderPath(val);
//            System.out.print("Pass to PanelSetting: " + tooltipStr + " :" + val + ".\n");
//        } else if (tooltipStr.equals(RESTConst.FILENAME)) {
//            panelSetting.setOutputFilename(val);
//            System.out.print("Pass to PanelSetting: " + tooltipStr + " :" + val + ".\n");
//        } else if (tooltipStr.equals(RESTConst.ERRORLOGPATH)) {
//            panelSetting.setErrorLogFilePath(val);
//            System.out.print("Pass to PanelSetting: " + tooltipStr + " :" + val + ".\n");
//        } else if (tooltipStr.equals(RESTConst.ID)) {
//            panelSetting.setWcID(val);
//            System.out.print("Pass to PanelSetting: " + tooltipStr + " :" + val + ".\n");
//        } else if (tooltipStr.equals(RESTConst.PASSWORD)) {
//            panelSetting.setWcPassword(val);
//            System.out.print("Pass to PanelSetting: " + tooltipStr + " :" + val + ".\n");
//        } else if (tooltipStr.equals(RESTConst.URL)) {
//            panelSetting.setWcUrl(val);
//            System.out.print("Pass to PanelSetting: " + tooltipStr + " :" + val + ".\n");
//        } else if (tooltipStr.equals(RESTConst.LOCALPATH)) {
//            panelSetting.setLocalPath(val);
//            System.out.print("Pass to PanelSetting: " + tooltipStr + " :" + val + ".\n");
//        }
//    }

    public void UpdateTypes() {
        for (int i = 0; i < comboBoxes.size(); i++){
            if(comboBoxes.get(i).getToolTipText().equals(RESTConst.SERVER))
                panelSetting.setServerType((CadServerType) comboBoxes.get(i).getSelectedItem());
            else if(comboBoxes.get(i).getToolTipText().equals(RESTConst.INPUT))
                panelSetting.setCadInputType((CadInputType) comboBoxes.get(i).getSelectedItem());
        }
    }
}
