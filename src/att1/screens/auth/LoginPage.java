package att1.screens.auth;

import att1.dao.EmployeeDAO;
import att1.dao.implementation.EmployeeDAOImpl;
import att1.db.DB;
import att1.screens.client.ClientPage;
import att1.screens.admin.AdminPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPage extends JFrame {
    private JButton loginButton, register;
    private JTextField email;
    private JPasswordField password;
    private JLabel emailLbl, passwordLbl, mainTxtLbl, userTypeLbl;
    private JCheckBox userType;

    protected final EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    public LoginPage() {
        initComponents();
    }

    public void initComponents() {
        setTitle("Login Page");
        setSize(new Dimension(400, 300));
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        mainTxtLbl = new JLabel("Welcome to the hotel Management System");
        emailLbl = new JLabel("Email:");
        passwordLbl = new JLabel("Password:");
        loginButton = new JButton("Login");
        email = new JTextField(10);
        password = new JPasswordField(10);

        mainTxtLbl.setBounds(50, 20, 350, 20);

        emailLbl.setBounds(127, 55, 200, 10);
        email.setBounds(127, 75, 150, 30);

        passwordLbl.setBounds(127, 115, 200, 10);
        password.setBounds(127, 135, 150, 30);

        loginButton.setBounds(100, 200, 100, 40);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(new String(password.getPassword()).isBlank() || email.getText().isBlank()) {
                    JOptionPane.showMessageDialog(LoginPage.this, "Please enter a valid email address or password");
                    return;
                }

                try {
                    boolean logged = employeeDAO.login(email.getText(), new String(password.getPassword()));

                    if(logged) {
                        dispose();
                        new AdminPage();
                    } else {
                        JOptionPane.showMessageDialog(LoginPage.this, "Invalid email or password");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        register = new JButton("Register");
        register.setBounds(200, 200, 100, 40);
        register.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SignUpPage();
            }
        });

        add(mainTxtLbl);
        add(emailLbl);
        add(email);
        add(passwordLbl);
        add(password);
        add(loginButton);
        add(register);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
