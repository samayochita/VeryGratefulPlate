package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.Donation;
import com.example.demo.model.User;

import com.example.demo.repository.DonationRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final DonationRepository donationRepository;

    @Autowired
    public UserService(UserRepository userRepository, DonationRepository donationRepository) {
        this.userRepository = userRepository;
        this.donationRepository = donationRepository;
    }

    public boolean existsByEmail(String email_id) {
        return userRepository.existsByEmailId(email_id);
    }

    public User findByEmailId(String email_id) {
        Optional<User> userOptional = userRepository.findByEmailId(email_id);
        return userOptional.orElse(null);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    // Method to get donations by user ID
    public List<Donation> getDonationsByUserId(int userId) {
        // Fetch all donations from the repository

        List<Donation> allDonations = donationRepository.findAll();

        // Filter donations by user ID
        return allDonations.stream()
                .filter(donation -> donation.getUserId() == userId)
                .collect(Collectors.toList());
    }

    // Method to get all donations sorted by status
    public List<Donation> getAllDonationsSortedByStatus() {
        // Fetch all donations from the repository
        List<Donation> allDonations = donationRepository.findAll();

        // Sort donations by status: pending, picked up, delivered
        return allDonations.stream()
                .sorted(Comparator.comparing(Donation::getDonationStatus))
                .collect(Collectors.toList());
    }
}
