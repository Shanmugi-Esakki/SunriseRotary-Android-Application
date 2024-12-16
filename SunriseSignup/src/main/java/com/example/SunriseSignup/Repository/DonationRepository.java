package com.example.SunriseSignup.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SunriseSignup.Entity.Donation;

public interface DonationRepository extends JpaRepository<Donation, Long> {
}

