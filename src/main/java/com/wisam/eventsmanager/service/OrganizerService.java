package com.wisam.eventsmanager.service;

import com.wisam.eventsmanager.domain.Organizer;
import com.wisam.eventsmanager.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizerService {
    private final OrganizerRepository organizerRepository;

    @Autowired
    public OrganizerService(OrganizerRepository organizerRepository) {
        this.organizerRepository = organizerRepository;
    }

    public List<Organizer> getAllOrganizers() {
        return organizerRepository.findAll();
    }

    public Optional<Organizer> getOrganizerById(Long id) {
        return organizerRepository.findById(id);
    }

    public Organizer createOrganizer(Organizer organizer) {
        return organizerRepository.save(organizer);
    }

    public Organizer updateOrganizer(Long id, Organizer updatedOrganizer) {
        Optional<Organizer> existingOrganizer = organizerRepository.findById(id);
        if (existingOrganizer.isPresent()) {
            Organizer organizer = existingOrganizer.get();
            organizer.setName(updatedOrganizer.getName());
            organizer.setOrganization(updatedOrganizer.getOrganization());
            return organizerRepository.save(organizer);
        }
        return null;
    }

    public boolean deleteOrganizer(Long id) {
        Optional<Organizer> organizer = organizerRepository.findById(id);
        if (organizer.isPresent()) {
            organizerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
