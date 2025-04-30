//package com.example.services;
//
//import com.example.dao.EmployeeDao;
//import com.example.entity.Employee;
//import jakarta.inject.Inject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//@Service
//public class EmployeeServiceImpl implements EmployeeService {
//
//
//    private final EmployeeDao employeeDao;
//@Autowired
//    public EmployeeServiceImpl(EmployeeDao employeeDao) {
//        this.employeeDao = employeeDao;
//    }
//
//    @Override
//    public void saveEmployee(Employee employee) {
//        employeeDao.save(employee);
//    }
//
//    @Override
//    public Employee getEmployeeById(int id) {
//        return employeeDao.findById(id);
//    }
//
//    @Override
//    public void updateEmployee(Employee employee) {
//        employeeDao.update(employee);
//    }
//
//    @Override
//    public void deleteEmployee(int id) {
//        employeeDao.delete(id);
//    }
//
//    @Override
//    public List<Employee> getAllEmployees() {
//        return employeeDao.findAll();
//    }
//}
package com.example.services;

import com.example.dao.EmployeeDao;
import com.example.entity.Employee;
import jakarta.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeDao employeeDao;
    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeDao.save(employee);
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeDao.findById(id);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeDao.update(employee);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeDao.delete(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.findAll();
    }
    @Override
    public void promoteEmployee(Employee employee) {
        // Check current designation and promote accordingly
        String currentDesignation = employee.getDesignation();

        if ("INTERN".equals(currentDesignation)) {
            employee.setDesignation("EMPLOYEE");
        } else if ("EMPLOYEE".equals(currentDesignation)) {
            employee.setDesignation("ADMIN");
        } else if ("ADMIN".equals(currentDesignation)) {
            employee.setDesignation("MANAGER");
        } else {
            // If already an Admin, do not promote further
            System.out.println("Employee is already at the highest designation.");
        }

        // Update the employee with the new designation
        updateEmployee(employee);
    }
}
