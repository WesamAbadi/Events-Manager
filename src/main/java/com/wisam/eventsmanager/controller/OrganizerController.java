package com.wisam.eventsmanager.controller;

import com.wisam.eventsmanager.domain.Organizer;
import com.wisam.eventsmanager.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/organizers")
public class OrganizerController {
    private final OrganizerService organizerService;

    @Autowired
    public OrganizerController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    @GetMapping
    public String getAllOrganizers(Model model) {
        List<Organizer> organizers = organizerService.getAllOrganizers();
        model.addAttribute("organizers", organizers);
        model.addAttribute("organizer", new Organizer()); // Add this line to create a new Organizer object for the form
        return "organizers";
    }

    @PostMapping
    public String createOrganizer(@ModelAttribute("organizer") Organizer organizer) {
        // Code to create the organizer
        organizerService.createOrganizer(organizer);
        return "redirect:/api/organizers"; // Redirect back to the GET mapping to display the updated list of organizers
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
