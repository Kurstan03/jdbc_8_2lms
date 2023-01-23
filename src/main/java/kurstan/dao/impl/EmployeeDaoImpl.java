package kurstan.dao.impl;

import kurstan.config.DatabaseConnection;
import kurstan.dao.EmployeeDao;
import kurstan.model.Employee;
import kurstan.model.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kurstan
 * @created at 22.01.2023 16:04
 */
public class EmployeeDaoImpl implements EmployeeDao {
    private Connection connection;

    public EmployeeDaoImpl() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public void createEmployee() {
        String query = """
                CREATE TABLE if NOT EXISTS employees(
                id SERIAL PRIMARY KEY,
                first_name VARCHAR(50) NOT NULL,
                last_name VARCHAR(50) ,
                age INTEGER,
                email VARCHAR UNIQUE,
                job_id INT REFERENCES jobs(id)
                );
                """;
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void addEmployee(Employee employee) {
        String query = """
                INSERT INTO employees(first_name, last_name, age, email, job_id)
                VALUES(?,?,?,?,?);
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setInt(5, employee.getJobId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropTable() {
        String query = "DROP TABLE if EXISTS employees;";
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cleanTable() {
        String query = "TRUNCATE TABLE employees";
       try (Statement statement = connection.createStatement()){
           statement.executeUpdate(query);
       } catch (SQLException e){
           throw new RuntimeException(e);
       }
    }

    @Override
    public void updateEmployee(Long id, Employee employee) {
        String query = "UPDATE employees " +
                "SET first_name = ?, last_name = ?, age = ?, email = ?, job_id = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setInt(5, employee.getJobId());
            preparedStatement.setLong(6, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employees";
        try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)){
            while (resultSet.next()) {
                employees.add(getMyEmployee(resultSet));
            }
            return employees;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    private Employee getMyEmployee(ResultSet resultSet) {
        Employee employee = new Employee();
        try {
            employee.setId(resultSet.getLong(1));
            employee.setFirstName(resultSet.getString(2));
            employee.setLastName(resultSet.getString(3));
            employee.setAge(resultSet.getInt(4));
            employee.setEmail(resultSet.getString(5));
            employee.setJobId(resultSet.getInt(6));

            return employee;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee findByEmail(String email) {
        String query = "SELECT * FROM employees WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            Employee employee = new Employee();
            while (resultSet.next()) {
                employee.setId(resultSet.getLong(1));
                employee.setFirstName(resultSet.getString(2));
                employee.setLastName(resultSet.getString(3));
                employee.setAge(resultSet.getInt(4));
                employee.setEmail(resultSet.getString(5));
                employee.setJobId(resultSet.getInt(6));
            }
            return employee;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        String query = "SELECT * FROM employees " +
                "JOIN jobs j ON employees.job_id = j.id " +
                "WHERE employees.id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1,employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            Map<Employee, Job> employeeJobMap = new HashMap<>();

            Employee employee = new Employee();
            Job job = new Job();
            while (resultSet.next()) {
                employee.setId(resultSet.getLong(1));
                employee.setFirstName(resultSet.getString(2));
                employee.setLastName(resultSet.getString(3));
                employee.setAge(resultSet.getInt(4));
                employee.setEmail(resultSet.getString(5));
                employee.setJobId(resultSet.getInt(6));

                job.setId(resultSet.getLong(7));
                job.setPosition(resultSet.getString(8));
                job.setProfession(resultSet.getString(9));
                job.setDescription(resultSet.getString(10));
                job.setExperience(resultSet.getInt(11));
            }

            employeeJobMap.put(employee,job);

            return employeeJobMap;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Employee> getEmployeeByPosition(String position) {
        String query = """
                SELECT * FROM employees
                JOIN jobs j ON employees.job_id = j.id
                WHERE j.position = ?""";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, position.trim());
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Employee> employees = new ArrayList<>();
            while (resultSet.next()) {
                employees.add(getMyEmployee(resultSet));
            }
            return employees;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}