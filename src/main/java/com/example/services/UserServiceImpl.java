package com.example.services;

import com.example.dao.RoleDao;
import com.example.dao.UserDao;
import com.example.entity.Role;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {


    private final UserDao userDao;
    private final RoleDao roleDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public boolean loginValid(String username, String password) {
        User user = userDao.findByUsername(username);
        return user != null && user.getPassword().equals(password);  // Validate login
    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);  // Delegate the saving of user to the DAO layer
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
        // Fetch user by username
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRole(User user) {
        return userDao.getRole(user);
    }
    @Transactional
    @Override
    public void changeRoleToAdmin(User user) {
        // Fetch the "ADMIN" role from the RoleDao
        Role adminRole = roleDao.findByName("ADMIN");

        if (user != null && adminRole != null) {
            // Change the user's role to ADMIN
            user.setRole(adminRole);
            userDao.saveUser(user);  // Save the updated user
        }
    }

}
