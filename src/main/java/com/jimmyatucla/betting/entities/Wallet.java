package com.jimmyatucla.betting.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "wallet")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(nullable = false)
    private Double balance;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getUserId() {
        return userId;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
    public Double getBalance() {
        return balance;
    }
}