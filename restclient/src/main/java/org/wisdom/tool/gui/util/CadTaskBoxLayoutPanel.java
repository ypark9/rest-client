package org.wisdom.tool.gui.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.wisdom.tool.constant.RESTConst;
import org.wisdom.tool.gui.req.EasyView;
import org.wisdom.tool.model.*;
import org.wisdom.tool.model.CadJsonWrapper.CadTaskJson;


import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;

public class CadTaskBoxLayoutPanel extends JPanel implements ActionListener {

    PanelSetting panelSetting = null;
    private JButton btnLoadJson;
    private EasyView easyView;
    private JFileChooser fc;

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

    private int width = 400;
    private int height = 100;
    private JComboBox<CadTaskType> cbCadTaskType = null;

    private JPanel GetMainPanel() {
        return this;
    }

    public CadTaskBoxLayoutPanel(EasyView easyView) {
        this.init(easyView);
    }

    public void init(final EasyView easyView) {
        this.easyView = easyView;
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
                        //UpdatePanelSetting(src.getToolTipText(), src.getText());
                    }
                } catch (ClassCastException ignored) {
                    /* I only listen to JTextFields */
                }
            }
        };

        JPanel b1 = new JPanel();
        b1.setMaximumSize(new Dimension((int) b1.getMaximumSize().getWidth(), 25));

        JLabel lbCadTaskApi = new JLabel(RESTConst.CADTASK);
        lbCadTaskApi.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbCadTaskApi.setFont(new Font("tahoma", Font.BOLD, 12));

        cbCadTaskType = new JComboBox<CadTaskType>(CadTaskType.values());
        cbCadTaskType.setToolTipText(RESTConst.CADTASK);
        cbCadTaskType.setPreferredSize(new Dimension(150, 20));
        cbCadTaskType.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                String selectedValue = cbCadTaskType.getSelectedItem().toString();
                System.out.print("Cad Task Selected :" + selectedValue + ". \n");
                if(selectedValue.equals(CadTaskType.HELLO.toString()))
                {
                    easyView.getPnlTaskOpt().setVisible(false);
                }
                else if(selectedValue.equals(CadTaskType.GEN_SVG.toString())){
                    easyView.getPnlTaskOpt().setVisible(true);
                }
                else if(selectedValue.equals(CadTaskType.GET_DESIGN_TYPE.toString())){
                    easyView.getPnlTaskOpt().setVisible(false);
                }
                easyView.getCadTaskBrain().ChangeCadTask((CadTaskType) cbCadTaskType.getSelectedItem());
            }
        });
        b1.add(lbCadTaskApi);
        b1.add(cbCadTaskType);

        //TASK - Load json BUTTON
        btnLoadJson = new JButton(RESTConst.LOAD_JSON_FILE);
        btnLoadJson.setName(RESTConst.LOAD_JSON_FILE);
        btnLoadJson.setToolTipText(RESTConst.LOAD_JSON_FILE);
        btnLoadJson.addActionListener(this);

        global.add(b1);
        global.add(btnLoadJson);
        global.add(Box.createVerticalGlue());

        this.add(global);
        this.setBorder(BorderFactory.createTitledBorder(null, mainTitle, TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));

        fc = new JFileChooser("c:\\Temp");
    }


    public void actionPerformed(ActionEvent e) {
        String srcString = e.getSource().toString();
        System.out.print("CadTaskBoxLayoutPanel Action triggered. " + srcString + " \n");
        Object src = e.getSource();
        if (!(src instanceof JButton))
        {
            return;
        }
        JButton btn = (JButton) src;
        if (!RESTConst.LOAD_JSON_FILE.equals(btn.getName()))
        {
            return;
        }

        String content = UIUtil.openFile(this, fc);
        if (StringUtils.isEmpty(content))
        {
            return;
        }

        //fill JSON Object
        try {
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();
            File sf = fc.getSelectedFile();
            // convert JSON file to map
            Map<?, ?> map = mapper.readValue(sf, Map.class);

            // print map entries
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
            }

            CadTaskJson cadTaskJson = mapper.readValue(sf, CadTaskJson.class);
            easyView.getCadTaskBrain().SetPanelsFromJSONObject(cadTaskJson);
            easyView.UpdateGUI();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
