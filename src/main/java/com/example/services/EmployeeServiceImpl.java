package com.example.services;

import com.example.entity.Employee;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<Integer, Employee> employeeMap = new HashMap<>();
    private int idCounter = 1;

    @PostConstruct
    public void init() {
        addSampleEmployee("John Doe", 30, "Delhi");
        addSampleEmployee("Jane Smith", 28, "Mumbai");
        addSampleEmployee("Amit Sharma", 35, "Bangalore");
        addSampleEmployee("Priya Singh", 27, "Chennai");
        addSampleEmployee("Ravi Kumar", 40, "Kolkata");
        addSampleEmployee("Anita Roy", 32, "Hyderabad");
        addSampleEmployee("Karan Patel", 29, "Pune");
        addSampleEmployee("Meena Verma", 26, "Ahmedabad");
        addSampleEmployee("Vikram Joshi", 31, "Jaipur");
        addSampleEmployee("Neha Kapoor", 33, "Lucknow");
    }

    private void addSampleEmployee(String name, int age, String address) {
        Employee e = new Employee();
        e.setId(idCounter++);
        e.setName(name);
        e.setAge(age);
        e.setAddress(address);
        employeeMap.put(e.getId(), e);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeMap.values());
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeMap.get(id);
    }

    @Override
    public void saveEmployee(Employee employee) {
        if (employee.getId() == 0) {
            employee.setId(idCounter++);
        }
        employeeMap.put(employee.getId(), employee);
    }
}
