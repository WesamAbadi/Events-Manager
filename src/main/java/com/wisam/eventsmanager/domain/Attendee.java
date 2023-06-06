package com.wisam.eventsmanager.domain;

import jakarta.persistence.*;
import lombok.Data;
//import javax.persistence.*;

@Data
@Entity
@Table(name = "attendees")
public class Attendee {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
