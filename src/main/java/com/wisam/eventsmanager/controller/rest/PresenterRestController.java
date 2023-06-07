package com.wisam.eventsmanager.controller.rest;

import com.wisam.eventsmanager.domain.Organizer;
import com.wisam.eventsmanager.domain.Presenter;
import com.wisam.eventsmanager.service.OrganizerService;
import com.wisam.eventsmanager.service.PresenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/presenters")
public class PresenterRestController {
    private final PresenterService presenterService;
    private final OrganizerService organizerService;

    @Autowired
    public PresenterRestController(PresenterService presenterService, OrganizerService organizerService) {
        this.presenterService = presenterService;
        this.organizerService = organizerService;
    }

    @GetMapping
    public List<Presenter> getAllPresenters(@RequestParam(required = false) Long organizerId) {
        if (organizerId != null) {
            return presenterService.getAllPresentersByOrganizer(organizerId);
        } else {
            return presenterService.getAllPresenters();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Presenter> getPresenter(@PathVariable Long id) {
        Optional<Presenter> presenter = presenterService.getPresenterById(id);
        return presenter.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
        public ResponseEntity<Presenter> createPresenter(@ModelAttribute("presenterForm") Presenter presenter) {

        Long organizerId = presenter.getOrganizer().getId();
        Optional<Organizer> organizer = organizerService.getOrganizerById(organizerId);
        if (organizer.isPresent()) {
            presenter.setOrganizer(organizer.get());
            Presenter createdPresenter = presenterService.createPresenter(presenter);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPresenter);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePresenter(@PathVariable Long id) {
        boolean deleted = presenterService.deletePresenter(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
