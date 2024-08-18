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
    private JButton deleteEmployeeBtn;

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
                if(employee.isActive()) {
                    listModel.addElement(employee);
                }
            }

            deleteEmployeeBtn = new JButton("Delete Employee");
            deleteEmployeeBtn.addActionListener(e -> {
                int selectedIndex = list.getSelectedIndex();

                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this employee?",
                        "Warning", JOptionPane.YES_NO_OPTION);

                if (selectedIndex >= 0 && confirm == JOptionPane.YES_OPTION) {
                    Employee employee = listModel.get(selectedIndex);
                    employee.setActive(false);
                    try {
                        employeeDAO.update(employee);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    listModel.remove(selectedIndex);
                    JOptionPane.showMessageDialog(null, "Employee deleted successfully", "Warning", JOptionPane.INFORMATION_MESSAGE);
                }
            });

            add(list, c);
            c.gridy = 1;
            c.insets = new Insets(60, 10, 10, 10);
            add(deleteEmployeeBtn, c);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
