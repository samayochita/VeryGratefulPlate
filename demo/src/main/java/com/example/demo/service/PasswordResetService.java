package com.example.demo.service;

import com.example.demo.model.DeliveryPerson;
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

    private final DeliveryPersonService deliveryPersonService;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final JavaMailSender javaMailSender;

    @Autowired
    public PasswordResetService(UserService userService, DeliveryPersonService deliveryPersonService, PasswordResetTokenRepository passwordResetTokenRepository, JavaMailSender javaMailSender) {
        this.userService = userService;
        this.deliveryPersonService=deliveryPersonService;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.javaMailSender = javaMailSender;
    }
    public PasswordResetToken generateUserToken(User user) {
        // Generate a random token
        String token = UUID.randomUUID().toString();

        // Deactivate existing tokens for the user
        deactivateExistingUserTokens(user);

        // Set token expiry time (e.g., 1 hour from now)
        LocalDateTime expiryTime = LocalDateTime.now().plusHours(1);

        // Create a new password reset token
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUser(user); // Set user
        resetToken.setExpiryTime(expiryTime);
        resetToken.setActive(true);


        // Save the token in the database
        passwordResetTokenRepository.save(resetToken);

        return resetToken;
    }

    private void deactivateExistingUserTokens(User user) {
        // Find and deactivate existing tokens for the user
        PasswordResetToken existingToken = passwordResetTokenRepository.findByUserAndActiveTrue(user);
        if (existingToken != null) {
            existingToken.setActive(false);
            passwordResetTokenRepository.save(existingToken);
        }
    }

    public boolean resetUserPassword(String email, String token, String newPassword) {
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

    public void sendResetTokenEmailUser(String userEmail, String token) {
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

    public PasswordResetToken generateDeliveryPersonToken(DeliveryPerson deliveryPerson) {
        // Generate a random token
        String token = UUID.randomUUID().toString();

        // Deactivate existing tokens for the delivery person
        deactivateExistingDeliveryPersonTokens(deliveryPerson);

        // Set token expiry time (e.g., 1 hour from now)
        LocalDateTime expiryTime = LocalDateTime.now().plusHours(1);

        // Create a new password reset token
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setDeliveryPerson(deliveryPerson); // Set delivery person
        resetToken.setExpiryTime(expiryTime);
        resetToken.setActive(true);

        // Save the token in the database
        passwordResetTokenRepository.save(resetToken);

        return resetToken;
    }

    private void deactivateExistingDeliveryPersonTokens(DeliveryPerson deliveryPerson) {
        // Find and deactivate existing tokens for the delivery person
        PasswordResetToken existingToken = passwordResetTokenRepository.findByDeliveryPersonAndActiveTrue(deliveryPerson);
        if (existingToken != null) {
            existingToken.setActive(false);
            passwordResetTokenRepository.save(existingToken);
        }
    }



    public boolean resetDeliveryPersonPassword(String email, String token, String newPassword) {
        // Find the delivery person by email
        DeliveryPerson deliveryPerson = deliveryPersonService.findByEmailId(email);
        if (deliveryPerson == null) {
            return false;
        }

        // Find the token in the database
        PasswordResetToken resetToken = passwordResetTokenRepository.findByDeliveryPersonAndTokenAndActiveTrue(deliveryPerson, token);
        if (resetToken == null || resetToken.getExpiryTime().isBefore(LocalDateTime.now())) {
            return false;
        }

        // Update the delivery person's password
        deliveryPerson.setPassword(newPassword);
        deliveryPersonService.save(deliveryPerson);

        // Invalidate the token
        resetToken.setActive(false);
        passwordResetTokenRepository.save(resetToken);

        return true;
    }
    public void sendResetTokenEmailDeliveryPerson(String deliveryPersonEmail, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(deliveryPersonEmail);
        message.setSubject("Password Reset");
        message.setText("Dear Delivery Person,\n\n"
                + "Please use the following token to reset your password: " + token + "\n\n"
                + "This token is valid for a limited time.\n\n"
                + "Regards,\n"
                + "Grateful Plate");

        javaMailSender.send(message);
    }

}
