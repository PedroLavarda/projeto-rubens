package att1.dao;

import att1.entity.Client;

import java.sql.SQLException;

public interface ClientDAO extends DAO<Client>{
    Client getClientByCpf(String cpfS) throws SQLException;
}
