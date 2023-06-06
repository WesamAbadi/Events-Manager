package com.wisam.eventsmanager.controller;

import com.wisam.eventsmanager.domain.Attendee;
import com.wisam.eventsmanager.domain.Event;
import com.wisam.eventsmanager.service.AttendeeService;
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
@RequestMapping("/api/attendees")
public class AttendeeController {
    private final AttendeeService attendeeService;
    private final EventService eventService;

    @Autowired
    public AttendeeController(AttendeeService attendeeService, EventService eventService) {
        this.attendeeService = attendeeService;
        this.eventService = eventService;
    }

    @GetMapping
    public String getAllAttendees(Model model) {
        List<Attendee> attendees = attendeeService.getAllAttendees();
        List<Event> events = eventService.getAllEvents();

        model.addAttribute("attendees", attendees);
        model.addAttribute("events", events);

        return "attendees";
    }

    @PostMapping
    public String createAttendee(@ModelAttribute("attendee") Attendee attendee, @RequestParam("eventId") Long eventId) {
        Optional<Event> event = eventService.getEventById(eventId);
        if (event.isPresent()) {
            attendee.setEvent(event.get());
            attendeeService.createAttendee(attendee);
            return "redirect:/api/attendees";
        } else {
            return "error";
        }
    }

    @GetMapping("/{id}")
    public String getAttendeeById(@PathVariable Long id, Model model) {
        Optional<Attendee> attendee = attendeeService.getAttendeeById(id);
        attendee.ifPresent(value -> model.addAttribute("attendee", value));
        return attendee.isPresent() ? "attendee-details" : "error";
    }

    @PutMapping("/{id}")
    public String updateAttendee(@PathVariable Long id, @ModelAttribute("attendee") Attendee attendee) {
        Attendee updatedAttendee = attendeeService.updateAttendee(id, attendee);
        return updatedAttendee != null ? "redirect:/api/attendees" : "error";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendee(@PathVariable Long id) {
        boolean deleted = attendeeService.deleteAttendee(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
