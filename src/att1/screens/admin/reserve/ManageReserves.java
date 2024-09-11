package att1.screens.admin.reserve;

import att1.screens.admin.AdminPage;
import att1.screens.admin.room.RegisterRoom;
import att1.screens.admin.room.RoomsList;

import javax.swing.*;
import java.awt.*;

public class ManageReserves extends JFrame {
    private JPanel pane;
    private JButton reservesList, registerReserve, returnBtn;
    private JLabel maintxt;

    public ManageReserves() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Reserves Management Page");
        setSize(new Dimension(500, 400));
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        maintxt = new JLabel("Reserves Management Page");
        maintxt.setBounds(140, 0, 250, 50);
        maintxt.setFont(new Font("Tahoma", Font.BOLD, 15));

        reservesList = new JButton("Reserves List");
        reservesList.setBounds(150, 70, 200, 70);
        reservesList.addActionListener(e -> {
            dispose();
            new ReservesList();
        });

        registerReserve = new JButton("Register Reserve");
        registerReserve.setBounds(150, 170, 200, 70);
        registerReserve.addActionListener(e -> {
            dispose();
            new RegisterReserve();
        });

        returnBtn = new JButton("Return");
        returnBtn.setBounds(150,300, 200, 50);
        returnBtn.addActionListener(e -> {
            dispose();
            new AdminPage();
        });

        add(maintxt);
        add(registerReserve);
        add(reservesList);
        add(returnBtn);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
