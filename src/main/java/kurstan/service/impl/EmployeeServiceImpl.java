package kurstan.service.impl;

import kurstan.dao.EmployeeDao;
import kurstan.dao.impl.EmployeeDaoImpl;
import kurstan.model.Employee;
import kurstan.model.Job;
import kurstan.service.EmployeeService;

import java.util.List;
import java.util.Map;

/**
 * @author kurstan
 * @created at 22.01.2023 16:14
 */
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDao employeeDao = new EmployeeDaoImpl();
    @Override
    public void createEmployee() {
        employeeDao.createEmployee();
        System.out.println("Table employees created");
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeDao.addEmployee(employee);
        System.out.println(employee.getFirstName() + " added");
    }

    @Override
    public void dropTable() {
        employeeDao.dropTable();
        System.out.println("Table employees dropped");
    }

    @Override
    public void cleanTable() {
        employeeDao.cleanTable();
        System.out.println("Table employees cleaned");
    }

    @Override
    public void updateEmployee(Long id, Employee employee) {
        employeeDao.updateEmployee(id, employee);
        System.out.println(employee.getFirstName() + " updated");
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeDao.findByEmail(email);
    }

    @Override
    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        return employeeDao.getEmployeeById(employeeId);
    }

    @Override
    public List<Employee> getEmployeeByPosition(String position) {
        return employeeDao.getEmployeeByPosition(position);
    }
}
