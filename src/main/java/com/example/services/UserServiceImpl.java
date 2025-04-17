package com.example.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final Map<String, String> users = new HashMap<>();

    public UserServiceImpl() {
        // sample hardcoded users
        users.put("admin", "admin123");
        users.put("anju", "1234");
    }

    @Override
    public boolean loginValid(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }
}
