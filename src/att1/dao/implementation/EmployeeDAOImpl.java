package att1.dao.implementation;

import att1.dao.DAO;

import att1.db.DB;
import att1.entity.Address;
import att1.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeDAOImpl implements DAO<Employee> {
    @Override
    public Employee get(int id) throws SQLException {
        // abre conexao
        Connection conn = DB.getConnection();
        Employee employee = null;

        // prepara a query
        PreparedStatement stmt = conn.prepareStatement("SELECT e.*, a.id as idaddress, a.street, a.house_number, a.country, a.state, a.city, a.zip_code FROM EMPLOYEES e " +
                "INNER JOIN ADDRESS a ON a.id = e.id_address WHERE e.id = ?");

        // seta os atributos da query
        stmt.setInt(1, id);

        // executa a query
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            // se tiver resultado ele mapeia o empregado e seta ele como a variavel employee
            int eid = rs.getInt("id");
            String fullname = rs.getString("name");
            String email = rs.getString("email");
            String password = rs.getString("password");
            int age = rs.getInt("age");
            String cpf = rs.getString("cpf");
            double salary = rs.getDouble("salary");
            int workHours = rs.getInt("workHours");
            Date hiringDate = rs.getDate("hiringDate");
            boolean isActive = rs.getBoolean("isActive");
            Address address = new Address(rs.getInt("idaddress"), rs.getString("street"), rs.getInt("house_number"),
            rs.getString("country"), rs.getString("state"), rs.getString("city"), rs.getInt("zip_code"));

            employee = new Employee(eid, fullname, email, password, age, cpf, salary, workHours, hiringDate, isActive, address);
        }

        // retorna o employee, ou null
        return employee;
    }

    @Override
    public List<Employee> getAll() throws SQLException {
        // abre conexao
        List<Employee> employees = new ArrayList<>();
        Connection conn = DB.getConnection();

        // prepara a query
        PreparedStatement stmt = conn.prepareStatement("SELECT e.*, a.id as idaddress, a.street, a.house_number, a.country, a.state, a.city, a.zip_code FROM EMPLOYEES e INNER JOIN ADDRESS a ON a.id = e.id_address");

        // executa a query
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            // se tiver resultado ele mapeia o empregado e adiciona na lista employees
            int eid = rs.getInt("id");
            String fullname = rs.getString("name");
            String email = rs.getString("email");
            String password = rs.getString("password");
            int age = rs.getInt("age");
            String cpf = rs.getString("cpf");
            double salary = rs.getDouble("salary");
            int workHours = rs.getInt("workHours");
            Date hiringDate = rs.getDate("hiringDate");
            boolean isActive = rs.getBoolean("isActive");
            Address address = new Address(rs.getInt("idaddress"), rs.getString("street"), rs.getInt("house_number"),
                    rs.getString("country"), rs.getString("state"), rs.getString("city"), rs.getInt("zip_code"));

            employees.add(new Employee(eid, fullname, email, password, age, cpf, salary, workHours, hiringDate, isActive, address));
        }

        // retorna a lista de employees
        return employees;
    }

    @Override
    public int insert(Employee employee) throws SQLException {
        // abre conexao
        Connection conn = DB.getConnection();

        // prepara a query
        PreparedStatement stmt =
                conn.prepareStatement("INSERT INTO EMPLOYEES (name, email, password, age, cpf, salary, workHours, hiringDate, isActive, id_address)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        // seta os atributos da query
        stmt.setString(1, employee.getFullName());
        stmt.setString(2, employee.getEmail());
        stmt.setString(3, employee.getPassword());
        stmt.setInt(4, employee.getAge());
        stmt.setString(5, employee.getCpf());
        stmt.setDouble(6, employee.getSalary());
        stmt.setInt(7, employee.getWorkHours());
        stmt.setDate(8, employee.getHiringDate());
        stmt.setBoolean(9, true);
        stmt.setInt(10, employee.getAddress().getId());

        // executa a query
        stmt.execute();
        return 0;
    }

    @Override
    public int update(Employee employee) throws SQLException {
        // abre conexao
        Connection conn = DB.getConnection();

        // prepara a query
        PreparedStatement stmt = conn.prepareStatement("UPDATE EMPLOYEES SET name = ?, email = ?, age = ?, cpf = ?, salary = ?, workHours = ?, isActive = ?, id_address = ? WHERE id = ?");

        // seta os atributos da query
        stmt.setString(1, employee.getFullName());
        stmt.setString(2, employee.getEmail());
        stmt.setInt(3, employee.getAge());
        stmt.setString(4, employee.getCpf());
        stmt.setDouble(5, employee.getSalary());
        stmt.setInt(6, employee.getWorkHours());
        stmt.setBoolean(7, employee.isActive());
        stmt.setInt(8, employee.getAddress().getId());
        stmt.setInt(9, employee.getId());

        // executa a query
        stmt.executeUpdate();
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        // abre conexao
        return 0;
    }
}
