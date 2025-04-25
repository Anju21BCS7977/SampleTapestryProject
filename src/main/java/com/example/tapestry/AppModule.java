package com.example.tapestry;

import com.example.services.EmployeeService;
import com.example.services.EmployeeServiceImpl;
import com.example.services.UserService;
import com.example.services.UserServiceImpl;
import org.apache.tapestry5.commons.MappedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;

public class AppModule {
    public static void bind(ServiceBinder binder) {
        binder.bind(UserService.class, UserServiceImpl.class);
        binder.bind(EmployeeService.class, EmployeeServiceImpl.class);
    }
    public static void contributeApplicationDefaults(MappedConfiguration<String, Object> configuration) {
//        configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en");
//        configuration.add(SymbolConstants.DEFAULT_STYLESHEET, "context:assets/css/empty.css");
        configuration.add("tapestry.production-mode", false);
        configuration.add("tapestry.reload-classes", true);
        configuration.add("tapestry.reload-pages", true);
        configuration.add("tapestry.reload-resources", true);
    }
}
