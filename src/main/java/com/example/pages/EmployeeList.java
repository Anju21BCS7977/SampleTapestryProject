//package com.example.pages;
//
//import com.example.entity.Employee;
//import com.example.services.EmployeeService;
//import jakarta.inject.Inject;
//import org.apache.tapestry5.annotations.Property;
//import java.util.List;
//
//public class EmployeeList {
//
//    @Inject
//    private EmployeeService employeeService;
//
//    @Property
//    private List<Employee> employees;
//
//    // Populate the list of employees on page load
//    void onActivate() {
//        employees = employeeService.getAllEmployees();
//    }
//
//    // Action for deleting an employee
//    public Object onActionFromDeleteEmployee(long id) {
//        employeeService.deleteEmployee((int) id);
//        return null;  // Refreshes the page after deletion
//    }
//
//    // Action for editing an employee
//    public Object onActionFromEditEmployee(long id) {
//        return EditEmployee.class;  // Redirect to EditEmployee page
//    }
//
//    // Action for viewing an employee
//    public Object onActionFromViewEmployee(long id) {
//        return ViewEmployee.class;  // Redirect to ViewEmployee page
//    }
//}
//package com.example.pages;
//
//import com.example.entity.Employee;
//import com.example.services.EmployeeService;
//import jakarta.inject.Inject;
//import org.apache.tapestry5.annotations.Property;
//import org.apache.tapestry5.http.Link;
//import org.apache.tapestry5.services.PageRenderLinkSource;
//
//import java.util.List;
//
//public class EmployeeList {
//
//    @Inject
//    private EmployeeService employeeService;
//
//
//    @Inject
//    private PageRenderLinkSource linkSource;
//
//    public List<Employee> getEmployees() {
//        return employeeService.getAllEmployees();
//    }
//
//    public Link onActionFromViewEmployee(int id) {
//        return linkSource.createPageRenderLinkWithContext(ViewEmployee.class, id);
//    }
//
//    public Link onActionFromEditEmployee(int id) {
//        return linkSource.createPageRenderLinkWithContext(EditEmployee.class, id);
//    }
//
//    @Property
//    private Employee employee; // ✅ This line is required for <t:loop>
//
//
//
//    public Object onActionFromDeleteEmployee(long id) {
//        employeeService.deleteEmployee((int) id);
//        return null;
//    }
//
//}
//
package com.example.pages;

import com.example.entity.Employee;
import com.example.services.EmployeeService;
import com.example.entity.User;
import jakarta.inject.Inject;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.http.Link;
import org.apache.tapestry5.services.PageRenderLinkSource;

import java.util.List;

public class EmployeeList {

    @Inject
    private EmployeeService employeeService;

    @Inject
    private PageRenderLinkSource linkSource;

    @Property
    private Employee employee;
    private boolean isAdmin;   // ✅ Add this line!

    @Property

    @ActivationRequestParameter("u")
    private String loggedInUsername; // ✅ store username from login

    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }

    public Link onActionFromViewEmployee(int id) {
        Link link = linkSource.createPageRenderLinkWithContext(ViewEmployee.class, id);
        if (loggedInUsername != null) {
            link.addParameter("u", loggedInUsername);
        }
        return link;
    }

    public Link onActionFromEditEmployee(int id) {
        Link link = linkSource.createPageRenderLinkWithContext(EditEmployee.class, id);
        if (loggedInUsername != null) {
            link.addParameter("u", loggedInUsername);
        }
        return link;
    }

    public Object onActionFromDeleteEmployee(long id) {
        employeeService.deleteEmployee((int) id);
        return null;
    }

    public boolean isAdmin() {
        if (loggedInUsername == null) {
            return false;
        }
        // You can add here checking logic later if you want
        return true;
    }
}
