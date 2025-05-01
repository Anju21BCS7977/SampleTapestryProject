package com.example.dao;

import com.example.entity.Role;
import com.example.entity.User;

public interface UserDao {
    User findByUsername(String username);
    Role getRole(User user);
    User findById(int id);
    void saveUser(User user);
}
