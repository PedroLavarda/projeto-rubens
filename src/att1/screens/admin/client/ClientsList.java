package att1.screens.admin.client;

import att1.dao.DAO;
import att1.dao.implementation.ClientDAOImpl;
import att1.entity.Client;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ClientsList extends JFrame {
    private JList<Client> list;
    private JButton deleteClientBtn, returnBtn;

    protected final DAO<Client> cLientDAO = new ClientDAOImpl();

    public ClientsList () {
        initComponents();
    }

    private void initComponents() {
        setTitle("Clients List");
        setSize(new Dimension(530, 600));
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);
        setResizable(false);

        GridBagConstraints c = new GridBagConstraints();

        DefaultListModel<Client> listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        try {
            List<Client> clients = cLientDAO.getAll();

            for (Client client : clients) {
                if(!client.isBanned()) {
                    listModel.addElement(client);
                }
            }

            deleteClientBtn = new JButton("Delete Client");
            deleteClientBtn.addActionListener(e -> {
                int selectedIndex = list.getSelectedIndex();

                if(selectedIndex >= 0) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this client?",
                            "Warning", JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        Client client = listModel.get(selectedIndex);
                        client.setBanned(true);
                        try {
                            cLientDAO.update(client);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        listModel.remove(selectedIndex);
                        JOptionPane.showMessageDialog(null, "Client deleted successfully", "Warning", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Select a client first!", "Warning", JOptionPane.INFORMATION_MESSAGE);
                }
            });

            returnBtn = new JButton("Return");
            returnBtn.addActionListener(e -> {
                dispose();
                new ManageClients();
            });

            add(list, c);
            c.gridy = 1;
            c.insets = new Insets(60, 10, 10, 10);
            add(deleteClientBtn, c);
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
