package att1.DAO.implementation;

import att1.DAO.EmployeeDAO;
import att1.DB.DB;
import att1.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public Employee get(int id) throws SQLException {
        Connection conn = DB.getConnection();
        Employee employee = null;

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM EMPLOYEES WHERE id = ?");

        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            int eid = rs.getInt("id");
            String fullname = rs.getString("fullName");
            String email = rs.getString("email");
            String password = rs.getString("password");
            Date birthday = rs.getDate("birthDate");
            String cpf = rs.getString("cpf");
            double salary = rs.getDouble("salary");
            int workHours = rs.getInt("workHours");
            Date hiringDate = rs.getDate("hiringDate");
            boolean isActive = rs.getBoolean("isActive");

            employee = new Employee(eid, fullname, email, password, birthday, cpf, salary, workHours, hiringDate, isActive);
        }

        return employee;
    }

    @Override
    public List<Employee> getAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        Connection conn = DB.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM EMPLOYEES");
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            int eid = rs.getInt("id");
            String fullname = rs.getString("name");
            String email = rs.getString("email");
            String password = rs.getString("password");
            Date birthday = new Date();
            String cpf = rs.getString("cpf");
            double salary = rs.getDouble("salary");
            int workHours = rs.getInt("workHours");
            Date hiringDate = rs.getDate("hiringDate");
            boolean isActive = rs.getBoolean("isActive");

            employees.add(new Employee(eid, fullname, email, password, birthday, cpf, salary, workHours, hiringDate, isActive));
        }

        return employees;
    }

    @Override
    public int insert(Employee employee) throws SQLException {
        return 0;
    }

    @Override
    public int save(Employee employee) throws SQLException {
        return 0;
    }

    @Override
    public int update(Employee employee) throws SQLException {
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        return 0;
    }
}
