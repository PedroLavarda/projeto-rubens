package att1.dao;

import att1.entity.Reserve;

import java.sql.SQLException;

public interface ReservesDAO extends DAO<Reserve> {
    int returnReserveId(Reserve reserve) throws SQLException;
}
