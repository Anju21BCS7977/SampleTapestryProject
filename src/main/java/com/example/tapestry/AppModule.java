package com.example.tapestry;

import com.example.services.EmployeeService;
import com.example.services.EmployeeServiceImpl;
import com.example.services.UserService;
import com.example.services.UserServiceImpl;
import org.apache.tapestry5.ioc.ServiceBinder;

public class AppModule {
    public static void bind(ServiceBinder binder) {
        binder.bind(UserService.class, UserServiceImpl.class);
        binder.bind(EmployeeService.class, EmployeeServiceImpl.class);
    }
}
