package com.machine.TimeDeal.repository;

import com.machine.TimeDeal.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String emailId);
}
