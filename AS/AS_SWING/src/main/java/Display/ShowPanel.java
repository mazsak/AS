package Display;

import javax.swing.*;
import java.awt.*;

public class ShowPanel extends JPanel {

    private OptionsPanel optionsPanel = new OptionsPanel();
    private ViewPanel viewPanel = new ViewPanel();

    public ShowPanel(){
        addPanel();
        setLayout(new GridLayout());
    }

    public void addPanel(){
        add(optionsPanel);
        optionsPanel.setSize(this.getWidth(), this.getHeight()/10);
        add(viewPanel);
    }

}
