package att1.screens.admin.reserve;

import att1.dao.DAO;
import att1.dao.ReserveClientDAO;
import att1.dao.implementation.ReserveClientDAOImpl;
import att1.dao.implementation.ReservesDAOImpl;
import att1.entity.Client;
import att1.entity.Reserve;
import att1.screens.admin.room.ManageRooms;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ReservesList extends JFrame{
    private JList<Reserve> list;
    private JButton deleteReserveBtn, returnBtn;

    protected final DAO<Reserve> reservesDAO = new ReservesDAOImpl();
    protected final ReserveClientDAO reserveClientDAO = new ReserveClientDAOImpl();

    public ReservesList () {
        initComponents();
    }

    private void initComponents() {
        setTitle("Reserves List");
        setSize(new Dimension(530, 600));
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);
        setResizable(false);

        GridBagConstraints c = new GridBagConstraints();

        DefaultListModel<Reserve> listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        try {
            List<Reserve> reserves = reservesDAO.getAll();

            for (Reserve reserve : reserves) {
                listModel.addElement(reserve);
            }

            deleteReserveBtn = new JButton("Delete Reserve");
            deleteReserveBtn.addActionListener(e -> {
                int selectedIndex = list.getSelectedIndex();

                if(selectedIndex >= 0) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this reserve?",
                            "Warning", JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        Reserve reserve = listModel.get(selectedIndex);
                        try {
                            List<Client> clients = reserveClientDAO.findAllClientsInReserve(reserve.getId());

                            if(clients != null) {
                                for (Client client : clients) {
                                    reserveClientDAO.deleteClientInReserve(client.getId(), reserve.getId());
                                }
                            }

                            reservesDAO.delete(reserve.getId());
                            listModel.remove(selectedIndex);
                            JOptionPane.showMessageDialog(null, "Reserves deleted successfully", "Warning", JOptionPane.INFORMATION_MESSAGE);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Select a reserve first!", "Warning", JOptionPane.INFORMATION_MESSAGE);
                }
            });

            returnBtn = new JButton("Return");
            returnBtn.addActionListener(e -> {
                dispose();
                new ManageReserves();
            });

            add(list, c);
            c.gridy = 1;
            c.insets = new Insets(60, 10, 10, 10);
            add(deleteReserveBtn, c);
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
