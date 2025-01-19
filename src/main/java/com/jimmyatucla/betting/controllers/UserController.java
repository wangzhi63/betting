

package com.jimmyatucla.betting.controllers;

import com.jimmyatucla.betting.entities.User;
import com.jimmyatucla.betting.dtos.UserDTO;
import com.jimmyatucla.betting.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.jimmyatucla.betting.entities.Wallet;
import com.jimmyatucla.betting.services.WalletService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WalletService walletService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.fetchAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        return user.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<User> createOrUpdateUser(@RequestBody User user) {
        User savedUser = userService.save(user);
        Wallet wallet = walletService.findByUserId(savedUser.getId());
        if (wallet == null) {
            Wallet newWallet = new Wallet();
            newWallet.setUserId(savedUser.getId());
            newWallet.setBalance(1000000.0);

            walletService.save(newWallet);
            
        }
        return ResponseEntity.ok(savedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}