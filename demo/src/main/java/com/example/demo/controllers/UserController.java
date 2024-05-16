package com.example.demo.controllers;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.model.UserType;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        try {
            LOGGER.info("Registering user...");

            // Set userType to USER
            userDTO.setUserType("USER");
            LOGGER.info("User type set to USER");

            // Convert UserDTO to User entity
            User user = mapUserDtoToEntity(userDTO);
            LOGGER.info("UserDTO converted to User entity"+user);

            if (userService.existsByEmail(userDTO.getEmailId())) {
                LOGGER.error("User with this user_id already exists");
                return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this user_id already exists");
            }

            // Save the new user
            userService.save(user);
            LOGGER.info("User registered successfully");

            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (Exception ex) {
            LOGGER.error("Registration failed", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed");
        }
    }

    // Helper method to map UserDTO to User entity

    private User mapUserDtoToEntity(UserDTO userDTO) {
        User user = new User();
        user.setEmailId(userDTO.getEmailId());
        user.setPassword(userDTO.getPassword());

        // Convert string representation of userType to enum value
        UserType userType = UserType.valueOf(userDTO.getUserType().toUpperCase());
        user.setUserType(userType);

        return user;
    }

    @PostMapping(path = "/loginuser")
    public ResponseEntity<Object> loginUser(@RequestBody UserDTO userDTO) {
        try {
            LOGGER.info("Logging in user...");

            // Check if user with the provided email exists
            User existingUser = userService.findByEmailId(userDTO.getEmailId());

            if (existingUser == null) {
                LOGGER.error("User not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            // Check if the provided password matches the user's password
            if (!existingUser.getPassword().equals(userDTO.getPassword())) {
                LOGGER.error("Incorrect password");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
            }

//             Check if user type is "USER"
            if (!userDTO.getUserType().equals("USER")) {
                LOGGER.error("User login failed: Invalid user type");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User login failed: Invalid user type");
            }

            // Include user ID in the response
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("userId", existingUser.getUserId()); // Assuming ID is stored as 'userId' in User entity

            LOGGER.info("User login successful");
            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            LOGGER.error("Login failed", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed");
        }
    }
    @PostMapping(path = "/loginadmin")
    public ResponseEntity<Object> loginAdmin(@RequestBody UserDTO userDTO) {
        try {
            LOGGER.info("Logging in admin...");

            // Check if user with the provided email exists
            User existingUser = userService.findByEmailId(userDTO.getEmailId());

            if (existingUser == null) {
                LOGGER.error("Admin not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
            }

            // Check if the provided password matches the user's password
            if (!existingUser.getPassword().equals(userDTO.getPassword())) {
                LOGGER.error("Incorrect password");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
            }
            // Check if user type is "ADMIN"
            if (userDTO.getUserType().equals("ADMIN")) {
                LOGGER.info("Admin login successful");

                // Include user ID in the response
                Map<String, Object> responseData = new HashMap<>();
                responseData.put("userId", existingUser.getUserId()); // Assuming ID is stored as 'userId' in User entity

                return ResponseEntity.ok(responseData);
            }

            // If user type is not "ADMIN", return unauthorized
            else {
                LOGGER.error("Admin login failed: Invalid user type");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Admin login failed: Invalid user type");
            }
        } catch (Exception ex) {
            LOGGER.error("Login failed", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed");
        }
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            LOGGER.info("Logging out user...");

            // Invalidate session
            request.getSession().invalidate();

            LOGGER.info("Logout successful");
            return ResponseEntity.ok("Logout successful");
        } catch (Exception ex) {
            LOGGER.error("Logout failed", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Logout failed");
        }
    }


}
