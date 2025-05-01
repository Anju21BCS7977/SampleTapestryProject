package com.example.pages;

import com.example.entity.Employee;
import com.example.entity.User;
import com.example.services.EmployeeService;
import jakarta.inject.Inject;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;

import java.util.Date;

public class EditEmployee {

    @Property
    @Persist
    private int employeeId;

    @Property
    private Employee employee;
    @Property
    private String loggedInUsername;

    @Property
    private User loggedInUser;

    @Inject
    private EmployeeService employeeService;

    @InjectComponent
    private Form editEmployeeForm;

    void onActivate(int id) {
        this.employeeId = id;
    }

    int onPassivate() {
        return employeeId;
    }

    void onPrepareForRender() {
        if (employee == null) {
            employee = employeeService.getEmployeeById(employeeId);
        }
    }

    void onPrepareForSubmit() {
        // Load the employee object from the database again before binding the form values
        employee = employeeService.getEmployeeById(employeeId);
    }

    void onValidateFromEditEmployeeForm() {
        if (employee.getName() == null || employee.getName().trim().isEmpty()) {
            editEmployeeForm.recordError("Name is required.");
        }
        if (employee.getAge() <= 0) {
            editEmployeeForm.recordError("Age must be a positive number.");
        }
        if (employee.getAddress() == null || employee.getAddress().trim().isEmpty()) {
            editEmployeeForm.recordError("Address is required.");
        }
        if (employee.getDesignation() == null || employee.getDesignation().trim().isEmpty()) {
            editEmployeeForm.recordError("Designation is required.");
        }
        if (employee.getDob() == null) {
            editEmployeeForm.recordError("Date of Birth is required.");
        }
        if (employee.getDob() != null && employee.getDob().compareTo(new Date())>0) {
            editEmployeeForm.recordError("Date of Birth cannot be in the future.");
        }
        if (employee.getGender() == null || employee.getGender().trim().isEmpty()) {
            editEmployeeForm.recordError("Gender is required.");
        }

    }

    Object onSuccess() {
        employeeService.updateEmployee(employee);
        return EmployeeList.class;
    }
}
