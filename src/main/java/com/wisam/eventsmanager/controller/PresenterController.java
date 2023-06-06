package com.wisam.eventsmanager.controller;

import com.wisam.eventsmanager.domain.Organizer;
import com.wisam.eventsmanager.domain.Presenter;
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

    @Autowired
    public PresenterController(PresenterService presenterService) {
        this.presenterService = presenterService;
    }

    @GetMapping
    public ResponseEntity<List<Presenter>> getAllPresenters() {
        List<Presenter> presenters = presenterService.getAllPresenters();
        return new ResponseEntity<>(presenters, HttpStatus.OK);

    }
    @PostMapping
    public ResponseEntity<Presenter> createPresenter(@RequestBody Presenter presenter) {
        Presenter createdPresenter = presenterService.createPresenter(presenter);
        return new ResponseEntity<>(createdPresenter, HttpStatus.CREATED);
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
