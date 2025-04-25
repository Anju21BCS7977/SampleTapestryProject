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
}
