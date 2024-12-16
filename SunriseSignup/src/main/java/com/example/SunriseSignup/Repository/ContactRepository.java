package com.example.SunriseSignup.Repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SunriseSignup.Entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}

