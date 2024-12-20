package att1.screens.admin.employee;

import att1.dao.DAO;
import att1.dao.implementation.EmployeeDAOImpl;
import att1.entity.Employee;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class EmployeesList extends JFrame {
    private JList<Employee> list;
    private JButton deleteEmployeeBtn, returnBtn;

    protected final DAO<Employee> employeeDAO = new EmployeeDAOImpl();

    public EmployeesList () {
        initComponents();
    }

    private void initComponents() {
        // declara settings basicas das paginas
        setTitle("Employees List");
        setSize(new Dimension(425, 600));
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);
        setResizable(false);

        GridBagConstraints c = new GridBagConstraints();

        // declarei um gridbag pra ficar melhor a lista

        //cria a lista de Employee
        DefaultListModel<Employee> listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        try {
            // pega todos os employees
            List<Employee> employees = employeeDAO.getAll();

            // coloca só os q estao ativos na lista pra mostrar
            for (Employee employee : employees) {
                if(employee.isActive()) {
                    listModel.addElement(employee);
                }
            }

            deleteEmployeeBtn = new JButton("Delete Employee");
            deleteEmployeeBtn.addActionListener(e -> {
                // pega o employee selecionado
                int selectedIndex = list.getSelectedIndex();

                // se ele selecionou 1
                if(selectedIndex >= 0) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this employee?",
                            "Warning", JOptionPane.YES_NO_OPTION);

                    // confirmou que quer deletar
                    if (confirm == JOptionPane.YES_OPTION) {
                        // pega o employee pelo index da lista e pelo selectedIndex que foi o q ele selecionou
                        Employee employee = listModel.get(selectedIndex);
                        employee.setActive(false);
                        try {
                            // da um update na tabela employees, fazendo com que ele nao esteja mais ativo (CRUD DE UPDATE)
                            employeeDAO.update(employee);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        listModel.remove(selectedIndex);
                        JOptionPane.showMessageDialog(null, "Employee deleted successfully", "Warning", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Select an employee first!", "Warning", JOptionPane.INFORMATION_MESSAGE);
                }
            });

            returnBtn = new JButton("Return");
            returnBtn.addActionListener(e -> {
               dispose();
               new ManageEmployees();
            });

            add(list, c);
            c.gridy = 1;
            c.insets = new Insets(60, 10, 10, 10);
            add(deleteEmployeeBtn, c);
            c.gridy = 2;
            c.insets = new Insets(10, 10, 10, 10);
            add(returnBtn, c);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
