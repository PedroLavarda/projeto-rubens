package att1.screens.admin.client;

import att1.screens.admin.AdminPage;

import javax.swing.*;
import java.awt.*;

public class ManageClients extends JFrame {
    private JPanel pane;
    private JButton clientsList, registerClients, returnBtn;
    private JLabel maintxt;

    public ManageClients() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Clients Management Page");
        setSize(new Dimension(500, 400));
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        // inicia a pagina

        maintxt = new JLabel("Client Management Page");
        maintxt.setBounds(140, 0, 250, 50);
        maintxt.setFont(new Font("Tahoma", Font.BOLD, 15));

        // inicia os botoes e seus listeners

        clientsList = new JButton("Clients List");
        clientsList.setBounds(150, 70, 200, 70);
        clientsList.addActionListener(e -> {
            dispose();
            new ClientsList();
        });

        registerClients = new JButton("Register Client");
        registerClients.setBounds(150, 170, 200, 70);
        registerClients.addActionListener(e -> {
            dispose();
            new RegisterClient();
        });

        returnBtn = new JButton("Return");
        returnBtn.setBounds(150,300, 200, 50);
        returnBtn.addActionListener(e -> {
            dispose();
            new AdminPage();
        });

        // adiciona os elementos na pagina

        add(maintxt);
        add(registerClients);
        add(clientsList);
        add(returnBtn);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}