package com.example.components;

import com.example.entity.Employee;
import com.example.services.EmployeeService;
import com.example.services.EmployeeServiceImpl;
import jakarta.inject.Inject;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ComponentResources;

@SupportsInformalParameters
public class PromoteLinkComponent {

    @Parameter(required = true)
    @Property
    private Employee employee;

    @Inject
    private ComponentResources resources;

    @Inject
    private EmployeeService employeeService;

    void onPromote(){
        employeeService.promoteEmployee(employee);
    }
}


