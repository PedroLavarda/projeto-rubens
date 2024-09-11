package att1.dao.implementation;

import att1.dao.DAO;
import att1.db.DB;
import att1.entity.Room;
import att1.enums.RoomType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements DAO<Room> {

    @Override
    public Room get(int id) throws SQLException {
        Connection conn = DB.getConnection();
        Room room = null;

        PreparedStatement stmt = conn.prepareStatement("SELECT r.* FROM ROOMS r WHERE id = ?");

        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            int eid = rs.getInt("id");
            int roomNumber = rs.getInt("roomNmb");
            String description = rs.getString("description");
            double rating = rs.getDouble("rating");
            String roomType = rs.getString("roomType");
            double dailyRate = rs.getDouble("dailyRate");
            int capacity = rs.getInt("capacity");
            int numBeds = rs.getInt("numBeds");
            boolean isReserved = rs.getBoolean("isReserved");
            room = new Room(eid, roomNumber, description, rating, RoomType.valueOf(roomType), dailyRate, capacity, numBeds, isReserved);
        }

        return room;
    }

    @Override
    public List<Room> getAll() throws SQLException {
        List<Room> rooms = new ArrayList<>();
        Connection conn = DB.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT r.* FROM ROOMS r");
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            int eid = rs.getInt("id");
            int roomNumber = rs.getInt("roomNmb");
            String description = rs.getString("description");
            double rating = rs.getDouble("rating");
            String roomType = rs.getString("roomType");
            double dailyRate = rs.getDouble("dailyRate");
            int capacity = rs.getInt("capacity");
            int numBeds = rs.getInt("numBeds");
            boolean isReserved = rs.getBoolean("isReserved");

            rooms.add(new Room(eid, roomNumber, description, rating, RoomType.valueOf(roomType), dailyRate, capacity, numBeds, isReserved));
        }

        return rooms;
    }

    @Override
    public int insert(Room room) throws SQLException {
        Connection conn = DB.getConnection();

        PreparedStatement stmt =
                conn.prepareStatement("INSERT INTO ROOMS " +
                        "(roomNmb, description, rating, roomType, dailyRate, capacity, numBeds, isReserved)" +
                        " VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

        stmt.setInt(1, room.getRoomNumber());
        stmt.setString(2, room.getDescription());
        stmt.setDouble(3, room.getRating());
        stmt.setString(4, room.getRoomType().toString());
        stmt.setDouble(5, room.getDailyRate());
        stmt.setInt(6, room.getCapacity());
        stmt.setInt(7, room.getNumBeds());
        stmt.setBoolean(8, room.isReserved());

        stmt.execute();
        return 0;
    }

    @Override
    public int update(Room room) throws SQLException {
        Connection conn = DB.getConnection();

        PreparedStatement stmt = conn.prepareStatement("UPDATE ROOMS SET roomNmb = ?, description = ?, rating = ?, roomType = ?, dailyRate = ?, capacity = ?, numBeds = ?, isReserved = ? WHERE id = ?");
        stmt.setInt(1, room.getRoomNumber());
        stmt.setString(2, room.getDescription());
        stmt.setDouble(3, room.getRating());
        stmt.setString(4, room.getRoomType().toString());
        stmt.setDouble(5, room.getDailyRate());
        stmt.setInt(6, room.getCapacity());
        stmt.setInt(7, room.getNumBeds());
        stmt.setBoolean(8, room.isReserved());
        stmt.setInt(9, room.getId());

        stmt.executeUpdate();
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        Connection conn = DB.getConnection();

        PreparedStatement stmt = conn.prepareStatement("DELETE FROM ROOMS WHERE id = ?");

        stmt.setInt(1, id);

        stmt.execute();
        return 0;
    }
}
