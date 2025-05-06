package com.example.dao;

import com.example.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.inject.Inject;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDao {


    private final SessionFactory sessionFactory;
@Autowired
    public EmployeeDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Employee employee) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(employee);
            tx.commit();
        }
    }

    @Override
    public void update(Employee employee) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(employee);
            tx.commit();
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Employee emp = session.get(Employee.class, id);
            if (emp != null) {
                session.delete(emp);
            }
            tx.commit();
        }
    }

    @Override
    public Employee findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Employee.class, id);
        }
    }

    @Override
    public List<Employee> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Employee", Employee.class).list();
        }
    }
    @Override
    public List<Employee> searchEmployeesByName(String keyword) {
        try (Session session = sessionFactory.openSession()) {
            String sql = "SELECT * FROM employees WHERE search_vector @@ to_tsquery(:query)";
            NativeQuery<Employee> query = session.createNativeQuery(sql, Employee.class);
            query.setParameter("query", keyword + ":*"); // Prefix search
            return query.getResultList();
        }
    }
    @Override
    public List<String> findEmployeeNamesByPrefix(String prefix) {
        try (Session session = sessionFactory.openSession()) {
            String sql = "SELECT name FROM employees WHERE search_vector @@ to_tsquery(:query)";
            NativeQuery<String> query = session.createNativeQuery(sql);
            query.setParameter("query", prefix + ":*"); // Prefix search for full-text
            return query.getResultList();
        }
    }
}
