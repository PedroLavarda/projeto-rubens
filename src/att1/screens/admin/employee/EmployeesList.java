package att1.screens.admin.employee;

import att1.DAO.EmployeeDAO;
import att1.DAO.implementation.EmployeeDAOImpl;
import att1.entity.Employee;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class EmployeesList extends JFrame {
    private JList<Employee> list;

    protected final EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    public EmployeesList () {
        initComponents();
    }

    private void initComponents() {
        setTitle("Employees List");
        setSize(new Dimension(700, 600));
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);

        GridBagConstraints c = new GridBagConstraints();

        DefaultListModel<Employee> listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        try {
            List<Employee> employees = employeeDAO.getAll();

            for (Employee employee : employees) {
                listModel.addElement(employee);
            }

            add(list, c);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
