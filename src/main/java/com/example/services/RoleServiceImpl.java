package com.example.services;

import com.example.dao.RoleDao;
import com.example.entity.Role;
import com.example.entity.User;
import jakarta.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Inject
    private RoleDao roleDao;  // Inject RoleDao to fetch roles from DB

    @Override
    public Role findByName(String name) {
        return roleDao.findByName(name);  // Fetch the role from the database by name
    }

}
