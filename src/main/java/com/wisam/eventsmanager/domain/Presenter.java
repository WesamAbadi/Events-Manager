package com.wisam.eventsmanager.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "presenters")
public class Presenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String expertise;

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private Organizer organizer;
}
