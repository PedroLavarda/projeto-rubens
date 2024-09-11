package att1.dao.implementation;

import att1.dao.AddressDAO;
import att1.db.DB;
import att1.entity.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDAOImpl implements AddressDAO {
    @Override
    public Address getAddress(Address address) throws SQLException {
        Connection conn = DB.getConnection();
        Address result = null;

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ADDRESS WHERE street = ? and house_number = ? and country = ? and zip_code = ?");

        stmt.setString(1, address.getStreet());
        stmt.setInt(2, address.getHouseNumber());
        stmt.setString(3, address.getCountry());
        stmt.setInt(4, address.getZipCode());

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            int eid = rs.getInt("id");
            String street = rs.getString("street");
            int houseNmb = rs.getInt("house_number");
            String state = rs.getString("state");
            String city = rs.getString("city");
            String country = rs.getString("country");
            int zipcode = rs.getInt("zip_code");

            result = new Address(eid, street, houseNmb, state, city, country, zipcode);
            return result;
        }

        return null;
    }

    @Override
    public Address get(int id) throws SQLException {
        Connection conn = DB.getConnection();

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ADDRESS WHERE id = ?");

        stmt.setInt(1, id);
        Address result = null;

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            int eid = rs.getInt("id");
            String street = rs.getString("street");
            int houseNmb = rs.getInt("house_number");
            String state = rs.getString("state");
            String city = rs.getString("city");
            String country = rs.getString("country");
            int zipcode = rs.getInt("zip_code");

            result = new Address(eid, street, houseNmb, state, city, country, zipcode);
            return result;
        }

        return null;
    }

    @Override
    public List<Address> getAll() throws SQLException {
        Connection conn = DB.getConnection();
        List<Address> addresses = new ArrayList<>();

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ADDRESS ");

        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            int eid = rs.getInt("id");
            String street = rs.getString("street");
            int houseNmb = rs.getInt("house_number");
            String state = rs.getString("state");
            String city = rs.getString("city");
            String country = rs.getString("country");
            int zipcode = rs.getInt("zip_code");

            addresses.add(new Address(eid, street, houseNmb, state, city, country, zipcode));
        }

        return addresses;
    }

    @Override
    public int insert(Address address) throws SQLException {
        Connection conn = DB.getConnection();

        PreparedStatement stmt =
                conn.prepareStatement("INSERT INTO ADDRESS (street, house_number, country, state, zip_code)" +
                        " VALUES (?, ?, ?, ?, ?)");

        stmt.setString(1, address.getStreet());
        stmt.setInt(2, address.getHouseNumber());
        stmt.setString(3, address.getCountry());
        stmt.setString(4, address.getState());
        stmt.setInt(5, address.getZipCode());

        stmt.execute();

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
