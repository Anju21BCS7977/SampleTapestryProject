//package com.example.util;
//
//import com.example.entity.Employee;
//import org.hibernate.SessionFactory;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.service.ServiceRegistry;
//
//public class HibernateUtil {
//
//    private static final SessionFactory sessionFactory = buildSessionFactory();
//
//    private static SessionFactory buildSessionFactory() {
//        try {
//            Configuration configuration = new Configuration();
//            configuration.configure("hibernate.cfg.xml");
//            configuration.addAnnotatedClass(Employee.class);
//
//            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                    .applySettings(configuration.getProperties()).build();
//
//            return configuration.buildSessionFactory(serviceRegistry);
//        } catch (Exception e) {
//            System.err.println("Initial SessionFactory creation failed: " + e);
//            throw new ExceptionInInitializerError(e);
//        }
//    }
//
//    public static SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
//
//    public static void shutdown() {
//        if (sessionFactory != null) {
//            try {
//                sessionFactory.close();
//            } catch (Exception e) {
//                System.err.println("SessionFactory close failed: " + e);
//            }
//        }
//    }
//}
