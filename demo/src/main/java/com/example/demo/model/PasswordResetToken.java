package com.example.demo.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;

import java.time.LocalDateTime;

@Entity
@Table(name = "password_reset_token")
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token")
    private String token;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "delivery_person_id", referencedColumnName = "id")
    private DeliveryPerson deliveryPerson;

    // Add a method to check if either user or deliveryPerson is null but not both


    @Column(name = "expiry_time")
    private LocalDateTime expiryTime;

    @Column(name = "new_password")
    private String newPassword;

    public DeliveryPerson getDeliveryPerson() {
        return deliveryPerson;
    }

    public void setDeliveryPerson(DeliveryPerson deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }

    @AssertTrue(message = "Either user_id or delivery_person_id must be provided, but not both")
    private boolean isEitherUserOrDeliveryPersonNotNull() {
        return (user != null && deliveryPerson == null) || (user == null && deliveryPerson != null);
    }

    public PasswordResetToken(String token, User user, DeliveryPerson deliveryPerson, LocalDateTime expiryTime, String newPassword, boolean active) {
        this.token = token;
        this.user = user;
        this.deliveryPerson = deliveryPerson;
        this.expiryTime = expiryTime;
        this.newPassword = newPassword;
        this.active = active;
    }

    public PasswordResetToken(Long id, String token, User user, DeliveryPerson deliveryPerson, LocalDateTime expiryTime, String newPassword, boolean active) {
        this.id = id;
        this.token = token;
        this.user = user;
        this.deliveryPerson = deliveryPerson;
        this.expiryTime = expiryTime;
        this.newPassword = newPassword;
        this.active = active;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    public PasswordResetToken() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Column(name = "active")
    private boolean active;

    // Constructors, getters, and setters
}
