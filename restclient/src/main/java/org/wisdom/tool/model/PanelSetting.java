package org.wisdom.tool.model;

import javax.swing.*;
import java.awt.*;

public class PanelSetting {
    String title = "";
    String borderTitle = "";
    String component1 = "";
    String component2 = "";
    ConfigType type = ConfigType.DEFAULT;

    public PanelSetting(String borderTitle, String title, String comp1, String comp2, ConfigType type){
        this.borderTitle = borderTitle;
        this.title = title;
        this.component1 = comp1;
        this.component2 = comp2;
        this.type = type;
    }

    //Getter
    public String GetBorderTitle(){
        return this.borderTitle;
    }
    public String GetTitle(){
        return this.title;
    }
    public String GetComponent1(){
        return this.component1;
    }
    public String GetComponent2(){
        return this.component2;
    }
    public ConfigType GetConfigType(){
        return this.type;
    }

    // GUI helper
    public JPanel makePanelForSettings(){
        JPanel b1 = new JPanel();
        b1.setMaximumSize(new Dimension((int) b1.getMaximumSize().getWidth(), 25));
        JLabel lbServerType = new JLabel("Server Type");
        JComboBox<type> cbServerType = new JComboBox<type>(type.values());
        cbServerType.setToolTipText("ServerType");
        cbServerType.setPreferredSize(new Dimension(150, 20));
        b1.add(lbServerType);
        b1.add(cbServerType);

        return b1;
    }
}
