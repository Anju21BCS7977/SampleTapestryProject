package com.example.pages;

import com.example.entity.Employee;
import com.example.services.EmployeeService;
import jakarta.inject.Inject;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.Property;

import java.util.ArrayList;
import java.util.List;

public class SearchEmployee {

    @Inject
    private EmployeeService employeeService;  // Inject the service to fetch employees

    @Property
    @ActivationRequestParameter
    private String searchText;  // Search text entered by the user

   

    @Property
    private Employee employee;  // Used to display employee details in the loop

    // Method to handle search action


    public List<Employee> getSearchResults() {
        return employeeService.searchEmployeesByName(searchText);
    }
}
