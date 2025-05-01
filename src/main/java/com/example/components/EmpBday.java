package com.example.components;

import com.example.entity.Employee;
import com.example.services.EmployeeService;
import jakarta.inject.Inject;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

import java.util.Date;

public class EmpBday {

    @Inject
    private EmployeeService employeeService;

    @Inject
    private ComponentResources resources;

    @Parameter(required = true)
    @Property
    private Employee employee;

    public boolean isBirthdayToday() {
        Date dob=employee.getDob();
        if (dob == null) return false;
        Date today = new Date();
        return dob.getDate() == today.getDate()
                && dob.getMonth() == today.getMonth();
    }
}
