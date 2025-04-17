package com.example.pages;

import com.example.entity.Employee;
import com.example.services.EmployeeService;
import jakarta.inject.Inject;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.Property;

public class EmployeeDetail {
    @Property
    private Employee employee;

    @Inject
    private EmployeeService employeeService;

    @ActivationRequestParameter
    @Property
    private int id;

    void onActivate(int id) {
        employee = employeeService.getEmployeeById(id);
    }
}
