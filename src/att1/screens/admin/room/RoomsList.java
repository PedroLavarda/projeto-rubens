package att1.screens.admin.room;

import att1.dao.DAO;
import att1.dao.implementation.ClientDAOImpl;
import att1.dao.implementation.RoomDAOImpl;
import att1.entity.Client;
import att1.entity.Room;
import att1.screens.admin.client.ManageClients;
import att1.screens.admin.client.RegisterClient;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class RoomsList extends JFrame {
    private JList<Room> list;
    private JButton deleteRoomBtn, returnBtn;

    protected final DAO<Room> roomDAO = new RoomDAOImpl();

    public RoomsList () {
        initComponents();
    }

    private void initComponents() {
        setTitle("Rooms List");
        setSize(new Dimension(530, 600));
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);
        setResizable(false);

        GridBagConstraints c = new GridBagConstraints();

        DefaultListModel<Room> listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        try {
            List<Room> rooms = roomDAO.getAll();

            for (Room room : rooms) {
                listModel.addElement(room);
            }

            deleteRoomBtn = new JButton("Delete Room");
            deleteRoomBtn.addActionListener(e -> {
                int selectedIndex = list.getSelectedIndex();

                if(selectedIndex >= 0) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this room?",
                            "Warning", JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        Room room = listModel.get(selectedIndex);
                        try {
                            if(room.isReserved()) {
                                JOptionPane.showMessageDialog(RoomsList.this, "Cannot delete room that is currently reserved.");
                            } else {
                                roomDAO.delete(room.getId());
                                listModel.remove(selectedIndex);
                                JOptionPane.showMessageDialog(null, "Room deleted successfully", "Warning", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Select a room first!", "Warning", JOptionPane.INFORMATION_MESSAGE);
                }
            });

            returnBtn = new JButton("Return");
            returnBtn.addActionListener(e -> {
                dispose();
                new ManageRooms();
            });

            add(list, c);
            c.gridy = 1;
            c.insets = new Insets(60, 10, 10, 10);
            add(deleteRoomBtn, c);
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
