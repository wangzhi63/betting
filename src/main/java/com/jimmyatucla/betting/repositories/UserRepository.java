package com.jimmyatucla.betting.repositories;

import com.jimmyatucla.betting.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}