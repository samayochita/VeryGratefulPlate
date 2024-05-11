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
}
