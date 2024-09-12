package att1.screens.admin.reserve;

import att1.dao.DAO;
import att1.dao.implementation.ClientDAOImpl;
import att1.entity.Client;
import att1.screens.admin.client.ManageClients;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ReserveClientsList extends JFrame {
    private JList<Client> list;
    private JButton deleteClientBtn;

    public ReserveClientsList (List<Client> list) {
        initComponents(list);
    }

    private void initComponents(List<Client> cList) {
        setTitle("Clients List");
        setSize(new Dimension(530, 600));
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);
        setResizable(false);

        GridBagConstraints c = new GridBagConstraints();

        DefaultListModel<Client> listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
            for (Client client : cList) {
                if(!client.isBanned()) {
                    listModel.addElement(client);
                    System.out.println(client);
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
                        cList.remove(selectedIndex);
                        listModel.remove(selectedIndex);
                        JOptionPane.showMessageDialog(null, "Client deleted successfully", "Warning", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Select a client first!", "Warning", JOptionPane.INFORMATION_MESSAGE);
                }
            });

            add(list, c);
            c.gridy = 1;
            c.insets = new Insets(60, 10, 10, 10);
            add(deleteClientBtn, c);
            c.gridy = 2;
            c.insets = new Insets(10, 10, 10, 10);

            setVisible(true);
    }
}
