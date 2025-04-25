package com.example.services;

import com.example.entity.Role;
import com.example.entity.User;

public interface RoleService {
    Role findByName(String name);
}
