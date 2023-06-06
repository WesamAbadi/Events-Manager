package com.wisam.eventsmanager.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Date date;
    private int attendees;

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private Organizer organizer;

    @ManyToOne
    @JoinColumn(name = "presenter_id")
    private Presenter presenter;
}
