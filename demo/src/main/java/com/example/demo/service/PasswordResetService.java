package com.example.demo.service;

import com.example.demo.model.PasswordResetToken;
import com.example.demo.model.User;
import com.example.demo.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class PasswordResetService {

    private final UserService userService;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final JavaMailSender javaMailSender;

    @Autowired
    public PasswordResetService(UserService userService, PasswordResetTokenRepository passwordResetTokenRepository, JavaMailSender javaMailSender) {
        this.userService = userService;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.javaMailSender = javaMailSender;
    }
    public PasswordResetToken generateToken(User user) {
        // Generate a random token
        String token = UUID.randomUUID().toString();

        // Deactivate existing tokens for the user
        deactivateExistingTokens(user);

        // Set token expiry time (e.g., 1 hour from now)
        LocalDateTime expiryTime = LocalDateTime.now().plusHours(1);

        // Create a new password reset token
        PasswordResetToken resetToken = new PasswordResetToken(token, user, expiryTime, true);

        // Save the token in the database
        passwordResetTokenRepository.save(resetToken);

        return resetToken;
    }

    private void deactivateExistingTokens(User user) {
        // Find and deactivate existing tokens for the user
        PasswordResetToken existingToken = passwordResetTokenRepository.findByUserAndActiveTrue(user);
        if (existingToken != null) {
            existingToken.setActive(false);
            passwordResetTokenRepository.save(existingToken);
        }
    }

    public boolean resetPassword(String email, String token, String newPassword) {
        // Find the user by email
        User user = userService.findByEmailId(email);
        if (user == null) {
            return false;
        }

        // Find the token in the database
        PasswordResetToken resetToken = passwordResetTokenRepository.findByUserAndTokenAndActiveTrue(user, token);
        if (resetToken == null || resetToken.getExpiryTime().isBefore(LocalDateTime.now())) {
            return false;
        }

        // Update the user's password
        user.setPassword(newPassword);
        userService.save(user);

        // Invalidate the token
        resetToken.setActive(false);
        passwordResetTokenRepository.save(resetToken);

        return true;
    }

    public void sendResetTokenEmail(String userEmail, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userEmail);
        message.setSubject("Password Reset");
        message.setText("Dear User,\n\n"
                + "Please use the following token to reset your password: " + token + "\n\n"
                + "This token is valid for a limited time.\n\n"
                + "Regards,\n"
                + "Grateful Plate");

        javaMailSender.send(message);
    }
}
