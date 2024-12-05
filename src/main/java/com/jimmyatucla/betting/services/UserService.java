package com.jimmyatucla.betting.services;

import com.jimmyatucla.betting.entities.*;
import com.jimmyatucla.betting.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        Optional<User> existingUserOptional = userRepository.findByEmail(user.getEmail());
        if (existingUserOptional.isPresent()) {
            // Update the existing user's properties
            User existingUser = existingUserOptional.get();
            existingUser.setUsername(user.getUsername());
       
            // Update other properties as needed
            return userRepository.save(existingUser);
        } else {
            // Save the new user
            return userRepository.save(user);
        }
    }


    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
