package att1.dao.implementation;

import att1.dao.DAO;
import att1.dao.ReserveClientDAO;
import att1.db.DB;
import att1.entity.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReserveClientDAOImpl implements ReserveClientDAO {

    DAO<Client> clientDAO = new ClientDAOImpl();

    @Override
    public List<Client> findAllClientsInReserve(int idReserve) throws SQLException {
        // Abre Conexao com o banco
        Connection conn = DB.getConnection();
        List<Client> clients = new ArrayList<>();

        // Prepara a query
        PreparedStatement stmt = conn.prepareStatement("SELECT id_client FROM RESERVES_CLIENTS WHERE id_reserve = ?");

        // Seta os campos necessariso pro select
        stmt.setInt(1, idReserve);

        // Executa a query
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            // Equanto tiver resultados no result set ele mapeia os address e adicioa na lista que foi criada antes

            clients.add(clientDAO.get(rs.getInt("id_client")));
        }

        // Se nao tiver resultado retorna nulo
        return clients;
    }

    @Override
    public void insertClientInReserve(int idClient, int idReserve) throws SQLException {
        // Abre Conexao com o banco
        Connection conn = DB.getConnection();

        // Prepara a query
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO RESERVES_CLIENTS (id_client, id_reserve) VALUES (?, ?)");

        // Seta os campos necessariso pro select
        stmt.setInt(1, idClient);
        stmt.setInt(2, idReserve);

        // Executa a query
        stmt.execute();
    }

    @Override
    public void updateClientInReserve(int idClient, int idReserve) throws SQLException {
        Connection conn = DB.getConnection();

        // Prepara a query
        PreparedStatement stmt = conn.prepareStatement("UPDATE RESERVES_CLIENTS SET id_client = ? WHERE id_reserve = ?");

        // Seta os campos necessariso pro select
        stmt.setInt(1, idClient);
        stmt.setInt(2, idReserve);

        // Executa a query
        stmt.executeUpdate();
    }

    @Override
    public void deleteClientInReserve(int idClient, int idReserve) throws SQLException {
            // abre conexao com o banco
            Connection conn = DB.getConnection();
            // prepara a query
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM RESERVES_CLIENTS WHERE id_client = ? and id_reserve = ?");
            // seta os atributos pra query
            stmt.setInt(1, idClient);
            stmt.setInt(2, idReserve);
            // executa a query
            stmt.execute();
    }
}
