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
    private boolean isAdmin;

    @Property

    @ActivationRequestParameter("u")
    private String loggedInUsername;

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
        return true;
    }
}
