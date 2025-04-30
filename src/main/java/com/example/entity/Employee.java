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


    // Getter and Setter for role
    @Column(name = "image_url") // Optional: if you have a column in the database
    private String imageUrl;

    // Getter and Setter for imageUrl
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    @Column(name = "designation")
    private String designation;

    // Getter and Setter
    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }



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
