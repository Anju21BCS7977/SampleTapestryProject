package com.example.dao;

import com.example.entity.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Role role) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(role);
            tx.commit();
        }
    }

    @Override
    public Role findByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Role WHERE name = :name", Role.class)
                          .setParameter("name", name)
                          .uniqueResult();
        }
    }

    @Override
    public Role findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Role.class, id);
        }
    }

    @Override
    public List<Role> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Role", Role.class).list();
        }
    }
}
