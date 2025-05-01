package com.example.pages;
import com.example.entity.Employee;
import com.example.entity.User;
import com.example.services.EmployeeService;
import jakarta.inject.Inject;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;

import java.time.LocalDate;
import java.util.Date;

public class AddEmployee {

    @InjectComponent
    private Form employeeForm;

    @Property
    private Employee employee = new Employee();

    @Inject
    private EmployeeService employeeService;

    @SessionState
    private User loggedInUser;

    private boolean loggedInUserExists;

    @Property
    private String loggedInUsername;

    void setupRender() {
        if (loggedInUserExists) {
            loggedInUsername = loggedInUser.getUsername();
        } else {
            loggedInUsername = "Guest";
        }
    }

    void onValidateFromEmployeeForm() {
        if (employee.getName() == null || employee.getName().trim().isEmpty()) {
            employeeForm.recordError("Name is required.");
        }
        if (employee.getAge() <= 0) {
            employeeForm.recordError("Age cannot be zero or negative.");
        }
        if (employee.getAddress() == null || employee.getAddress().trim().isEmpty()) {
            employeeForm.recordError("Address is required.");
        }
        if (employee.getDesignation() == null || employee.getDesignation().trim().isEmpty()) {
            employeeForm.recordError("Designation is required.");
        }
        if (employee.getDob() == null) {
            employeeForm.recordError("Date of Birth is required.");
        }
        if (employee.getDob() != null && employee.getDob().compareTo(new Date())>0) {
            employeeForm.recordError("Date of Birth cannot be in the future.");
        }
        if (employee.getGender() == null || employee.getGender().trim().isEmpty()) {
            employeeForm.recordError("Gender is required.");
        }

    }

    Object onSuccess() {
        employeeService.saveEmployee(employee);
        return EmployeeList.class;
    }
}
