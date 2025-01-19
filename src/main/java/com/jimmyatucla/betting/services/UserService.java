package com.jimmyatucla.betting.services;

import com.jimmyatucla.betting.dtos.UserDTO;
import com.jimmyatucla.betting.entities.*;
import com.jimmyatucla.betting.exceptions.ResourceNotFoundException;
import com.jimmyatucla.betting.mappers.UserMapper;
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

    public List<UserDTO> fetchAll() {
       List<User> users = userRepository.findAll();
        return UserMapper.toUserDTOList(users);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public UserDTO findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));

        return UserMapper.toUserDTO(user);
    }

    public User save(User user) {
        User r = userRepository.findByEmail(user.getEmail());
        if(r != null) {          
            return r;
        }
        if(user.getId() == null) {
            user.setCreatedAt(LocalDateTime.now());
        }
        user.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
