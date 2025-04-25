package com.example.services;

import com.example.entity.Role;
import com.example.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    boolean loginValid(String username, String password);  // Validate login credentials
    void saveUser(User user);  // Save user to the database
    User findByUsername(String username);  // Find a user by username
    User findById(int id);
    Role getRole(User user);

    @Transactional
    void changeRoleToAdmin(User user);
}
