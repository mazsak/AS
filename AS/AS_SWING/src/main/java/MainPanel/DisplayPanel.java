package MainPanel;

import Display.SelectionPanel;
import Display.ShowPanel;
import org.hibernate.sql.Select;

import javax.swing.*;
import java.awt.*;

public class DisplayPanel extends JPanel {

    private SelectionPanel selectionPanel = new SelectionPanel();
    private ShowPanel showPanel = new ShowPanel();

    public DisplayPanel() {
        setBackground(Color.GREEN);
        setLayout(new GridLayout(1,2));
        addPanel();
    }

    public void addPanel(){
        add(selectionPanel);
        selectionPanel.setSize(this.getWidth()/8, this.getHeight());
        add(showPanel);
    }
}
