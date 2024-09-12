package att1.screens.admin.reserve;

import att1.dao.ClientDAO;
import att1.dao.DAO;
import att1.dao.ReserveClientDAO;
import att1.dao.ReservesDAO;
import att1.dao.implementation.*;
import att1.entity.Client;
import att1.entity.Employee;
import att1.entity.Reserve;
import att1.entity.Room;
import att1.screens.admin.AdminPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegisterReserve extends JFrame {
    private JLabel mainTxt, priceLbl, roomLbl, initialDateLbl, leavingDateLbl, employeeLbl, clientsLbl;
    private JTextField priceField, initialDateField, leavingDateField, clientsField;
    private JComboBox<Room> roomChoice;
    private JComboBox<Employee> employeeChoice;

    private JButton submitBtn, returnBtn, addClientBtn, verifyClientListBtn;

    protected final DAO<Employee> employeeDAO = new EmployeeDAOImpl();
    protected final ClientDAO clientDAO = new ClientDAOImpl();
    protected final DAO<Room> roomDAO = new RoomDAOImpl();
    protected final ReservesDAO reserveDAO = new ReservesDAOImpl();
    protected final ReserveClientDAO reserveClientDAO = new ReserveClientDAOImpl();

    public RegisterReserve() {
        // inicia a tela
        initComponents();
    }

    private void initComponents() {
        // seta titulo e outrs coisas da tela
        setTitle("Register Reserve");
        setSize(new Dimension(600, 450));
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        try {

            // inicio da declaracao das partes da tela

            mainTxt = new JLabel("Register Reserve Page");
            mainTxt.setFont(new Font("Tahoma", Font.BOLD, 15));
            mainTxt.setBounds(200, 1, 200, 60);

            // informações pessoais da reserve
            priceLbl = new JLabel("Total price:");
            priceLbl.setBounds(120, 40, 100, 30);
            roomLbl = new JLabel("Room:");
            roomLbl.setBounds(260, 40, 100, 30);
            initialDateLbl = new JLabel("Entering Date:");
            initialDateLbl.setBounds(400, 40, 100, 30);
            leavingDateLbl = new JLabel("Leaving Date:");
            leavingDateLbl.setBounds(120, 100, 100, 30);
            employeeLbl = new JLabel("Employee:");
            employeeLbl.setBounds(260, 100, 100, 30);
            clientsLbl = new JLabel("Add client(cpf):");
            clientsLbl.setBounds(400, 100, 100, 30);

            priceField = new JTextField();
            priceField.setBounds(120, 70, 110, 30);
            roomChoice = new JComboBox<>();
            roomChoice.setBounds(260, 70, 110, 30);

            List<Room> rooms = roomDAO.getAll();

            for (Room room : rooms) {
                if(!room.isReserved()) {
                    roomChoice.addItem(room);
                }
            }

            initialDateField = new JPasswordField();
            initialDateField.setBounds(400, 70, 110, 30);
            leavingDateField = new JTextField();
            leavingDateField.setBounds(120, 130, 110, 30);
            employeeChoice = new JComboBox<>();
            employeeChoice.setBounds(260, 130, 110, 30);

            List<Employee> employees = employeeDAO.getAll();

            for (Employee employee : employees) {
                if(employee.isActive()) {
                    employeeChoice.addItem(employee);
                }
            }

            clientsField = new JTextField();
            clientsField.setBounds(400, 130, 110, 30);

            addClientBtn = new JButton("Add client");
            addClientBtn.setBounds(400, 170, 110, 30);

            verifyClientListBtn = new JButton("View list");
            verifyClientListBtn.setBounds(400, 210, 110, 30);

            // botoes submit e retorno
            submitBtn = new JButton("Submit");
            submitBtn.setBounds(180, 360, 110, 30);

            returnBtn = new JButton("Return");
            returnBtn.setBounds(300, 360, 110, 30);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        add(mainTxt);
        add(priceLbl);
        add(roomLbl);
        add(initialDateLbl);
        add(leavingDateLbl);
        add(employeeLbl);
        add(clientsLbl);

        add(priceField);
        add(roomChoice);
        add(initialDateField);
        add(leavingDateField);
        add(employeeChoice);
        add(clientsField);
        add(addClientBtn);
        add(verifyClientListBtn);


        // seta funcoes dos botoes

        List<Client> clients = new ArrayList<>();

        addClientBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Client client = clientDAO.getClientByCpf(clientsField.getText());
                    boolean inList = false;

                    // veririca se o cliente existe ou se ele esta banido

                    if(client == null || client.isBanned()) {
                        JOptionPane.showMessageDialog(RegisterReserve.this, "Client is not registered.");
                        return;
                    }

                    // verifica se o cliente esta na lista


                    for(Client c1 : clients) {
                        if(client.getCpf().equals(c1.getCpf())) {
                            inList = true;
                            break;
                        }
                    }

                    if(inList) {
                        JOptionPane.showMessageDialog(RegisterReserve.this, "Client is already in this list of this reservation.");
                        return;
                    }

                    // adiciona o cliente ao array de clientes da reserve

                    clients.add(client);
                    JOptionPane.showMessageDialog(RegisterReserve.this, "Client added to the reservation.");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        verifyClientListBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReserveClientsList(clients);
            }
        });

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Room room = (Room) roomChoice.getSelectedItem();
                    Employee employee = (Employee) employeeChoice.getSelectedItem();
                    Reserve reserve = new Reserve(0, Double.parseDouble(priceField.getText()), room, Date.valueOf(LocalDate.now()),
                            Date.valueOf(LocalDate.now()), employee, clients);

                    // isnere a reserva criada no banco
                    reserveDAO.insert(reserve);

                    // pega o id da reserva
                    int idReserv = reserveDAO.returnReserveId(reserve);

                    // coloca os cliente na tabela de relação da reserva
                    for(Client cc : clients) {
                        reserveClientDAO.insertClientInReserve(cc.getId(), idReserv);
                    }

                    dispose();
                    new RegisterReserve();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        returnBtn.addActionListener(e -> {
            dispose();
            new AdminPage();
        });

        add(submitBtn);
        add(returnBtn);

        setVisible(true);
    }
}
