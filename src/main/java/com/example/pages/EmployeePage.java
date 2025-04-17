package com.example.pages;

import com.example.entity.Employee;
import com.example.services.EmployeeService;
import jakarta.inject.Inject;
import org.apache.tapestry5.annotations.Property;

public class EmployeePage {

    @Property
    private Employee employee;

    @Inject
    private EmployeeService employeeService;

    void onActivate(int id) {
        employee = employeeService.getEmployeeById(id);
    }

    int onPassivate() {
        return employee != null ? employee.getId() : 0;
    }
}
