package com.example.demo.controllers;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.PasswordResetToken;
import com.example.demo.model.User;
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

    @PostMapping("/forgotpassword")
    public ResponseEntity<String> forgotPassword(@RequestParam("emailId") String emailId) {
        User existingUser = userService.findByEmailId(emailId);
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // Generate token, deactivate existing tokens, and save new token
        PasswordResetToken token = passwordResetService.generateToken(existingUser);

        // Send the token in an email to the user
        passwordResetService.sendResetTokenEmail(existingUser.getEmailId(), token.getToken());

        return ResponseEntity.ok("Password reset token sent successfully");
    }

    @PostMapping("/resetpassword")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetToken request) {
        String emailId = request.getUser().getEmailId();
        String token = request.getToken();
        String newPassword = request.getNewPassword();
        // Validate token and reset password
        boolean resetSuccessful = passwordResetService.resetPassword(emailId, token, newPassword);

        if (resetSuccessful) {
            return ResponseEntity.ok("Password reset successful");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired token");
        }
    }

}
