package org.wisdom.tool.gui.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.wisdom.tool.constant.RESTConst;
import org.wisdom.tool.gui.req.EasyView;
import org.wisdom.tool.model.*;
import org.wisdom.tool.model.CadJsonWrapper.CadTaskJson;
import org.wisdom.tool.util.RESTUtil;


import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CadTaskBoxLayoutPanel extends JPanel implements ActionListener {

    private JButton btnLoadJson;
    private JButton btnSaveJson;
    private EasyView easyView;
    private JFileChooser fileChooser;
    private Map<String, Boolean> options;
    String mainTitle = "";

    private int width = 400;
    private int height = 100;

    private JComboBox<CadTaskType> cbCadTaskType = null;
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

        JPanel b1 = new JPanel();
        b1.setMaximumSize(new Dimension((int) b1.getMaximumSize().getWidth(), 25));

        JLabel lbCadTaskApi = new JLabel(RESTConst.CADTASK);
        lbCadTaskApi.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbCadTaskApi.setFont(new Font("tahoma", Font.BOLD, 12));

        cbCadTaskType = new JComboBox<CadTaskType>(CadTaskType.values());
        cbCadTaskType.setToolTipText(RESTConst.CADTASK);
        cbCadTaskType.setPreferredSize(new Dimension(150, 20));
        //light weight popup can be hidden by other component near it. (They can be drawn above the light popup hence no show)
        cbCadTaskType.setLightWeightPopupEnabled(false);
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
        JPanel b2 = new JPanel();
        b2.setMaximumSize(new Dimension((int) b2.getMaximumSize().getWidth(), 25));
        btnLoadJson = new JButton(RESTConst.LOAD_JSON_FILE);
        btnLoadJson.setName(RESTConst.LOAD_JSON_FILE);
        btnLoadJson.setToolTipText(RESTConst.LOAD_JSON_FILE);
        btnLoadJson.addActionListener(this);

        btnSaveJson = new JButton(RESTConst.SAVE_JSON_FILE);
        btnSaveJson.setName(RESTConst.SAVE_JSON_FILE);
        btnSaveJson.setToolTipText(RESTConst.SAVE_JSON_FILE);
        btnSaveJson.addActionListener(this);

        b2.add(btnLoadJson);
        b2.add(btnSaveJson);

        global.add(b1);
        global.add(b2);
        global.add(Box.createVerticalGlue());

        this.add(global);
        this.setBorder(BorderFactory.createTitledBorder(null, mainTitle, TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));

        fileChooser = new JFileChooser("c:\\Temp");
        fileChooser.setPreferredSize(new Dimension(800, 600));

        options = new HashMap<String, Boolean>();
    }

    public void changeCadTaskCombo(CadTaskType type){
        cbCadTaskType.setSelectedItem(type);
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
        if (!RESTConst.LOAD_JSON_FILE.equals(btn.getName()) && !RESTConst.SAVE_JSON_FILE.equals(btn.getName()))
        {
            return;
        }
        if(RESTConst.LOAD_JSON_FILE.equals(btn.getName()))
        {
            String content = UIUtil.openFile(this, fileChooser);
            if (StringUtils.isEmpty(content)) {
                return;
            }

            //Fill JSON Object
            try {
                // create object mapper instance
                ObjectMapper mapper = new ObjectMapper();
                File sf = fileChooser.getSelectedFile();
                // convert JSON file to map
                Map<?, ?> map = mapper.readValue(sf, Map.class);

                // print map entries
                for (Map.Entry<?, ?> entry : map.entrySet()) {
                    System.out.println(entry.getKey() + "=" + entry.getValue());
                }

                CadTaskJson cadTaskJson = mapper.readValue(sf, CadTaskJson.class);
                easyView.getCadTaskBrain().setCadTaskJsonObj(cadTaskJson);
                easyView.getCadTaskBrain().setPanelsFromJSONObject(cadTaskJson);
                easyView.UpdateGUI();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else if(RESTConst.SAVE_JSON_FILE.equals(btn.getName())) {
            int retVal = fileChooser.showSaveDialog(this);
            if (JFileChooser.APPROVE_OPTION != retVal)
            {
                return;
            }
            File newJsonFile = fileChooser.getSelectedFile();
            RESTUtil.toJsonFile(newJsonFile, easyView.getCadTaskBrain().buildJsonObj());
        }
    }
}
