package att1.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    // get one generic
    T get(int id) throws SQLException;
    // get all generic
    List<T> getAll() throws SQLException;
    // insert generic
    int insert(T t) throws SQLException;
    // update generic
    int update(T t) throws SQLException;
    // delete generic
    int delete(int id) throws SQLException;
}
