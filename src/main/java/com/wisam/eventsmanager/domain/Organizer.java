package com.wisam.eventsmanager.domain;

import jakarta.persistence.*;
import lombok.Data;
//import javax.persistence.*;

@Data
@Entity
@Table(name = "organizers")
public class Organizer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String organization;

}
