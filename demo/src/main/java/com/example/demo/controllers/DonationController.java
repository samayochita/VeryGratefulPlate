package com.example.demo.controllers;
import com.example.demo.model.Donation;
import com.example.demo.model.User;
import com.example.demo.model.UserType;
import com.example.demo.service.DonationService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donations")
public class DonationController {

    private final DonationService donationService;

    @Autowired
    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }


    @PostMapping(path="/add")
    public Donation addDonation(@RequestBody Donation donation) {
        return donationService.addDonation(donation);
    }


    @PutMapping("/{donationId}/delivered")
    public ResponseEntity<String> markAsDelivered(@PathVariable("donationId") int donationId) {
        Donation existingDonation = donationService.getDonationById(donationId);
        if (existingDonation == null) {
            return ResponseEntity.notFound().build();
        }

        if (!existingDonation.getDonationStatus().equals("picked up")) {
            return ResponseEntity.badRequest().body("Donation status is not picked up.");
        }

        existingDonation.setDonationStatus("delivered");
        donationService.updateDonation(existingDonation);

        return ResponseEntity.ok().body("Donation status updated to delivered");
    }
}
