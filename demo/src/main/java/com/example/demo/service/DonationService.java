package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.DeliveryPersonRepository;
import com.example.demo.repository.DonationRepository;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationService {
    private final DonationRepository donationRepository;
    private final DeliveryPersonRepository deliveryPersonRepository;
    private final UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(DonationService.class);

    @Autowired
    public DonationService(DonationRepository donationRepository, DeliveryPersonRepository deliveryPersonRepository, UserRepository userRepository) {
        this.donationRepository = donationRepository;
        this.deliveryPersonRepository = deliveryPersonRepository;
        this.userRepository = userRepository;
    }


    public Donation addDonation(Donation donation) {
        if (donation.getUser() == null || donation.getUser().getUserId() == null) {
            throw new IllegalArgumentException("User ID cannot be null.");
        }

        // Logic for automatic assignment
        DeliveryPerson deliveryPerson = findAvailableDeliveryPerson();

        if (deliveryPerson == null) {
            throw new DataIntegrityViolationException("Failed to create donation request. No available delivery person.");
        }

        donation.setDeliveryPerson(deliveryPerson);
        donation.setDonationStatus("picked-up");
        donationRepository.save(donation);

        // Update delivery person status
        deliveryPerson.setStatus(DeliveryPersonStatus.ON_DUTY_BUSY);
        deliveryPersonRepository.save(deliveryPerson);

        return donation;
    }

    private DeliveryPerson findAvailableDeliveryPerson() {
        logger.info("Finding an available delivery person.");

        // Logic to find the first delivery person who is on duty and free
        return deliveryPersonRepository.findFirstByStatusOrderByIdAsc(DeliveryPersonStatus.ON_DUTY_FREE);
    }


    public void updateDonation(Donation donation) {
        logger.info("Updating donation with ID: " + donation.getDonationId());

        donationRepository.save(donation);

        logger.info("Donation updated successfully.");
    }

    public Donation getDonationById(int donationId) {
        logger.info("Fetching donation with ID: " + donationId);

        return donationRepository.findById(donationId).orElse(null);
    }
}
