package com.example.SunriseSignup.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SunriseSignup.Entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
