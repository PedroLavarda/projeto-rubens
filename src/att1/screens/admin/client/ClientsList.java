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
        // inicia a pagina
        setTitle("Clients List");
        setSize(new Dimension(530, 600));
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);
        setResizable(false);

        GridBagConstraints c = new GridBagConstraints();

        // seta uma lista de clientes
        DefaultListModel<Client> listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        try {
            // pega todos os clientes do banco
            List<Client> clients = cLientDAO.getAll();


            // adiciona na listra criada apenas os que nao estao banidos;
            for (Client client : clients) {
                if(!client.isBanned()) {
                    listModel.addElement(client);
                }
            }

            deleteClientBtn = new JButton("Delete Client");
            deleteClientBtn.addActionListener(e -> {
                // pegfa o index do cliente que ele selecionou
                int selectedIndex = list.getSelectedIndex();

                if(selectedIndex >= 0) {
                    // se ele escolheu um entra aqui
                    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this client?",
                            "Warning", JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        // confirmou que quer deletrar ele acha o cliente na lista pelo selectedIndex
                        Client client = listModel.get(selectedIndex);
                        // seta ele como banido
                        client.setBanned(true);
                        try {
                            // update na tabela cliente desse ccliente em especifico, CRUD UPDATE AQUI
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
