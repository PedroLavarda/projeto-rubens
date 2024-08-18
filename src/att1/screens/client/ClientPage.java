package att1.screens.client;

import javax.swing.*;
import java.awt.*;

public class ClientPage extends JFrame {
    private JLabel label;

    public ClientPage() {
        initComponents();
    }

    public void initComponents() {
        setTitle("Client Page");
        setSize(new Dimension(700, 600));
        setLayout(null);
        setLocationRelativeTo(null);

        label = new JLabel("Client screen");

        add(label);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
