package att1.screens;

import att1.DAO.EmployeeDAO;
import att1.DAO.implementation.EmployeeDAOImpl;
import att1.screens.auth.LoginPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpPage extends JFrame {
    private JTextField email;
    private JPasswordField password;
    private JLabel emailLbl, passwordLbl, mainTxtLbl, userTypeLbl;
    private JCheckBox userType;
    private JButton signUpBtn, goBackBtn;

    protected final EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    public SignUpPage() {
        initComponents();
    }

    public void initComponents() {
        setTitle("Sign Up Page");
        setSize(new Dimension(400, 300));
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        emailLbl = new JLabel("Email:");
        passwordLbl = new JLabel("Password:");
        email = new JTextField(10);
        password = new JPasswordField(10);
        userType = new JCheckBox("Is Employee");
        goBackBtn = new JButton("Login");
        signUpBtn = new JButton("Sign Up");

        emailLbl.setBounds(127, 55, 200, 10);
        email.setBounds(127, 75, 150, 30);

        passwordLbl.setBounds(127, 115, 200, 10);
        password.setBounds(127, 135, 150, 30);

        userType.setBounds(127, 170, 200, 30);

        signUpBtn.setBounds(100, 200, 100, 40);
        goBackBtn.setBounds(200, 200, 100, 40);

        signUpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        goBackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginPage();
            }
        });

        add(emailLbl);
        add(email);
        add(passwordLbl);
        add(password);
        add(userType);
        add(signUpBtn);
        add(goBackBtn);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
