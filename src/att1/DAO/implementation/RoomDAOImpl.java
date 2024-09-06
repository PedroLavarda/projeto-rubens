package att1.DAO.implementation;

import att1.DAO.DAO;
import att1.entity.Room;

import java.sql.SQLException;
import java.util.List;

public class RoomDAOImpl implements DAO<Room> {

    @Override
    public Room get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Room> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public int insert(Room room) throws SQLException {
        return 0;
    }

    @Override
    public int update(Room room) throws SQLException {
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        return 0;
    }
}
