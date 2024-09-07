package att1.dao.implementation;

import att1.dao.DAO;
import att1.entity.Address;

import java.sql.SQLException;
import java.util.List;

public class AddressDAOImpl implements DAO<Address> {
    @Override
    public Address get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Address> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public int insert(Address address) throws SQLException {
        return 0;
    }

    @Override
    public int update(Address address) throws SQLException {
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        return 0;
    }
}
