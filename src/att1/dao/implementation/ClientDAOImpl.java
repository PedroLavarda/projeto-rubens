package att1.dao.implementation;

import att1.dao.ClientDAO;
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

public class ClientDAOImpl implements ClientDAO {
    @Override
    public Client get(int id) throws SQLException {
        // Abre conexao
        Connection conn = DB.getConnection();

        // Inicia o resultado client como null
        Client client = null;

        // Prepara query
        PreparedStatement stmt = conn.prepareStatement("SELECT c.*, a.id as idaddress, a.street, a.house_number, a.country, a.state, a.city, a.zip_code FROM CLIENTS c " +
                "INNER JOIN ADDRESS a ON a.id = c.id_address WHERE c.id = ?");

        // Seta os campos necessarios para query
        stmt.setInt(1, id);

        // Executa a query
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            // Se tiver resultado ele mapeia o resultado aqui, e faz com que o client seja o novo cliente que a gente achou
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

        // retorna o client, se nao achou nada retorna null
        return client;
    }

    @Override
    public List<Client> getAll() throws SQLException {
        // abre conexao e inicia o array clients como vazio pra ser o arry de resultados
        List<Client> clients = new ArrayList<>();
        Connection conn = DB.getConnection();
        // prepara a query
        PreparedStatement stmt = conn.prepareStatement("SELECT c.*, a.id as idaddress, a.street, a.house_number, a.country, a.state, a.city, a.zip_code FROM CLIENTS c INNER JOIN ADDRESS a ON a.id = c.id_address");
        // executa a query
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            // enquanto tiver resultados ele vai mapear os clientes e adicionar na lista de clients
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

        // retorna a lista
        return clients;
    }

    @Override
    public int insert(Client client) throws SQLException {
        // abre conexao com o banco
        Connection conn = DB.getConnection();

        // prepara a query
        PreparedStatement stmt =
                conn.prepareStatement("INSERT INTO CLIENTS (name, email, password, age, cpf, isReserving, isBanned, lastReservationDate, employeeNotes, id_address)" +
                        " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        // seta os atributos pra query
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

        // executa o insert
        stmt.execute();
        return 0;
    }

    @Override
    public int update(Client client) throws SQLException {
        // abre conexao com o banco
        Connection conn = DB.getConnection();

        // prepara a query
        PreparedStatement stmt = conn.prepareStatement("UPDATE CLIENTS SET name = ?, email = ?, age = ?, cpf = ?, isReserving = ?, isBanned = ?, lastReservationDate = ?, employeeNotes = ?, id_address = ? WHERE id = ?");

        // seta os atributos pra query
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

        // executa a query
        stmt.executeUpdate();
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        // abre conexao com o banco
        Connection conn = DB.getConnection();

        // prepara a query
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM CLIENTS WHERE id = ?");

        // seta atribuutos da query
        stmt.setInt(1, id);

        // eexecuta a query
        stmt.execute();
        return 0;
    }

    @Override
    public Client getClientByCpf(String cpfS) {
        try {
            // Abre conexao
            Connection conn = DB.getConnection();

            // Inicia o resultado client como null
            Client client = null;

            // Prepara query
            PreparedStatement stmt = conn.prepareStatement("SELECT c.*, a.id as idaddress, a.street, a.house_number, a.country, a.state, a.city, a.zip_code FROM CLIENTS c " +
                    "INNER JOIN ADDRESS a ON a.id = c.id_address WHERE cpf = ?");

            // Seta os campos necessarios para query
            stmt.setString(1, cpfS);

            // Executa a query
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Se tiver resultado ele mapeia o resultado aqui, e faz com que o client seja o novo cliente que a gente achou
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

            // retorna o client, se nao achou nada retorna null
            return client;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
