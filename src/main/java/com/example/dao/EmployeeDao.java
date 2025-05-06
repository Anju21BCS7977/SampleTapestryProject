package com.example.dao;

import com.example.entity.Employee;

import java.util.List;

public interface EmployeeDao {
    void save(Employee employee);
    Employee findById(int id);
    void update(Employee employee);
    void delete(int id);
    List<Employee> findAll();
    List<Employee> searchEmployeesByName(String keyword);
    List<String> findEmployeeNamesByPrefix(String prefix);


}
