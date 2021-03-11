package org.wisdom.tool.gui.util;
import org.wisdom.tool.constant.RESTConst;
import org.wisdom.tool.model.CadInputType;
import org.wisdom.tool.model.ConfigType;
import org.wisdom.tool.model.PanelSetting;
import org.wisdom.tool.model.ServerType;


import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class BoxLayoutTemplate extends JPanel {

    String borderTitle = "";
    String title = "";
    String component1 = "";
    String component2 = "";

    public JTextField txtfldComp1;
    public JTextField txtfldComp2;
    private int width = 400;
    private int height = 300;
    private JComboBox<ServerType> cbServerType = null;
    private JComboBox<CadInputType> cbInputType = null;

    public String GetComponent1Input(){return txtfldComp1.getText(); }
    public String GetComponent2Input(){return txtfldComp2.getText(); }

    public BoxLayoutTemplate(PanelSetting pSetting)
    {
        this.init(pSetting);
    }

    public void init(PanelSetting pSetting){
        this.setLayout(new BorderLayout(RESTConst.BORDER_WIDTH, RESTConst.BORDER_WIDTH));
        this.setBorder(BorderFactory.createEmptyBorder(RESTConst.BORDER_WIDTH, RESTConst.BORDER_WIDTH, RESTConst.BORDER_WIDTH, RESTConst.BORDER_WIDTH));

        JPanel global = new JPanel();
        global.setLayout(new BoxLayout(global, BoxLayout.Y_AXIS));
        global.setPreferredSize(new Dimension(width, height));
        global.setSize(width, height);
        global.setBounds(8, 5, width, height);
        global.setBorder(BorderFactory.createLineBorder(Color.black));
        global.setBackground(Color.white);
        // Elements of global
//        switch (type)
//        {
//            case INPUT:
//                borderTitle = "Input";
//                title = "WCDocument";
//                component1 = "Project";
//                component2 = "Document";
//                JPanel b = new JPanel();
//                b.setMaximumSize(new Dimension((int) b.getMaximumSize().getWidth(), 25));
//                JLabel lbInputType = new JLabel("Input Type");
//                cbInputType = new JComboBox<CadInputType>(CadInputType.values());
//                cbInputType.setToolTipText("Input Type");
//                cbInputType.setPreferredSize(new Dimension(150, 20));
//                b.add(lbInputType);
//                b.add(cbInputType);
//                global.add(b);
//                break;
//            case OUTPUT:
//                borderTitle = "Output";
//                title = "Location";
//                component1 = "Folder Path";
//                component2 = "Filename";
//                break;
//            case SERVER:
//                borderTitle = "";
//                title = "WebCenter";
//                component1 = "ID";
//                component2 = "password";
//                JPanel b1 = new JPanel();
//                b1.setMaximumSize(new Dimension((int) b1.getMaximumSize().getWidth(), 25));
//                JLabel lbServerType = new JLabel("Server Type");
//                JComboBox<ServerType> cbServerType = new JComboBox<ServerType>(ServerType.values());
//                cbServerType.setToolTipText("ServerType");
//                cbServerType.setPreferredSize(new Dimension(150, 20));
//                b1.add(lbServerType);
//                b1.add(cbServerType);
//                global.add(b1);
//                break;
//            case DEFAULT:
//        }
        JPanel additionalPanel = pSetting.makePanelForSettings();
        if(additionalPanel != null)
        {
            global.add(additionalPanel);
        }
        borderTitle = pSetting.GetBorderTitle();
        title = pSetting.GetTitle();
        component1 = pSetting.GetComponent1();
        component2 = pSetting.GetComponent2();

        JLabel label1 = new JLabel(title);
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        label1.setFont(new Font("tahoma", Font.BOLD, 12));
        global.add(label1);
        JSeparator sep = new JSeparator();
        sep.setMaximumSize(new Dimension((int) sep.getMaximumSize().getWidth(), 50));
        global.add(sep);

        // Name Field
        JPanel c = new JPanel();
        c.setMaximumSize(new Dimension((int) c.getMaximumSize().getWidth(), 50));
        JLabel label = new JLabel(component1);
        txtfldComp1 = new JTextField(20);
        txtfldComp1.setPreferredSize(new Dimension(20, 25));
        c.add(label);
        c.add(txtfldComp1);
        global.add(c);

        // Hostname Field
        JPanel c1 = new JPanel();
        c1.setMaximumSize(new Dimension((int) c1.getMaximumSize().getWidth(), 50));
        JLabel label2 = new JLabel(component2);
        txtfldComp2 = new JTextField(20);
        txtfldComp2.setPreferredSize(new Dimension(20, 25));
        c1.add(label2);
        c1.add(txtfldComp2);
        global.add(c1);
        global.add(Box.createVerticalGlue());

        this.add(global);
        this.setBorder(BorderFactory.createTitledBorder(null, borderTitle, TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
    }
}
