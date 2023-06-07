package com.wisam.eventsmanager.controller.rest;

import com.wisam.eventsmanager.domain.Organizer;
import com.wisam.eventsmanager.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/organizers")
public class OrganizerRestController {
    private final OrganizerService organizerService;

    @Autowired
    public OrganizerRestController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    @GetMapping
    public List<Organizer> getAllOrganizers() {
        return organizerService.getAllOrganizers();
    }

    @PostMapping
//    public ResponseEntity<Organizer> createOrganizer(@RequestBody Organizer organizer) {
        public ResponseEntity<Organizer> createOrganizer(@ModelAttribute("organizerForm") Organizer organizer) {
        Organizer createdOrganizer = organizerService.createOrganizer(organizer);
        return new ResponseEntity<>(createdOrganizer, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organizer> getOrganizerById(@PathVariable Long id) {
        Optional<Organizer> organizer = organizerService.getOrganizerById(id);
        return organizer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Organizer> updateOrganizer(@PathVariable Long id, @RequestBody Organizer organizer) {
        Organizer updatedOrganizer = organizerService.updateOrganizer(id, organizer);
        return updatedOrganizer != null ?
                new ResponseEntity<>(updatedOrganizer, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganizer(@PathVariable Long id) {
        boolean deleted = organizerService.deleteOrganizer(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
