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

        // Abre Conexao com o banco
        Connection conn = DB.getConnection();
        Address result = null;

        // Prepara a query
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ADDRESS WHERE street = ? and house_number = ? and country = ? and zip_code = ?");

        // Seta os campos necessariso pro select
        stmt.setString(1, address.getStreet());
        stmt.setInt(2, address.getHouseNumber());
        stmt.setString(3, address.getCountry());
        stmt.setInt(4, address.getZipCode());

        // Executa a query
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            // Se existir resultado ele entra e mapeia o Address aqui
            int eid = rs.getInt("id");
            String street = rs.getString("street");
            int houseNmb = rs.getInt("house_number");
            String state = rs.getString("state");
            String city = rs.getString("city");
            String country = rs.getString("country");
            int zipcode = rs.getInt("zip_code");

            result = new Address(eid, street, houseNmb, state, city, country, zipcode);

            // Se tiver retorna o address;
            return result;
        }

        // Se nao tiver resultado retorna nulo
        return null;
    }

    @Override
    public Address get(int id) throws SQLException {
        // Abre Conexao com o banco
        Connection conn = DB.getConnection();

        // Prepara a query
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ADDRESS WHERE id = ?");

        // Seta os campos necessariso pro select
        stmt.setInt(1, id);
        Address result = null;

        // Executa a query
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            // Se existir resultado ele entra e mapeia o Address aqui
            int eid = rs.getInt("id");
            String street = rs.getString("street");
            int houseNmb = rs.getInt("house_number");
            String state = rs.getString("state");
            String city = rs.getString("city");
            String country = rs.getString("country");
            int zipcode = rs.getInt("zip_code");

            // Se tiver retorna o address;
            result = new Address(eid, street, houseNmb, state, city, country, zipcode);
            return result;
        }

        // Se nao tiver resultado retorna nulo
        return null;
    }

    @Override
    public List<Address> getAll() throws SQLException {
        // Abre conexao com o banco
        Connection conn = DB.getConnection();
        // Cria uma lista vazia pros resultados da query
        List<Address> addresses = new ArrayList<>();

        // Prepara a query
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ADDRESS ");

        // EXecuta a query
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            // Equanto tiver resultados no result set ele mapeia os address e adicioa na lista que foi criada antes
            int eid = rs.getInt("id");
            String street = rs.getString("street");
            int houseNmb = rs.getInt("house_number");
            String state = rs.getString("state");
            String city = rs.getString("city");
            String country = rs.getString("country");
            int zipcode = rs.getInt("zip_code");

            addresses.add(new Address(eid, street, houseNmb, state, city, country, zipcode));
        }

        // Retorna a lista, se nao tiver resultado ele retorna uma lista vazia
        return addresses;
    }

    @Override
    public int insert(Address address) throws SQLException {
       //Abre conexao
        Connection conn = DB.getConnection();

        // Prepara query
        PreparedStatement stmt =
                conn.prepareStatement("INSERT INTO ADDRESS (street, house_number, country, state, zip_code)" +
                        " VALUES (?, ?, ?, ?, ?)");

        // Seta os campos necessarios pra query
        stmt.setString(1, address.getStreet());
        stmt.setInt(2, address.getHouseNumber());
        stmt.setString(3, address.getCountry());
        stmt.setString(4, address.getState());
        stmt.setInt(5, address.getZipCode());

        // Exxecuta o insert
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
