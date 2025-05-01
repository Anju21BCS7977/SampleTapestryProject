package com.example.components;
import com.example.entity.Employee;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.Parameter;
public class WishBanner {
    @Property
    @Parameter(required = true)
    private Employee employee;

    // Method to render the birthday banner with appropriate colors
    @BeginRender
    public void beginRender(MarkupWriter writer) {
        if (employee.getGender() != null) {
            String color = employee.getGender().equalsIgnoreCase("Female") ? "Pink" : "SkyBlue";
            writer.element("div", "style", "background-color:" + color + "; padding: 10px; text-align: center;");
            writer.write("Happy Birthday, " + employee.getName() + "!");
            writer.end();
        }
    }
}
