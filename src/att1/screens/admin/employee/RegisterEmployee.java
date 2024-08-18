package att1.screens.admin.employee;

import javax.swing.*;
import java.awt.*;

public class RegisterEmployee extends JFrame {
    private JLabel mainTxt, nameLbl, emailLbl, passwordLbl, ageLbl, cpfLbl, salaryLbl, workHoursLbl;
    private JTextField nameField, emailField, ageField, cpfField, salaryField, workHoursField;
    private JPasswordField passwordField;

    private JButton submitBtn;

    public RegisterEmployee() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Register Employee");
        setSize(new Dimension(600, 400));
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        mainTxt = new JLabel("Register Employee Page");
        mainTxt.setFont(new Font("Tahoma", Font.BOLD, 15));
        mainTxt.setBounds(200, 10, 200, 60);

        nameLbl = new JLabel("Name:");
        nameLbl.setBounds(120, 80, 100, 30);
        emailLbl = new JLabel("Email:");
        emailLbl.setBounds(260, 80, 100, 30);
        passwordLbl = new JLabel("Password:");
        passwordLbl.setBounds(400, 80, 100, 30);

        ageLbl = new JLabel("Age:");
        ageLbl.setBounds(120, 140, 100, 30);
        cpfLbl = new JLabel("CPF:");
        cpfLbl.setBounds(260, 140, 100, 30);
        salaryLbl = new JLabel("Salary:");
        salaryLbl.setBounds(400, 140, 100, 30);
        workHoursLbl = new JLabel("Work Hours:");

        nameField = new JTextField();
        nameField.setBounds(120, 110, 110, 30);
        emailField = new JTextField();
        emailField.setBounds(260, 110, 110, 30);
        passwordField = new JPasswordField();
        passwordField.setBounds(400, 110, 110, 30);

        ageField = new JTextField();
        ageField.setBounds(120, 170, 110, 30);
        cpfField = new JTextField();
        cpfField.setBounds(260, 170, 110, 30);
        salaryField = new JTextField();
        salaryField.setBounds(400, 170, 110, 30);

        add(mainTxt);
        add(nameLbl);
        add(emailLbl);
        add(passwordLbl);
        add(ageLbl);
        add(salaryLbl);
        add(cpfLbl);

        add(nameField);
        add(emailField);
        add(passwordField);
        add(ageField);
        add(cpfField);
        add(salaryField);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
