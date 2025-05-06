package com.example.services;
import com.example.entity.Employee;
import java.util.List;
public interface EmployeeService {
    void saveEmployee(Employee employee);
    Employee getEmployeeById(int id);
    void updateEmployee(Employee employee);
    void deleteEmployee(int id);
    List<Employee> getAllEmployees();
    void promoteEmployee(Employee employee);
    List<Employee> searchEmployeesByName(String keyword);
    List<String> findEmployeeNamesByPrefix(String prefix);



}
