package com.example.tapestry;

import com.example.services.EmployeeService;
import com.example.services.EmployeeServiceImpl;
import com.example.services.UserService;
import com.example.services.UserServiceImpl;
import org.apache.tapestry5.commons.MappedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.services.Response;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AppModule {

    public static void bind(ServiceBinder binder) {
        binder.bind(UserService.class, UserServiceImpl.class);
        binder.bind(EmployeeService.class, EmployeeServiceImpl.class);

    }
    @Contribute(SessionFactory.class)
    public static void provideSessionFactory(MappedConfiguration<String, Object> configuration) {
        // Provide the configuration that you need for Hibernate
        configuration.add("hibernate.session_factory", new Configuration().configure().buildSessionFactory());
    }
    @Contribute(Response.class)
    public static void provideResponse(MappedConfiguration<String, Object> configuration) {
        // No need to add anything here; this makes Response available for DI
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
