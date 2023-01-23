package kurstan.service;

import kurstan.model.Employee;
import kurstan.model.Job;

import java.util.List;
import java.util.Map;

/**
 * @author kurstan
 * @created at 22.01.2023 16:13
 */
public interface EmployeeService {
    void createEmployee();
    void addEmployee(Employee employee);
    void dropTable();
    void cleanTable();
    void updateEmployee(Long id,Employee employee);
    List<Employee> getAllEmployees();
    Employee findByEmail(String email);
    Map<Employee, Job> getEmployeeById(Long employeeId);
    List<Employee> getEmployeeByPosition(String position);
}
