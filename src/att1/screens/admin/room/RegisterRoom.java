package att1.screens.admin.room;

import att1.dao.DAO;
import att1.dao.implementation.RoomDAOImpl;
import att1.entity.Room;
import att1.enums.RoomType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Objects;

public class RegisterRoom extends JFrame {
    private JLabel mainTxt, roomNmbLbl, descriptionLbl, roomTypeLbl, dailyRateLbl, capacityLbl, numBedsLbl;
    private JTextField roomNmbField, descriptionField, dailyRateField, capacityField, numBedsField;
    private JComboBox<String> roomTypeField;

    private JButton submitBtn, returnBtn;

    protected final DAO<Room> roomDAO = new RoomDAOImpl();

    public RegisterRoom() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Register Room");
        setSize(new Dimension(600, 450));
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        // declara os elementos da pagina

        mainTxt = new JLabel("Room Employee Page");
        mainTxt.setFont(new Font("Tahoma", Font.BOLD, 15));
        mainTxt.setBounds(200, 1, 200, 60);

        // informações pessoais do room
        roomNmbLbl = new JLabel("Room Nmb:");
        roomNmbLbl.setBounds(120, 40, 100, 30);
        descriptionLbl = new JLabel("Description:");
        descriptionLbl.setBounds(260, 40, 100, 30);
        roomTypeLbl = new JLabel("Room Type:");
        roomTypeLbl.setBounds(400, 40, 100, 30);
        dailyRateLbl = new JLabel("Daily Rate:");
        dailyRateLbl.setBounds(120, 100, 100, 30);
        capacityLbl = new JLabel("Capacity:");
        capacityLbl.setBounds(260, 100, 100, 30);
        numBedsLbl = new JLabel("Number of Beds:");
        numBedsLbl.setBounds(400, 100, 100, 30);


        roomNmbField = new JTextField();
        roomNmbField.setBounds(120, 70, 110, 30);
        descriptionField = new JTextField();
        descriptionField.setBounds(260, 70, 110, 30);
        roomTypeField = new JComboBox<String>();
        roomTypeField.setBounds(400, 70, 110, 30);

        roomTypeField.addItem("SINGLE_ROOM");
        roomTypeField.addItem("DOUBLE_ROOM");
        roomTypeField.addItem("DELUXE_DOUBLE_ROOM");
        roomTypeField.addItem("JUNIOR_SUITE");
        roomTypeField.addItem("EXECUTIVE_SUITE");

        dailyRateField = new JTextField();
        dailyRateField.setBounds(120, 130, 110, 30);
        capacityField = new JTextField();
        capacityField.setBounds(260, 130, 110, 30);
        numBedsField = new JTextField();
        numBedsField.setBounds(400, 130, 110, 30);


        // botoes submit e retorno
        submitBtn = new JButton("Submit");
        submitBtn.setBounds(180, 360, 110, 30);

        returnBtn = new JButton("Return");
        returnBtn.setBounds(300, 360, 110, 30);

        add(mainTxt);
        add(roomNmbLbl);
        add(descriptionLbl);
        add(roomTypeLbl);
        add(dailyRateLbl);
        add(capacityLbl);
        add(numBedsLbl);

        add(roomNmbField);
        add(descriptionField);
        add(roomTypeField);
        add(dailyRateField);
        add(capacityField);
        add(numBedsField);

        // seta funcoes dos botoes

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // só insere o quarto no banco, roomNmb é unique
                    roomDAO.insert(new Room(0, Integer.parseInt(roomNmbField.getText()), descriptionField.getText(), 0.0, RoomType.valueOf(Objects.requireNonNull(roomTypeField.getSelectedItem()).toString()),
                            Double.parseDouble(dailyRateField.getText()), Integer.parseInt(capacityField.getText()), Integer.parseInt(numBedsField.getText()), false));
;
                    dispose();
                    new RegisterRoom();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        returnBtn.addActionListener(e -> {
            dispose();
            new ManageRooms();
        });

        add(submitBtn);
        add(returnBtn);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
