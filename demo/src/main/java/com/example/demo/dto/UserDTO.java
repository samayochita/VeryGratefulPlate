package com.example.demo.dto;

import com.example.demo.model.UserType;
public class UserDTO {

    private String emailId;
    private String password;
    private String userType;

    public UserDTO(String emailId, String password, String userType) {
        this.emailId = emailId;
        this.password = password;
        this.userType = userType;
    }



    public UserDTO() {
        // Default constructor
    }

    // Getters and Setters for emailId, password, and userType
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
