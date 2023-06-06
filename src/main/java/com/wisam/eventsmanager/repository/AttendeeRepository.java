package com.wisam.eventsmanager.repository;

import com.wisam.eventsmanager.domain.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
}
