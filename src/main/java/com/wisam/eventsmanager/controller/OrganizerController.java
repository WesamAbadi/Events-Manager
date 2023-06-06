package com.wisam.eventsmanager.controller;

import com.wisam.eventsmanager.domain.Event;
import com.wisam.eventsmanager.domain.Organizer;
import com.wisam.eventsmanager.service.OrganizerService;
import com.wisam.eventsmanager.service.EventService;
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
    private final EventService eventService;

    @Autowired
    public OrganizerController(OrganizerService organizerService, EventService eventService) {
        this.organizerService = organizerService;
        this.eventService = eventService;
    }

    @GetMapping
    public String getAllOrganizers(Model model) {
        List<Organizer> organizers = organizerService.getAllOrganizers();
        model.addAttribute("organizers", organizers);
        model.addAttribute("organizerForm", new Organizer());
        return "organizers";
    }

    @PostMapping
    public String createOrganizer(@ModelAttribute("organizerForm") Organizer organizer) {
        organizerService.createOrganizer(organizer);
        return "redirect:/api/organizers/" + organizer.getId();
    }

    @GetMapping("/{id}")
    public String getOrganizerById(@PathVariable Long id, Model model) {
        Optional<Organizer> organizer = organizerService.getOrganizerById(id);
        organizer.ifPresent(value -> {
            model.addAttribute("organizer", value);
            List<Event> events = eventService.getEventsByOrganizerId(id);
            model.addAttribute("events", events);
        });
        return organizer.isPresent() ? "organizer-details" : "error";
    }

    @GetMapping("/{id}/events")
    public String getEventsByOrganizerId(@PathVariable Long id, Model model) {
        List<Event> events = eventService.getEventsByOrganizerId(id);
        model.addAttribute("events", events);
        return "organizer-events";
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
