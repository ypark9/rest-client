package org.wisdom.tool.model;

import org.apache.logging.log4j.core.jmx.Server;

import javax.swing.*;
import java.awt.*;

public class PanelSetting {
    String title = "";
    String borderTitle = "";
    String component1 = "";
    String component2 = "";
    ConfigType type = ConfigType.DEFAULT;
    boolean bNeedProgressBar = false;

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
    public ConfigType GetConfigType(){return this.type;}
    public boolean GetNeedProgressBar() { return this.bNeedProgressBar;}

    //Setter
    public void SetProgressBar(boolean bNeed){this.bNeedProgressBar = bNeed; }

}
