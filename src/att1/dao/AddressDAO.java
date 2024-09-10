package att1.dao;

import att1.entity.Address;

import java.sql.SQLException;

public interface AddressDAO extends DAO<Address>{
    Address getAddress(Address address) throws SQLException;
}
