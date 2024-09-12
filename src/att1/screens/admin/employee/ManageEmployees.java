package att1.screens.admin.employee;

import att1.screens.admin.AdminPage;
import att1.screens.auth.LoginPage;

import javax.swing.*;
import java.awt.*;

public class ManageEmployees extends JFrame {
    private JPanel pane;
    private JButton employeesList, registerEmployee, returnBtn;
    private JLabel maintxt;

    public ManageEmployees() {
        initComponents();
    }

    private void initComponents() {
        // declara settigns basicas da pagina
        setTitle("Employees Management Page");
        setSize(new Dimension(500, 400));
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        // declara elementos da pagina + tamanho, texto etc
        maintxt = new JLabel("Employee Management Page");
        maintxt.setBounds(140, 0, 250, 50);
        maintxt.setFont(new Font("Tahoma", Font.BOLD, 15));

        // os botoes em todas as classes ManageEntity, discartam a pagina atual e abrem uma nova pagina

        employeesList = new JButton("Employees List");
        employeesList.setBounds(150, 70, 200, 70);
        employeesList.addActionListener(e -> {
            dispose();
            new EmployeesList();
        });


        registerEmployee = new JButton("Register Employee");
        registerEmployee.setBounds(150, 170, 200, 70);
        registerEmployee.addActionListener(e -> {
            dispose();
            new RegisterEmployee();
        });

        returnBtn = new JButton("Return");
        returnBtn.setBounds(150,300, 200, 50);
        returnBtn.addActionListener(e -> {
            dispose();
            new AdminPage();
        });

        add(maintxt);
        add(registerEmployee);
        add(employeesList);
        add(returnBtn);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
