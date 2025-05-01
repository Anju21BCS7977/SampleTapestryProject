package com.example.components;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
public class CompanyHeader {
    @Property
    private final String companyName = "Kane Solutions";

    @Property
    private final String companyAddress = "Noida";

    @Parameter(allowNull = true)
    private String loggedInUsername;

    public String getDisplayUsername() {
        return (loggedInUsername != null) ? loggedInUsername : "Guest";
    }
}

