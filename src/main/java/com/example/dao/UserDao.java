package com.example.dao;

import com.example.entity.Role;
import com.example.entity.User;

public interface UserDao {
    User findByUsername(String username);  // Find user by username
    Role getRole(User user);  // Find user by username

    User findById(int id);

    void saveUser(User user);  // Save a user to the database
}
