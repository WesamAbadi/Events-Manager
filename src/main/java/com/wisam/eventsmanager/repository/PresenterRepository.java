package com.wisam.eventsmanager.repository;

import com.wisam.eventsmanager.domain.Presenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresenterRepository extends JpaRepository<Presenter, Long> {
    List<Presenter> findByOrganizerId(Long organizerId);
}
