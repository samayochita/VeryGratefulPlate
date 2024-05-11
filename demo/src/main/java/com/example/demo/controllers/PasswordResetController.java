package com.example.demo.controllers;

import com.example.demo.model.PasswordResetToken;
import com.example.demo.model.User;
import com.example.demo.model.DeliveryPerson;
import com.example.demo.service.DeliveryPersonService;
import com.example.demo.service.PasswordResetService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class PasswordResetController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordResetService passwordResetService;

    @Autowired
    private DeliveryPersonService deliveryPersonService;

    @PostMapping("/forgotpassword/user")
    public ResponseEntity<String> forgotUserPassword(@RequestParam("emailId") String emailId) {
        User existingUser = userService.findByEmailId(emailId);
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // Generate token, deactivate existing tokens, and save new token
        PasswordResetToken token = passwordResetService.generateUserToken(existingUser);

        // Send the token in an email to the user
        passwordResetService.sendResetTokenEmailUser(existingUser.getEmailId(), token.getToken());

        return ResponseEntity.ok("Password reset token sent successfully");
    }

    @PostMapping("/resetpassword/user")
    public ResponseEntity<String> resetUserPassword(@RequestBody PasswordResetToken request) {
        String emailId = request.getUser().getEmailId();
        String token = request.getToken();
        String newPassword = request.getNewPassword();
        // Validate token and reset password
        boolean resetSuccessful = passwordResetService.resetUserPassword(emailId, token, newPassword);

        if (resetSuccessful) {
            return ResponseEntity.ok("Password reset successful");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired token");
        }
    }

    // Endpoint for forgot password for delivery person
    @PostMapping("/forgotpassword/deliveryperson")
    public ResponseEntity<String> forgotDeliveryPersonPassword(@RequestParam("emailId") String emailId) {
        DeliveryPerson existingDeliveryPerson = deliveryPersonService.findByEmailId(emailId);
        if (existingDeliveryPerson == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Delivery person not found");
        }

        // Generate token, deactivate existing tokens, and save new token
        PasswordResetToken token = passwordResetService.generateDeliveryPersonToken(existingDeliveryPerson);

        // Send the token in an email to the delivery person
        passwordResetService.sendResetTokenEmailDeliveryPerson(existingDeliveryPerson.getEmailId(), token.getToken());

        return ResponseEntity.ok("Password reset token sent successfully");
    }

    // Endpoint for reset password for delivery person
    @PostMapping("/resetpassword/deliveryperson")
    public ResponseEntity<String> resetDeliveryPersonPassword(@RequestBody PasswordResetToken request) {
        String emailId = request.getDeliveryPerson().getEmailId();
        String token = request.getToken();
        String newPassword = request.getNewPassword();
        // Validate token and reset password
        boolean resetSuccessful = passwordResetService.resetDeliveryPersonPassword(emailId, token, newPassword);

        if (resetSuccessful) {
            return ResponseEntity.ok("Password reset successful");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired token");
        }
    }

}
