package att1.dao;

import att1.entity.Client;

import java.sql.SQLException;
import java.util.List;

public interface ReserveClientDAO {
    List<Client> findAllClientsInReserve (int idReserve) throws SQLException;
    void insertClientInReserve (int idClient, int idReserve) throws SQLException;
    void updateClientInReserve (int idClient, int idReserve) throws SQLException;
    void deleteClientInReserve (int idClient, int idReserve) throws SQLException;
}
