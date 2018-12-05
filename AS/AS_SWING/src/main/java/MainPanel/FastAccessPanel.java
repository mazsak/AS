package MainPanel;

import javax.swing.*;
import java.awt.*;

public class FastAccessPanel extends JPanel {

    private JButton button1 = new JButton("cos");
    private JButton button2 = new JButton("cos1");
    private JButton button3 = new JButton("cos2");
    private JButton button4 = new JButton("cos3");

    public FastAccessPanel() {
        setBackground(Color.BLACK);
        setLayout(new GridLayout());
        addButton();
    }

    public void addButton(){
        add(button1);
        add(button2);
        add(button3);
        add(button4);
    }
}
