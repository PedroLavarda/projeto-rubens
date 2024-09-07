package att1.dao.implementation;

import att1.dao.DAO;
import att1.entity.Client;

import java.sql.SQLException;
import java.util.List;

public class ClientDAOImpl implements DAO<Client> {
    @Override
    public Client get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Client> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public int insert(Client client) throws SQLException {
        return 0;
    }

    @Override
    public int update(Client client) throws SQLException {
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        return 0;
    }
}
