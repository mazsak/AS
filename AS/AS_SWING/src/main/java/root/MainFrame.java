package root;

import MainPanel.DisplayPanel;
import MainPanel.FastAccessPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private FastAccessPanel fastAccessPanel = new FastAccessPanel();
    private DisplayPanel displayPanel = new DisplayPanel();

    public MainFrame(){
        settingsWindow();
        addPanel();
    }

    public void addPanel(){
        add(fastAccessPanel);
        fastAccessPanel.setSize(this.getWidth(), this.getHeight()/8);
        add(displayPanel);
    }

    public void settingsWindow(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        Dimension minSize = new Dimension();
        minSize.setSize(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/1.5, Toolkit.getDefaultToolkit().getScreenSize().getHeight()/1.5);
        setMinimumSize(minSize);
        setVisible(true);
    }

}
