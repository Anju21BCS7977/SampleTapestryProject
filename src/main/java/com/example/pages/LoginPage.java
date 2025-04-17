//package com.example.pages;
//
//import com.example.services.UserService;
//import jakarta.inject.Inject;
//
//import org.apache.tapestry5.annotations.Property;
//import org.apache.tapestry5.services.PageRenderLinkSource;
//
//public class LoginPage {
//
//    @Property
//    private String username;
//
//    @Property
//    private String password;
//
//    @Property
//    private String errorMsg;
//
//    @Inject
//    private UserService userService;
//
//    @Inject
//    private PageRenderLinkSource linkSource;
//
//    Object onSuccess() {
//        if (userService.loginValid(username, password)) {
//            return linkSource.createPageRenderLink("employeeList");
//        } else {
//            errorMsg = "Invalid credentials.";
//            return null;
//        }
//    }
//}
//package com.example.pages;
//
//import com.example.services.UserService;
//import jakarta.inject.Inject;
//import org.apache.tapestry5.annotations.InjectComponent;
//import org.apache.tapestry5.annotations.Property;
//
//import org.apache.tapestry5.corelib.components.Form;
//import org.apache.tapestry5.services.PageRenderLinkSource;
//
//public class LoginPage {
//
//    @Property
//    private String username;
//
//    @Property
//    private String password;
//
//    @Property
//    private String errorMsg;
//
//    @Inject
//    private UserService userService;
//
//    @Inject
//    private PageRenderLinkSource linkSource;
//
//    @InjectComponent
//    private Form loginForm;
//
//    // Validation method
//    void onValidateFromLoginForm() {
//        if (username == null || username.trim().isEmpty()) {
//            loginForm.recordError("Username is required.");
//        }
//        if (password == null || password.trim().isEmpty()) {
//            loginForm.recordError("Password is required.");
//        }
//        // Validate if the username and password are correct
//        if (!userService.loginValid(username, password)) {
//            loginForm.recordError("Invalid username or password.");
//        }
//    }
//
//
//
//    // Called when form is successfully submitted
//    Object onSuccess() {
//        return linkSource.createPageRenderLink("employeeList"); // Redirect to employee list page
//    }
//}
//
package com.example.pages;

import com.example.services.UserService;
import jakarta.inject.Inject;
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

    // Use form validation phase
    void onValidateFromLoginForm() {
        if (!userService.loginValid(username, password)) {
            errorMsg = "Invalid username or password.";
            loginForm.recordError(errorMsg); // this triggers the error display
        }
    }

    Object onSuccess() {
        // Only called if no validation errors
        return linkSource.createPageRenderLink("employeeList");
    }
}
