package com.wisam.eventsmanager.controller.rest;

import com.wisam.eventsmanager.domain.Attendee;
import com.wisam.eventsmanager.service.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attendees")
public class AttendeeRestController {
    private final AttendeeService attendeeService;

    @Autowired
    public AttendeeRestController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    @GetMapping
    public List<Attendee> getAllAttendees() {
        return attendeeService.getAllAttendees();
    }

    @PostMapping
        public ResponseEntity<Attendee> createAttendee(@ModelAttribute("attendeeForm") Attendee attendee) {

            Attendee createdAttendee = attendeeService.createAttendee(attendee);
        return new ResponseEntity<>(createdAttendee, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attendee> getAttendeeById(@PathVariable Long id) {
        Optional<Attendee> attendee = attendeeService.getAttendeeById(id);
        return attendee.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
