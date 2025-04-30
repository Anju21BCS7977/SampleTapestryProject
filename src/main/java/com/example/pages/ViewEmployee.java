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

//import com.example.entity.Employee;
//import com.example.entity.Role;
//import com.example.entity.User;
//import com.example.services.EmployeeService;
//import com.example.services.RoleService;
//import com.example.services.UserService;
//import jakarta.inject.Inject;
//import org.apache.tapestry5.annotations.*;
//import org.apache.tapestry5.services.Request;
//import org.hibernate.SessionFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.transaction.annotation.Transactional;
//
//public class ViewEmployee {
//
//    private final Logger logger = LoggerFactory.getLogger(ViewEmployee.class);
//
//    @Inject
//    private EmployeeService employeeService;
//    @Inject
//    private RoleService roleService;
//    @Inject
//    private UserService userService;
//    // Injecting the request service to handle URL params or headers
//
//    @Property
//    private Employee employee;
//    @Property
//    private User user;
//    @Property
//    private int employeeId;
//    @Inject
//    private SessionFactory sessionFactory;
//    @ActivationRequestParameter("u")
//    @Property
//
//    private String loggedInUsername;
//    @Property
//            private User loggedInUser;
//
//    // onActivate method to fetch employee and associated user by employee ID
//    void onActivate(int id) {
//        if(loggedInUsername!=null) {
//            this.loggedInUser = userService.findByUsername(loggedInUsername);
//        }
//        this.employeeId = id;
//        employee = employeeService.getEmployeeById(id);
//
//        logger.info("inside onActivate(): employee = {}", employee.getName());
//        if (employee != null && employee.getUser() != null) {
//            user = employee.getUser(); // Fetch the associated user
//        } else {
//            user = null; // No user exists for this employee
//        }
//
//        // Example use of Request if you need to fetch query parameters or headers
//
//    }
//    public boolean isAdmin(){
//        if(loggedInUser==null){
//            return false;
//        }
//        Role role=userService.getRole(loggedInUser);
//        return role.getName().equals("ADMIN");
//    }
//
//    // For URL activation (to pass the employeeId to the page URL)
//    Object onPassivate() {
//        return employeeId;
//    }
//
//    public Role getUserRole() {
//        return userService.getRole(user);
//    }
//
//    // Event handler for user creation
//    Object onCreateUser() {
//
//        if (employee != null && employee.getUser() == null) {  // Create user only if no user exists for this employee
//            String username = employee.getName().toLowerCase().replaceAll("\\s", "");
//            String password = username + "@" + employee.getAge();
//
//            User newUser = new User();
//            newUser.setUsername(username);
//            newUser.setPassword(password);
//
//            // Assign role
//            Role role = roleService.findByName("CLIENT"); // Fetch the CLIENT role
//            newUser.setRole(role);
//
//            // Save the user and associate it with the employee
//            userService.saveUser(newUser);
//            employee.setUser(newUser); // Associate user with employee
//
//            // Update employee and persist the relationship
//            employeeService.updateEmployee(employee);
//
//            user = newUser; // Reflect the changes in the view
//        }
//
//        return null; // Stay on the same page
//    }
//     // Hide the popup
//
//
//}

//import com.example.entity.Employee;
//import com.example.entity.Role;
//import com.example.entity.User;
//import com.example.services.EmployeeService;
//import com.example.services.RoleService;
//import com.example.services.UserService;
//import jakarta.inject.Inject;
//import org.apache.tapestry5.annotations.*;
//
//import org.apache.tapestry5.corelib.components.Zone;
//import org.apache.tapestry5.services.Request;
//import org.apache.tapestry5.services.Response;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.event.EventListener;
//
//import java.io.IOException;
//
//public class ViewEmployee {
//
//    private final Logger logger = LoggerFactory.getLogger(ViewEmployee.class);
//
//    @Inject
//    private EmployeeService employeeService;
//
//    @Inject
//    private RoleService roleService;
//
//    @Inject
//    private UserService userService;
//
//    @Property
//    private Employee employee;
//
//    @Property
//    private User user;
//
//    @Property
//    private int employeeId;
//
//    @Inject
//    private SessionFactory sessionFactory;
//
//    @ActivationRequestParameter("u")
//    @Property
//    private String loggedInUsername;
//
//    @Property
//    private User loggedInUser;
//
//    @Property
//    private boolean popupVisible = false; // Controls visibility of the image popup
//
//
//    // onActivate method to fetch employee and associated user by employee ID
//    void onActivate(int id) {
//        if (loggedInUsername != null) {
//            this.loggedInUser = userService.findByUsername(loggedInUsername);
//        }
//        this.employeeId = id;
//        employee = employeeService.getEmployeeById(id);
//
//        logger.info("inside onActivate(): employee = {}", employee != null ? employee.getName() : "null");
//
//        if (employee != null) {
//            if (employee.getUser() != null) {
//                user = employee.getUser(); // Fetch the associated user
//            } else {
//                user = null;
//            }
//
//            // Set default image URL if not already set
//            if (employee.getImageUrl() == null || employee.getImageUrl().isEmpty()) {
//                employee.setImageUrl("https://via.placeholder.com/150");
//            }
//        }
//    }
//
//    // To check if the logged-in user is admin
//    public boolean isAdmin() {
//        if (loggedInUser == null) {
//            return false;
//        }
//        Role role = userService.getRole(loggedInUser);
//        return "ADMIN".equals(role.getName());
//    }
//
//    // For URL activation (to pass the employeeId to the page URL)
//    Object onPassivate() {
//        return employeeId;
//    }
//
//    public Role getUserRole() {
//        return userService.getRole(user);
//    }
//
//    // Event handler for user creation
//    Object onCreateUser() {
//        if (employee != null && employee.getUser() == null) {  // Create user only if no user exists for this employee
//            String username = employee.getName().toLowerCase().replaceAll("\\s", "");
//            String password = username + "@" + employee.getAge();
//
//            User newUser = new User();
//            newUser.setUsername(username);
//            newUser.setPassword(password);
//
//            // Assign role
//            Role role = roleService.findByName("CLIENT"); // Fetch the CLIENT role
//            newUser.setRole(role);
//
//            // Save the user and associate it with the employee
//            userService.saveUser(newUser);
//            employee.setUser(newUser);
//
//            // Update employee and persist the relationship
//            employeeService.updateEmployee(employee);
//
//            user = newUser; // Reflect the changes in the view
//        }
//
//        return null; // Stay on the same page
//    }
//
//    // Event handler to show the image popup
//    @Component(id = "popupZone")
//    private Zone popupZone;
//// Import org.apache.tapestry5.ajax.Zone
//
//    public Object onActionFromShowImagePopup() {
//        popupVisible = true;
//        return popupZone.getBody(); // Return zone body after setting flag
//    }
//    public Object onClosePopup() {
//        popupVisible = false;
//        return null;
//    }
//
//}
//
//

import com.example.entity.Employee;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.services.EmployeeService;
import com.example.services.RoleService;
import com.example.services.UserService;
import jakarta.inject.Inject;
import org.apache.tapestry5.annotations.*;

import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Response;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;

import java.io.IOException;

public class ViewEmployee {

    private final Logger logger = LoggerFactory.getLogger(ViewEmployee.class);

    @Inject
    private EmployeeService employeeService;

    @Inject
    private RoleService roleService;

    @Inject
    private UserService userService;

    @Property
    private Employee employee;

    @Property
    private User user;

    @Property
    private int employeeId;

    @Inject
    private SessionFactory sessionFactory;
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @ActivationRequestParameter("u")
    @Property
    private String loggedInUsername;

    @Property
    private User loggedInUser;

    @Property
    private boolean popupVisible = false; // Controls visibility of the image popup

    private Session session;
    @Inject

    public void setSession(Response session) {
        this.session = (Session) session;  // Inject the Response service using setter method
    }

    private Response response;
    @Inject
    public void setResponse(Response response) {
        this.response = response;  // Inject the Response service using setter method
    }


    // onActivate method to fetch employee and associated user by employee ID
    void onActivate(int id) {
        if (loggedInUsername != null) {
            this.loggedInUser = userService.findByUsername(loggedInUsername);
        }
        this.employeeId = id;
        employee = employeeService.getEmployeeById(id);

        logger.info("inside onActivate(): employee = {}", employee != null ? employee.getName() : "null");

        if (employee != null) {
            if (employee.getUser() != null) {
                user = employee.getUser(); // Fetch the associated user
            } else {
                user = null;
            }

            // Set default image URL if not already set
            if (employee.getImageUrl() == null || employee.getImageUrl().isEmpty()) {
                employee.setImageUrl("https://via.placeholder.com/150");
            }
        }
    }

    // To check if the logged-in user is admin
    public boolean isAdmin() {
        if (loggedInUser == null) {
            return false;
        }
        Role role = userService.getRole(loggedInUser);
        return "ADMIN".equals(role.getName());
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
            employee.setUser(newUser);

            // Update employee and persist the relationship
            employeeService.updateEmployee(employee);

            user = newUser; // Reflect the changes in the view
        }

        return null; // Stay on the same page
    }

    // Event handler to show the image popup
    @Component(id = "popupZone")
    private Zone popupZone;
// Import org.apache.tapestry5.ajax.Zone

    public Object onActionFromShowImagePopup() {
        popupVisible = true;
        return popupZone.getBody(); // Return zone body after setting flag
    }
    public Object onClosePopup() {
        popupVisible = false;
        return null;
    }
    void onLogout() {
        // Clear the session to log the user out
        getSession().clear();
    }

    // Event handler for logout
    public Object onLogoutPage() {
        // Clear the session and redirect to the login page
        session.clear();
        return LoginPage.class;  // Redirect to the Login page
    }



//    void onPromotionDone(Employee updatedEmployee) {
//        this.employee = updatedEmployee; // refresh view with updated data
//    }

//void onPromotionDone(Employee employee) {
//    this.employee = employee;
//}



}



//import com.example.entity.Employee;
//import com.example.entity.Role;
//import com.example.entity.User;
//import com.example.services.EmployeeService;
//import com.example.services.RoleService;
//import com.example.services.UserService;
//import jakarta.inject.Inject;
//import org.apache.tapestry5.annotations.*;
//
//import org.apache.tapestry5.corelib.components.Zone;
//import org.apache.tapestry5.services.Request;
//import org.apache.tapestry5.services.Response;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.event.EventListener;
//
//import java.io.IOException;
//
//public class ViewEmployee {
//
//    private final Logger logger = LoggerFactory.getLogger(ViewEmployee.class);
//
//    @Inject
//    private EmployeeService employeeService;
//
//    @Inject
//    private RoleService roleService;
//
//    @Inject
//    private UserService userService;
//
//    @Property
//    private Employee employee;
//
//    @Property
//    private User user;
//
//    @Property
//    private int employeeId;
//
//    @Inject
//    private SessionFactory sessionFactory;
//
//    @ActivationRequestParameter("u")
//    @Property
//    private String loggedInUsername;
//
//    @Property
//    private User loggedInUser;
//
//    @Property
//    private boolean popupVisible = false; // Controls visibility of the image popup
//
//    // Fetch session for operations like logout
//    private Session session;
//    @Inject
//    public void setSession(Response session) {
//        this.session = (Session) session;
//    }
//
//    // onActivate method to fetch employee and associated user by employee ID
//    void onActivate(int id) {
//        if (loggedInUsername != null) {
//            this.loggedInUser = userService.findByUsername(loggedInUsername);
//        }
//        this.employeeId = id;
//        employee = employeeService.getEmployeeById(id);
//
//        logger.info("inside onActivate(): employee = {}", employee != null ? employee.getName() : "null");
//
//        if (employee != null) {
//            if (employee.getUser() != null) {
//                user = employee.getUser(); // Fetch the associated user
//            } else {
//                user = null;
//            }
//
//            // Set default image URL if not already set
//            if (employee.getImageUrl() == null || employee.getImageUrl().isEmpty()) {
//                employee.setImageUrl("https://via.placeholder.com/150");
//            }
//        }
//    }
//
//    // To check if the logged-in user is admin
//    public boolean isAdmin() {
//        if (loggedInUser == null) {
//            return false;
//        }
//        Role role = userService.getRole(loggedInUser);
//        return "ADMIN".equals(role.getName());
//    }
//    public Role getUserRole() {
//        return userService.getRole(user);
//    }
//
//
//    // For URL activation (to pass the employeeId to the page URL)
//    Object onPassivate() {
//        return employeeId;
//    }
//
//    // Handle the promotion of an employee
//    public Object onPromote(Employee employee) {
//        if (employee != null) {
//            String currentDesignation = employee.getDesignation();
//
//            // Promotion logi   c based on current designation
//            if ("INTERN".equals(currentDesignation)) {
//                employee.setDesignation("EMPLOYEE");
//            } else if ("EMPLOYEE".equals(currentDesignation)) {
//                employee.setDesignation("MANAGER");
//            } else if ("ADMIN".equals(currentDesignation)) {
//                employee.setDesignation("MANAGER");
//            } else {
//                // If already an Admin, no promotion
//                logger.info("Employee is already at the highest designation.");
//            }
//
//            // Save the updated employee to the database
//            employeeService.updateEmployee(employee);
//
//            // Optionally, reflect the updated employee details after promotion
//            this.employee = employee;
//        }
//
//        return null; // Return null to stay on the same page
//    }
//
//    // Event handler for user creation
//    Object onCreateUser() {
//        if (employee != null && employee.getUser() == null) {
//            String username = employee.getName().toLowerCase().replaceAll("\\s", "");
//            String password = username + "@" + employee.getAge();
//
//            User newUser = new User();
//            newUser.setUsername(username);
//            newUser.setPassword(password);
//
//            // Assign role
//            Role role = roleService.findByName("CLIENT"); // Fetch the CLIENT role
//            newUser.setRole(role);
//
//            // Save the user and associate it with the employee
//            userService.saveUser(newUser);
//            employee.setUser(newUser);
//
//            // Update employee and persist the relationship
//            employeeService.updateEmployee(employee);
//
//            user = newUser; // Reflect the changes in the view
//        }
//
//        return null; // Stay on the same page
//    }
//
//    // Event handler to show the image popup
//    @Component(id = "popupZone")
//    private Zone popupZone;
//
//    public Object onActionFromShowImagePopup() {
//        popupVisible = true;
//        return popupZone.getBody(); // Return zone body after setting flag
//    }
//
//    public Object onClosePopup() {
//        popupVisible = false;
//        return null;
//    }
//
//    void onLogout() {
//        // Clear the session to log the user out
//        session.clear();
//    }
//
//    // Event handler for logout
//    public Object onLogoutPage() {
//        // Clear the session and redirect to the login page
//        session.clear();
//        return LoginPage.class;  // Redirect to the Login page
//    }
//}
