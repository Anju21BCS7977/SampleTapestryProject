package com.example.pages;

import com.example.services.UserService;
import jakarta.inject.Inject;
import org.apache.tapestry5.http.Link;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.services.PageRenderLinkSource;

public class LoginPage {

    @Property
    private String username;

    @Property
    private String password;

    @Property
    private String errorMsg;

    @Inject
    private UserService userService;

    @Inject
    private PageRenderLinkSource linkSource;

    @InjectComponent
    private Form loginForm;

    public boolean isErrorPresent() {
        return errorMsg != null && !errorMsg.isEmpty();
    }

    // Validation method to check if user credentials are correct
    void onValidateFromLoginForm() {
        if (username == null || username.trim().isEmpty()) {
            loginForm.recordError("Username is required.");
        }

        if (password == null || password.trim().isEmpty()) {
            loginForm.recordError("Password is required.");
        }

        // Validate if the username and password are correct by querying the database
        if (!userService.loginValid(username, password)) {
            errorMsg = "Invalid username or password.";
            loginForm.recordError(errorMsg); // Display the error
        }
    }

    // Called when form is successfully submitted
    Object onSuccess() {
        // Redirect to employee list page after successful login
        Link link= linkSource.createPageRenderLink("employeeList");
        link.addParameter("u",username);
        return link;
    }
}
