package att1.dao;

import att1.entity.Address;

import java.sql.SQLException;

public interface AddressDAO extends DAO<Address>{
    // query customizada pro endereco, achar id mandando endereço
    Address getAddress(Address address) throws SQLException;
}
