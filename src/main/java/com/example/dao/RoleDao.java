package com.example.dao;

import com.example.entity.Role;
import java.util.List;

public interface RoleDao {
    void save(Role role);
    Role findByName(String name);
    Role findById(int id);
    List<Role> findAll();
}
