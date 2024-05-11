package com.example.demo.controllers;
import com.example.demo.model.DeliveryPerson;
import com.example.demo.model.DeliveryPersonStatus;
import com.example.demo.model.Donation;
import com.example.demo.service.DeliveryPersonService;
import com.example.demo.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@RestController
@RequestMapping("/api/deliveryperson")
public class DeliveryPersonController {

    private final DeliveryPersonService deliveryPersonService;
    private final DonationService donationService;

    @Autowired
    public DeliveryPersonController(DeliveryPersonService deliveryPersonService, DonationService donationService) {
        this.deliveryPersonService = deliveryPersonService;
        this.donationService = donationService;
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
    public ResponseEntity<String> logout(HttpServletRequest request) {
        try {
            // Invalidate session
            request.getSession().invalidate();
            return ResponseEntity.ok("Logout successful");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Logout failed");
        }
    }
    @PutMapping("/{deliveryPersonId}/pickedup")
    public ResponseEntity<String> markAsPickedUp(@PathVariable("deliveryPersonId") Long deliveryPersonId) {
        // Find delivery person by id
        DeliveryPerson deliveryPerson = deliveryPersonService.findByDeliveryPersonId(deliveryPersonId);
        if (deliveryPerson == null) {
            return ResponseEntity.notFound().build();
        }
        // Find pending donations associated with the delivery person
        List<Donation> pendingDonations = donationService.getPendingDonationsForDeliveryPerson(deliveryPersonId);
        if (pendingDonations.isEmpty()) {
            return ResponseEntity.badRequest().body("No pending donations for this delivery person.");
        }

        // Mark the first pending donation as picked up
        Donation pendingDonation = pendingDonations.get(0);
        pendingDonation.setDonationStatus("picked up");
        donationService.updateDonation(pendingDonation);

        return ResponseEntity.ok().body("Donation status updated to picked up");
    }

    @PutMapping("/{deliveryPersonId}/delivered")
    public ResponseEntity<String> markAsDelivered(@PathVariable("deliveryPersonId") Long deliveryPersonId) {
        // Find delivery person by id
        DeliveryPerson deliveryPerson = deliveryPersonService.findByDeliveryPersonId(deliveryPersonId);
        if (deliveryPerson == null) {
            return ResponseEntity.notFound().build();
        }

        // Find delivered donations associated with the delivery person
        List<Donation> pickedupDonations = donationService.getPickedupDonationsByDeliveryPerson(deliveryPersonId);
        if (pickedupDonations.isEmpty()) {
            return ResponseEntity.badRequest().body("No picked up donations for this delivery person.");
        }

        // Mark the first delivered donation as delivered
        Donation pickedupDonation = pickedupDonations.get(0);
        pickedupDonation.setDonationStatus("delivered");
        donationService.updateDonation(pickedupDonation);

        return ResponseEntity.ok().body("Donation status updated to delivered");
    }
    @GetMapping("/{deliveryPersonId}/pendingdonations")
    public ResponseEntity<List<Donation>> getPendingDonationsForDeliveryPerson(@PathVariable("deliveryPersonId") Long deliveryPersonId) {
        List<Donation> pendingDonations = deliveryPersonService.getPendingDonationsForDeliveryPerson(deliveryPersonId);
        if (pendingDonations != null) {
            return ResponseEntity.ok(pendingDonations);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}