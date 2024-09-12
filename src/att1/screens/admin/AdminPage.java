package att1.screens.admin;

import att1.screens.admin.client.ManageClients;
import att1.screens.admin.employee.ManageEmployees;
import att1.screens.admin.reserve.ManageReserves;
import att1.screens.admin.room.ManageRooms;
import att1.screens.auth.LoginPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage extends JFrame {
    private JLabel mainTxt;
    private JButton employeesBtn, clientsBtn, reservesBtn, roomsBtn, returnBtn;

    public AdminPage() {
        initComponents();
    }

    public void initComponents() {
        setTitle("Admin Page");
        setSize(new Dimension(700, 600));
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        // declara os elementos da pagina

        mainTxt = new JLabel("This is the admin page");
        mainTxt.setBounds(230,0, 300, 100);
        mainTxt.setFont(new Font("Tahoma", Font.BOLD, 20));

        // todos os botoes na pagina admin, descartam a pagina atual e abrem a nova pagina.

        employeesBtn = new JButton("Manage Employees");
        employeesBtn.setBounds(77,100, 250, 100);
        employeesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ManageEmployees();
            }
        });


        clientsBtn = new JButton("Manage Clients");
        clientsBtn.setBounds(377,100, 250, 100);
        clientsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ManageClients();
            }
        });


        reservesBtn = new JButton("Manage Reserves");
        reservesBtn.setBounds(77,270, 250, 100);
        reservesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ManageReserves();
            }
        });


        roomsBtn = new JButton("Manage Rooms");
        roomsBtn.setBounds(377,270, 250, 100);
        roomsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ManageRooms();
            }
        });

        returnBtn = new JButton("Return to login page");
        returnBtn.setBounds(200,450, 300, 70);
        returnBtn.addActionListener(e -> {
            dispose();
            new LoginPage();
        });


        add(mainTxt);
        add(employeesBtn);
        add(clientsBtn);
        add(reservesBtn);
        add(roomsBtn);
        add(returnBtn);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
