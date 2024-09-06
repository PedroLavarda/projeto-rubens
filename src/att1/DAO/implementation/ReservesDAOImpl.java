package att1.DAO.implementation;

import att1.DAO.DAO;
import att1.entity.Reserve;

import java.sql.SQLException;
import java.util.List;

public class ReservesDAOImpl implements DAO<Reserve> {
    @Override
    public Reserve get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Reserve> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public int insert(Reserve reserve) throws SQLException {
        return 0;
    }

    @Override
    public int update(Reserve reserve) throws SQLException {
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        return 0;
    }
}
