package att1.screens.admin.room;

import att1.screens.admin.AdminPage;

import javax.swing.*;
import java.awt.*;

public class ManageRooms extends JFrame {
    private JPanel pane;
    private JButton roomsList, registerRoom, returnBtn;
    private JLabel maintxt;

    public ManageRooms() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Rooms Management Page");
        setSize(new Dimension(500, 400));
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        maintxt = new JLabel("Rooms Management Page");
        maintxt.setBounds(140, 0, 250, 50);
        maintxt.setFont(new Font("Tahoma", Font.BOLD, 15));

        roomsList = new JButton("Rooms List");
        roomsList.setBounds(150, 70, 200, 70);
        roomsList.addActionListener(e -> {
            dispose();
            new RoomsList();
        });

        registerRoom = new JButton("Register Room");
        registerRoom.setBounds(150, 170, 200, 70);
        registerRoom.addActionListener(e -> {
            dispose();
            new RegisterRoom();
        });

        returnBtn = new JButton("Return");
        returnBtn.setBounds(150,300, 200, 50);
        returnBtn.addActionListener(e -> {
            dispose();
            new AdminPage();
        });

        add(maintxt);
        add(registerRoom);
        add(roomsList);
        add(returnBtn);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
