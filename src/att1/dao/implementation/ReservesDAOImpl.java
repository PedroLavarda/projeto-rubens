package att1.dao.implementation;

import att1.dao.DAO;
import att1.dao.ReserveClientDAO;
import att1.db.DB;
import att1.entity.Client;
import att1.entity.Employee;
import att1.entity.Reserve;
import att1.entity.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservesDAOImpl implements DAO<Reserve> {
    DAO<Room> roomDAO = new RoomDAOImpl();
    DAO<Employee> employeeDAO = new EmployeeDAOImpl();
    ReserveClientDAO reserveClientDAO = new ReserveClientDAOImpl();

    @Override
    public Reserve get(int id) throws SQLException {
        // abre conexao com o banco
        Connection conn = DB.getConnection();
        Reserve reserve = null;

        // prepara a query
        PreparedStatement stmt = conn.prepareStatement("SELECT r.* FROM RESERVES r WHERE id = ?");

        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            int eid = rs.getInt("id");
            double price = rs.getDouble("price");
            int idRoom = rs.getInt("id_room");
            Date initialDate = rs.getDate("initial_date");
            Date leavingDate = rs.getDate("leaving_date");
            int idEmployee = rs.getInt("id_employee");

            Employee employee = employeeDAO.get(idEmployee);
            Room room = roomDAO.get(idRoom);

            List<Client> clients = reserveClientDAO.findAllClientsInReserve(eid);

            reserve = new Reserve(eid, price, room, initialDate, leavingDate, employee, clients);
        }

        return reserve;
    }

    @Override
    public List<Reserve> getAll() throws SQLException {
        // abre conexao com o banco e inicia a lista de resultados vazia
        List<Reserve> reserves = new ArrayList<>();
        Connection conn = DB.getConnection();
        // prepara a query
        PreparedStatement stmt = conn.prepareStatement("SELECT r.* FROM RESERVES r");

        // executa a query
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            // mapeia os resultados e adiciona na lista de rooms
            int eid = rs.getInt("id");
            double price = rs.getDouble("price");
            int idRoom = rs.getInt("id_room");
            Date initialDate = rs.getDate("initial_date");
            Date leavingDate = rs.getDate("leaving_date");
            int idEmployee = rs.getInt("id_employee");

            Employee employee = employeeDAO.get(idEmployee);
            Room room = roomDAO.get(idRoom);

            List<Client> clients = reserveClientDAO.findAllClientsInReserve(eid);

            reserves.add(new Reserve(eid, price, room, initialDate, leavingDate, employee, clients));
        }

        // retorna a lista de quartos
        return reserves;
    }

    @Override
    public int insert(Reserve reserve) throws SQLException {
        // abre conexao com o banco
        Connection conn = DB.getConnection();
        // prepara a query
        PreparedStatement stmt =
                conn.prepareStatement("INSERT INTO RESERVES " +
                        "(price, id_room, initial_date, leaving_date, id_employee)" +
                        " VALUES (?, ?, ?, ?, ?)");
        // seta os atributos pra query
        stmt.setDouble(1, reserve.getPrice());
        stmt.setInt(2, reserve.getRoom().getId());
        stmt.setDate(3, reserve.getInitialDate());
        stmt.setDate(4, reserve.getLeavingDate());
        stmt.setInt(5, reserve.getEmployee().getId());

        for(Client client : reserve.getClients()) {
            reserveClientDAO.insertClientInReserve(client.getId(), reserve.getId());
        }

        // executa a query
        stmt.execute();
        return 0;
    }

    @Override
    public int update(Reserve reserve) throws SQLException {
        // abre conexao com o banco
        Connection conn = DB.getConnection();
        // prepara a query
        PreparedStatement stmt = conn.prepareStatement("UPDATE RESERVES SET price = ?, id_room = ?, initial_date = ?, leaving_date = ?, id_employee = ? WHERE id = ?");
        // seta os atributos pra query
        stmt.setDouble(1, reserve.getPrice());
        stmt.setInt(2, reserve.getRoom().getId());
        stmt.setDate(3, reserve.getInitialDate());
        stmt.setDate(4, reserve.getLeavingDate());
        stmt.setInt(5, reserve.getEmployee().getId());

        for(Client client : reserve.getClients()) {
            reserveClientDAO.updateClientInReserve(client.getId(), reserve.getId());
        }
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        // abre conexao com o banco
        Connection conn = DB.getConnection();
        // prepara a query
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM RESERVES WHERE id = ?");
        // seta os atributos pra query
        stmt.setInt(1, id);
        // executa a query
        stmt.execute();
        return 0;
    }
}
