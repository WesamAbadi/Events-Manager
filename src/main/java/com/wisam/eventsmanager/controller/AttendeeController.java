package com.wisam.eventsmanager.controller;

import com.wisam.eventsmanager.domain.Attendee;
import com.wisam.eventsmanager.service.AttendeeService;
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

    @Autowired
    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    @GetMapping
    public String getAllAttendees(Model model) {
        List<Attendee> attendees = attendeeService.getAllAttendees();
        model.addAttribute("attendees", attendees);
        return "attendees";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attendee> getAttendeeById(@PathVariable Long id) {
        Optional<Attendee> attendee = attendeeService.getAttendeeById(id);
        return attendee.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Attendee> createAttendee(@RequestBody Attendee attendee) {
        Attendee createdAttendee = attendeeService.createAttendee(attendee);
        return new ResponseEntity<>(createdAttendee, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attendee> updateAttendee(@PathVariable Long id, @RequestBody Attendee attendee) {
        Attendee updatedAttendee = attendeeService.updateAttendee(id, attendee);
        return updatedAttendee != null ?
                new ResponseEntity<>(updatedAttendee, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendee(@PathVariable Long id) {
        boolean deleted = attendeeService.deleteAttendee(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
