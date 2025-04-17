//ppackage com.example.pages;
//
//import com.example.entity.Employee;
//import com.example.services.EmployeeService;
//import jakarta.inject.Inject;
//import org.apache.tapestry5.annotations.InjectComponent;
//import org.apache.tapestry5.annotations.Property;
//import org.apache.tapestry5.corelib.components.Form;
//
//public class AddEmployee {
//
//    @InjectComponent
//    private Form employeeForm;
//
//    @Property
//    private Employee employee = new Employee();
//
//    @Inject
//    private EmployeeService employeeService;
//
//    void onValidateFromEmployeeForm() {
//        if (employee.getName() == null || employee.getName().trim().isEmpty()) {
//            employeeForm.recordError("Name is required.");
//        }
//        if (employee.getAge() <= 0) {
//            employeeForm.recordError("Enter valid age.");
//        }
//        if (employee.getAddress() == null || employee.getAddress().trim().isEmpty()) {
//            employeeForm.recordError("Address is required.");
//        }
//    }
//
//    Object onSuccess() {
//        employeeService.saveEmployee(employee);
//        return EmployeeList.class;
//    }
//}
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
    private Employee employee = new Employee();

    @Inject
    private EmployeeService employeeService;

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

    Object onSuccess() {
        Employee newEmployee = new Employee();
        newEmployee.setName(employee.getName());
        newEmployee.setAge(employee.getAge());
        newEmployee.setAddress(employee.getAddress());

        employeeService.saveEmployee(newEmployee);
        return EmployeeList.class;
    }
}
