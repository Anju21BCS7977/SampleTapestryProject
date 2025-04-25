package com.example.pages;//package com.example.pages;
//
//import com.example.entity.Employee;
//import com.example.services.EmployeeService;
//import jakarta.inject.Inject;
//import org.apache.tapestry5.annotations.Property;
//
//public class ViewEmployee {
//
//    @Inject
//    private EmployeeService employeeService;
//
//    @Property
//    private Employee employee;
//
//    // Load employee details based on the ID passed from the list page
//    void onActivate(Long id) {
//        System.out.println("Activated with ID: " + id);
//        if (id != null) {
//            employee = employeeService.getEmployeeById(id.intValue());
//        }
//    }
//
//}

//import com.example.entity.Employee;
//import com.example.services.EmployeeService;
//import jakarta.inject.Inject;
//import org.apache.tapestry5.annotations.Property;
//import org.hibernate.SessionFactory;
//import org.hibernate.Session;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class ViewEmployee {
//
//    @Inject
//    private EmployeeService employeeService;
//
//    @Property
//    private Employee employee;
//
//    @Autowired
//    private SessionFactory sessionFactory;  // Inject sessionFactory
//
//    // Load employee details based on the ID passed from the list page
//    void onActivate(int id) {
//        System.out.println("Received ID: " + id);  // Log received ID
//
//        if (id <= 0) {
//            System.out.println("Invalid ID: " + id);  // Log invalid ID
//            return;
//        }
//
//        // Use the service to fetch the employee by ID
//        employee = employeeService.getEmployeeById(id);
//        if (employee == null) {
//            System.out.println("Employee not found for ID: " + id);  // Log if not found
//        } else {
//            System.out.println("Found employee: " + employee);  // Log the found employee
//        }
//    }
//
//}

import com.example.entity.Employee;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.services.EmployeeService;
import com.example.services.RoleService;
import com.example.services.UserService;
import jakarta.inject.Inject;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.services.Request;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

public class ViewEmployee {

    private final Logger logger = LoggerFactory.getLogger(ViewEmployee.class);

    @Inject
    private EmployeeService employeeService;
    @Inject
    private RoleService roleService;
    @Inject
    private UserService userService;
    // Injecting the request service to handle URL params or headers

    @Property
    private Employee employee;
    @Property
    private User user;
    @Property
    private int employeeId;
    @Inject
    private SessionFactory sessionFactory;
    @ActivationRequestParameter("u")
            private String loggedInUsername;
    @Property
            private User loggedInUser;

    // onActivate method to fetch employee and associated user by employee ID
    void onActivate(int id) {
        if(loggedInUsername!=null) {
            this.loggedInUser = userService.findByUsername(loggedInUsername);
        }
        this.employeeId = id;
        employee = employeeService.getEmployeeById(id);

        logger.info("inside onActivate(): employee = {}", employee.getName());
        if (employee != null && employee.getUser() != null) {
            user = employee.getUser(); // Fetch the associated user
        } else {
            user = null; // No user exists for this employee
        }

        // Example use of Request if you need to fetch query parameters or headers

    }
    public boolean isAdmin(){
        if(loggedInUser==null){
            return false;
        }
        Role role=userService.getRole(loggedInUser);
        return role.getName().equals("ADMIN");
    }

    // For URL activation (to pass the employeeId to the page URL)
    Object onPassivate() {
        return employeeId;
    }

    public Role getUserRole() {
        return userService.getRole(user);
    }

    // Event handler for user creation
    Object onCreateUser() {

        if (employee != null && employee.getUser() == null) {  // Create user only if no user exists for this employee
            String username = employee.getName().toLowerCase().replaceAll("\\s", "");
            String password = username + "@" + employee.getAge();

            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password);

            // Assign role
            Role role = roleService.findByName("CLIENT"); // Fetch the CLIENT role
            newUser.setRole(role);

            // Save the user and associate it with the employee
            userService.saveUser(newUser);
            employee.setUser(newUser); // Associate user with employee

            // Update employee and persist the relationship
            employeeService.updateEmployee(employee);

            user = newUser; // Reflect the changes in the view
        }

        return null; // Stay on the same page
    }

}
