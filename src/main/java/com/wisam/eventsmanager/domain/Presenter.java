package com.wisam.eventsmanager.domain;

import jakarta.persistence.*;
import lombok.Data;
//import javax.persistence.*;

@Data
@Entity
@Table(name = "presenters")
public class Presenter {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String expertise;
}
