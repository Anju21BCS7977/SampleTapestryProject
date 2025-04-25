package com.example.pages;

import com.example.entity.Employee;
import com.example.services.EmployeeService;
import jakarta.inject.Inject;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;

public class AddEmployee {

    @InjectComponent
    private Form employeeForm;

    @Property
    private Employee employee = new Employee(); // This is the employee object from the form

    @Inject
    private EmployeeService employeeService; // EmployeeService to save data

    // Form validation
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
    }

    // On success, save the employee and redirect to the Employee List
    Object onSuccess() {
        // You can directly pass the employee object to save in the database
        employeeService.saveEmployee(employee); // The save logic will now use Hibernate
        return EmployeeList.class; // Redirect to the Employee List page after saving
    }
}
