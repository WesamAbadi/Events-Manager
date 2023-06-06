package com.wisam.eventsmanager.controller;

import com.wisam.eventsmanager.domain.Event;
import com.wisam.eventsmanager.domain.Organizer;
import com.wisam.eventsmanager.domain.Presenter;
import com.wisam.eventsmanager.service.OrganizerService;
import com.wisam.eventsmanager.service.PresenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/presenters")
public class PresenterController {
    private final PresenterService presenterService;
    private final OrganizerService organizerService;


    @Autowired
    public PresenterController(PresenterService presenterService, OrganizerService organizerService) {
        this.presenterService = presenterService;
        this.organizerService = organizerService;

    }

    @GetMapping
    public String getPresenters(Model model) {
        List<Presenter> presenters = presenterService.getAllPresenters();
        List<Organizer> organizers = organizerService.getAllOrganizers();
        model.addAttribute("presenters", presenters);
        model.addAttribute("organizers", organizers);
        return "presenters";
    }

    @GetMapping("/organizer/{organizerId}")
    public ResponseEntity<List<Presenter>> getPresentersByOrganizerId(@PathVariable Long organizerId) {
        List<Presenter> presenters = presenterService.getPresentersByOrganizerId(organizerId);
        return new ResponseEntity<>(presenters, HttpStatus.OK);
    }

    // Other methods remain the same
    // ...

    @PostMapping
    public String createPresenter(@ModelAttribute("presenter") Presenter presenter) {
        // Set the organizer for the presenter
        Organizer organizer = organizerService.getOrganizerById(presenter.getOrganizer().getId()).orElse(null);
        if (organizer != null) {
            presenter.setOrganizer(organizer);
            Presenter createdPresenter = presenterService.createPresenter(presenter);
            if (createdPresenter != null) {
                return "redirect:/api/presenters";
            }
        }
        return "error"; // handle error case
    }
    @GetMapping("/organizer/{organizerId}/html")
    public String getPresentersByOrganizerIdHtml(@PathVariable Long organizerId, Model model) {
        List<Presenter> presenters = presenterService.getPresentersByOrganizerId(organizerId);
        model.addAttribute("presenters", presenters);
        return "presenter-options"; // Return the name of the HTML template for presenter options
    }



    @GetMapping("/{id}")
    public ResponseEntity<Presenter> getPresenterById(@PathVariable Long id) {
        Optional<Presenter> presenter = presenterService.getPresenterById(id);
        return presenter.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Presenter> updatePresenter(@PathVariable Long id, @RequestBody Presenter presenter) {
        Presenter updatedPresenter = presenterService.updatePresenter(id, presenter);
        return updatedPresenter != null ?
                new ResponseEntity<>(updatedPresenter, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePresenter(@PathVariable Long id) {
        boolean deleted = presenterService.deletePresenter(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
