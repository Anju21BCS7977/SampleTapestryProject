package com.example.pages;

import com.example.entity.Employee;
import com.example.services.EmployeeService;
import jakarta.inject.Inject;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.http.Link;
import org.apache.tapestry5.internal.services.LinkSource;
import org.apache.tapestry5.services.PageRenderLinkSource;

import java.util.List;


public class EmployeeList {

    @Property
    private Employee employee; // used for Tapestry Grid row

    @Inject
    private EmployeeService employeeService;

    @Inject
    private PageRenderLinkSource linkSource;

    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }

    public Link onActionFromView(int id) {
        return linkSource.createPageRenderLinkWithContext("employeePage", id);
    }
}
