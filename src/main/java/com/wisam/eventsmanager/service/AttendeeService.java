package com.wisam.eventsmanager.service;

import com.wisam.eventsmanager.domain.Attendee;
import com.wisam.eventsmanager.repository.AttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendeeService {
    private final AttendeeRepository attendeeRepository;

    @Autowired
    public AttendeeService(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    public List<Attendee> getAllAttendees() {
        return attendeeRepository.findAll();
    }

    public Optional<Attendee> getAttendeeById(Long id) {
        return attendeeRepository.findById(id);
    }

    public Attendee createAttendee(Attendee attendee) {
        return attendeeRepository.save(attendee);
    }

    public Attendee updateAttendee(Long id, Attendee updatedAttendee) {
        Optional<Attendee> existingAttendee = attendeeRepository.findById(id);
        if (existingAttendee.isPresent()) {
            Attendee attendee = existingAttendee.get();
            attendee.setName(updatedAttendee.getName());
            attendee.setEmail(updatedAttendee.getEmail());
            return attendeeRepository.save(attendee);
        }
        return null;
    }

    public boolean deleteAttendee(Long id) {
        Optional<Attendee> attendee = attendeeRepository.findById(id);
        if (attendee.isPresent()) {
            attendeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
