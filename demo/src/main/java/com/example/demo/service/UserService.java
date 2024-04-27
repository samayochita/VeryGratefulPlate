package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;

import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
