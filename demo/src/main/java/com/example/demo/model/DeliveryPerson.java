package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "delivery_person")
public class DeliveryPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email_id", unique = true)
    private String emailId;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private DeliveryPersonStatus status;

    public DeliveryPerson() {

    }

    public Long getId() {
        return id;
    }

    public DeliveryPerson(String emailId, String password, String name, String phoneNumber, DeliveryPersonStatus status) {
        this.emailId = emailId;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public DeliveryPerson(Long id, String emailId, String password, String name, String phoneNumber, DeliveryPersonStatus status) {
        this.id = id;
        this.emailId = emailId;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public DeliveryPersonStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryPersonStatus status) {
        this.status = status;
    }


    // Getters and setters
}


