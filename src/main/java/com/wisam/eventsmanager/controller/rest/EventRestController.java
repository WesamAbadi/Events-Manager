package com.wisam.eventsmanager.controller.rest;

import ch.qos.logback.core.model.Model;
import com.wisam.eventsmanager.domain.Event;
import com.wisam.eventsmanager.domain.Organizer;
import com.wisam.eventsmanager.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventRestController {
    private final EventService eventService;

    @Autowired
    public EventRestController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Optional<Event> event = eventService.getEventById(id);
        return event.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public String createEvent(@ModelAttribute Event event, RedirectAttributes redirectAttributes) {
        Event createdEvent = eventService.createEvent(event);
        if (createdEvent != null) {
            redirectAttributes.addFlashAttribute("event", createdEvent);
            return "<button type=\"button\" class=\"main-button\" onclick=\"location.href='/events'\">Back to events</button>"; // Redirect to the events list page
        } else {
            // Handle the case when event creation fails
            return "error";
        }
    }


    @PutMapping("/{id}")
    public String updateEvent(@PathVariable Long id, @ModelAttribute Event event, RedirectAttributes redirectAttributes) {
        Event updatedEvent = eventService.updateEvent(id, event);
        if (updatedEvent != null) {
            redirectAttributes.addFlashAttribute("event", updatedEvent);
            return "redirect:/events/" + id; // Redirect to the updated event details page
        } else {
            // Handle the case when the event is not found
            return "error";
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        boolean deleted = eventService.deleteEvent(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
