package com.wisam.eventsmanager.repository;

import com.wisam.eventsmanager.domain.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Long> {
}
