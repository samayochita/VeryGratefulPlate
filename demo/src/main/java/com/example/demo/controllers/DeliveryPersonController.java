package com.example.demo.controllers;
import com.example.demo.model.DeliveryPerson;
import com.example.demo.model.DeliveryPersonStatus;
import com.example.demo.service.DeliveryPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/deliverypersons")
public class DeliveryPersonController {

    private final DeliveryPersonService deliveryPersonService;

    @Autowired
    public DeliveryPersonController(DeliveryPersonService deliveryPersonService) {
        this.deliveryPersonService = deliveryPersonService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerDeliveryPerson(@RequestBody DeliveryPerson deliveryPerson) {
        try {
            // Set the default status for new delivery persons
            deliveryPerson.setStatus(DeliveryPersonStatus.ON_DUTY_FREE);
            DeliveryPerson savedDeliveryPerson = deliveryPersonService.saveDeliveryPerson(deliveryPerson);
            return ResponseEntity.ok(savedDeliveryPerson);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register delivery person");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginDeliveryPerson(@RequestBody DeliveryPerson deliveryPerson) {
        try {
            String email = deliveryPerson.getEmailId();
            String password = deliveryPerson.getPassword();

            // Find the delivery person by email
            DeliveryPerson storedDeliveryPerson = deliveryPersonService.findByEmailId(email);

            // If delivery person is found and password matches
            if (storedDeliveryPerson != null && storedDeliveryPerson.getPassword().equals(password)) {
                return ResponseEntity.ok(storedDeliveryPerson);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to login delivery person");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Invalidate session
            request.getSession().invalidate();
            return ResponseEntity.ok("Logout successful");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Logout failed");
        }
    }
}