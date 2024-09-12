package att1.dao;

import att1.entity.Employee;

import java.sql.SQLException;

public interface EmployeeDAO extends DAO<Employee>{
    boolean login(String email, String password) throws SQLException;
}
