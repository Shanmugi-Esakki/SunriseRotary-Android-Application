package com.example.SunriseSignup.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SunriseSignup.Entity.User;



public interface SignupRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}