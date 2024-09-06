package att1.screens.admin.employee;

import att1.screens.admin.AdminPage;

import javax.swing.*;
import java.awt.*;

public class RegisterEmployee extends JFrame {
    private JLabel mainTxt, nameLbl, emailLbl, passwordLbl, ageLbl, cpfLbl, salaryLbl, workHoursLbl;
    private JLabel  addressTxt, streetLbl, houseNumberLbl, countryLbl, stateLbl, cityLbl, zipLbl;
    private JTextField nameField, emailField, ageField, cpfField, salaryField, workHoursField;
    private JTextField streetField, houseNumberField, countryField, stateField, cityField, zipField;
    private JPasswordField passwordField;

    private JButton submitBtn, returnBtn;

    public RegisterEmployee() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Register Employee");
        setSize(new Dimension(600, 450));
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        mainTxt = new JLabel("Register Employee Page");
        mainTxt.setFont(new Font("Tahoma", Font.BOLD, 15));
        mainTxt.setBounds(200, 1, 200, 60);

        // informações pessoais do employee
        nameLbl = new JLabel("Name:");
        nameLbl.setBounds(120, 40, 100, 30);
        emailLbl = new JLabel("Email:");
        emailLbl.setBounds(260, 40, 100, 30);
        passwordLbl = new JLabel("Password:");
        passwordLbl.setBounds(400, 40, 100, 30);
        ageLbl = new JLabel("Age:");
        ageLbl.setBounds(120, 100, 100, 30);
        cpfLbl = new JLabel("CPF:");
        cpfLbl.setBounds(260, 100, 100, 30);
        salaryLbl = new JLabel("Salary:");
        salaryLbl.setBounds(400, 100, 100, 30);

        nameField = new JTextField();
        nameField.setBounds(120, 70, 110, 30);
        emailField = new JTextField();
        emailField.setBounds(260, 70, 110, 30);
        passwordField = new JPasswordField();
        passwordField.setBounds(400, 70, 110, 30);

        ageField = new JTextField();
        ageField.setBounds(120, 130, 110, 30);
        cpfField = new JTextField();
        cpfField.setBounds(260, 130, 110, 30);
        salaryField = new JTextField();
        salaryField.setBounds(400, 130, 110, 30);

        // informações do endereço do employee
        addressTxt = new JLabel("Address information:");
        addressTxt.setFont(new Font("Tahoma", Font.BOLD, 15));
        addressTxt.setBounds(220, 180, 200, 60);

        streetLbl = new JLabel("Street:");
        streetLbl.setBounds(120, 220, 100, 30);
        houseNumberLbl = new JLabel("House Nb:");
        houseNumberLbl.setBounds(260, 220, 100, 30);
        countryLbl = new JLabel("Country:");
        countryLbl.setBounds(400, 220, 100, 30);
        stateLbl = new JLabel("State:");
        stateLbl.setBounds(120, 280, 100, 30);
        cityLbl = new JLabel("City:");
        cityLbl.setBounds(260, 280, 100, 30);
        zipLbl = new JLabel("Zip:");
        zipLbl.setBounds(400, 280, 100, 30);

        streetField = new JTextField();
        streetField.setBounds(120, 250, 110, 30);
        houseNumberField = new JTextField();
        houseNumberField.setBounds(260, 250, 110, 30);
        countryField = new JTextField();
        countryField.setBounds(400, 250, 110, 30);
        stateField = new JTextField();
        stateField.setBounds(120, 310, 110, 30);
        cityField = new JTextField();
        cityField.setBounds(260, 310, 110, 30);
        zipField = new JTextField();
        zipField.setBounds(400, 310, 110, 30);

        // botoes submit e retorno
        submitBtn = new JButton("Submit");
        submitBtn.setBounds(180, 360, 110, 30);

        returnBtn = new JButton("Return");
        returnBtn.setBounds(300, 360, 110, 30);

        add(mainTxt);
        add(nameLbl);
        add(emailLbl);
        add(passwordLbl);
        add(ageLbl);
        add(salaryLbl);
        add(cpfLbl);

        add(addressTxt);
        add(streetLbl);
        add(houseNumberLbl);
        add(countryLbl);
        add(stateLbl);
        add(cityLbl);
        add(zipLbl);

        add(nameField);
        add(emailField);
        add(passwordField);
        add(ageField);
        add(cpfField);
        add(salaryField);

        add(streetField);
        add(houseNumberField);
        add(countryField);
        add(stateField);
        add(cityField);
        add(zipField);

        // seta funcoes dos botoes

        submitBtn.addActionListener(e -> {

        });

        returnBtn.addActionListener(e -> {
            dispose();
            new AdminPage();
        });

        add(submitBtn);
        add(returnBtn);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
