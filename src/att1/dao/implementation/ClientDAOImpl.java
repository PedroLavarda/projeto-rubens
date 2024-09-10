package att1.dao.implementation;

import att1.dao.DAO;
import att1.db.DB;
import att1.entity.Address;
import att1.entity.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientDAOImpl implements DAO<Client> {
    @Override
    public Client get(int id) throws SQLException {
        Connection conn = DB.getConnection();
        Client client = null;

        PreparedStatement stmt = conn.prepareStatement("SELECT c.*, a.id as idaddress, a.street, a.house_number, a.country, a.state, a.city, a.zip_code FROM CLIENTS c " +
                "INNER JOIN ADDRESS a ON a.id = c.id_address WHERE id = ?");

        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            int eid = rs.getInt("id");
            String fullname = rs.getString("name");
            String email = rs.getString("email");
            String password = rs.getString("password");
            int age = rs.getInt("age");
            String cpf = rs.getString("cpf");
            Address address = new Address(rs.getInt("idaddress"), rs.getString("street"), rs.getInt("house_number"),
                    rs.getString("country"), rs.getString("state"), rs.getString("city"), rs.getInt("zip_code"));
            boolean isReserving = rs.getBoolean("isReserving");
            boolean isBanned = rs.getBoolean("isBanned");
            Date lastReservationDate = rs.getDate("lastReservationDate");
            String employeeNotes = rs.getString("employeeNotes");
            client = new Client(eid, fullname, email, password, age, cpf, address, isReserving, isBanned, lastReservationDate, employeeNotes);
        }

        return client;
    }

    @Override
    public List<Client> getAll() throws SQLException {
        List<Client> clients = new ArrayList<>();
        Connection conn = DB.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT c.*, a.id as idaddress, a.street, a.house_number, a.country, a.state, a.city, a.zip_code FROM CLIENTS c INNER JOIN ADDRESS a ON a.id = c.id_address");
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            int eid = rs.getInt("id");
            String fullname = rs.getString("name");
            String email = rs.getString("email");
            String password = rs.getString("password");
            int age = rs.getInt("age");
            String cpf = rs.getString("cpf");
            Address address = new Address(rs.getInt("idaddress"), rs.getString("street"), rs.getInt("house_number"),
                    rs.getString("country"), rs.getString("state"), rs.getString("city"), rs.getInt("zip_code"));
            boolean isReserving = rs.getBoolean("isReserving");
            boolean isBanned = rs.getBoolean("isBanned");
            Date lastReservationDate = rs.getDate("lastReservationDate");
            String employeeNotes = rs.getString("employeeNotes");

            clients.add(new Client(eid, fullname, email, password, age, cpf, address, isReserving, isBanned, lastReservationDate, employeeNotes));
        }

        return clients;
    }

    @Override
    public int insert(Client client) throws SQLException {
        Connection conn = DB.getConnection();

        PreparedStatement stmt =
                conn.prepareStatement("INSERT INTO CLIENTS (name, email, password, age, cpf, isReserving, isBanned, lastReservationDate, employeeNotes, id_address)" +
                        " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        stmt.setString(1, client.getFullName());
        stmt.setString(2, client.getEmail());
        stmt.setString(3, client.getPassword());
        stmt.setInt(4, client.getAge());
        stmt.setString(5, client.getCpf());
        stmt.setBoolean(6, client.isReserving());
        stmt.setBoolean(7, client.isBanned());
        stmt.setDate(8, client.getLastReservationDate());
        stmt.setString(9, client.getEmployeeNotes());
        stmt.setInt(10, client.getAddress().getId());

        stmt.execute();
        return 0;
    }

    @Override
    public int update(Client client) throws SQLException {
        Connection conn = DB.getConnection();

        PreparedStatement stmt = conn.prepareStatement("UPDATE CLIENTS SET name = ?, email = ?, age = ?, cpf = ?, isReserving = ?, isBanned = ?, lastReservationDate = ?, employeeNotes = ?, id_address = ? WHERE id = ?");
        stmt.setString(1, client.getFullName());
        stmt.setString(2, client.getEmail());
        stmt.setInt(3, client.getAge());
        stmt.setString(4, client.getCpf());
        stmt.setBoolean(5, client.isReserving());
        stmt.setBoolean(6, client.isBanned());
        stmt.setDate(7, client.getLastReservationDate());
        stmt.setString(8, client.getEmployeeNotes());
        stmt.setInt(9, client.getAddress().getId());
        stmt.setInt(10, client.getId());

        stmt.executeUpdate();
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        return 0;
    }
}
