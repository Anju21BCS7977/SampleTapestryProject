package com.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    private String address;
    @OneToOne
    @JoinColumn(name = "username",foreignKey = @ForeignKey(name="employee_userid_fk"))
    private User user;



    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
