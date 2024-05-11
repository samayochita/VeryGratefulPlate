package com.example.demo.controllers;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.Donation;
import com.example.demo.model.User;
import com.example.demo.model.UserType;
import com.example.demo.service.DonationService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/donations")
public class DonationController {


    private final DonationService donationService;
    private static final Logger logger = LoggerFactory.getLogger(DonationController.class);


    @Autowired
    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }


    @PostMapping(path="/add")
    public Donation addDonation(@RequestBody Donation donation) {
        return donationService.addDonation(donation);
    }

    @GetMapping("/userdonations")
    public ResponseEntity<List<Donation>> getDonations(@RequestParam Integer userId, @RequestParam UserType userType) {
        try {
            if (userType == UserType.USER) {
                // If user is a regular user, retrieve donations associated with their user_id
                List<Donation> userDonations = donationService.getDonationsByUserId(userId);
                return ResponseEntity.ok(userDonations);
            } else if (userType == UserType.ADMIN) {
                // If user is an admin, retrieve all donations
                List<Donation> allDonations = donationService.getAllDonationsSortedByStatus();
                return ResponseEntity.ok(allDonations);
            } else {
                // Handle other user types, such as NGO
                return ResponseEntity.badRequest().body(new ArrayList<>());
            }
        } catch (Exception e) {
            // Handle any exceptions
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
    }


}
