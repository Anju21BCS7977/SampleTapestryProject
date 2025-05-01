package com.example.pages;
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
}



