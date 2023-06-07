package com.wisam.eventsmanager.controller.web;

import com.wisam.eventsmanager.domain.Organizer;
import com.wisam.eventsmanager.domain.Presenter;
import com.wisam.eventsmanager.service.OrganizerService;
import com.wisam.eventsmanager.service.PresenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/presenters")
public class PresenterWebController {
    private final PresenterService presenterService;
    private final OrganizerService organizerService;

    @Autowired
    public PresenterWebController(PresenterService presenterService, OrganizerService organizerService) {
        this.presenterService = presenterService;
        this.organizerService = organizerService;
    }

    @GetMapping
    public String getPresenters(Model model) {
        List<Presenter> presenters = presenterService.getAllPresenters();
        List<Organizer> organizers = organizerService.getAllOrganizers();
        model.addAttribute("presenters", presenters);
        model.addAttribute("organizers", organizers);
        model.addAttribute("presenter", new Presenter());
        return "presenters";
    }

    @PostMapping
    public String createPresenter(@ModelAttribute("presenter") Presenter presenter) {
        // Set the organizer for the presenter
        Organizer organizer = organizerService.getOrganizerById(presenter.getOrganizer().getId()).orElse(null);
        if (organizer != null) {
            presenter.setOrganizer(organizer);
            presenterService.createPresenter(presenter);
        }
        return "redirect:/presenters";
    }

    @GetMapping("/{id}")
    public String getPresenterById(@PathVariable Long id, Model model) {
        presenterService.getPresenterById(id).ifPresent(presenter -> model.addAttribute("presenter", presenter));
        return "presenter-details";
    }

    @GetMapping("/{id}/delete")
    public String deletePresenter(@PathVariable Long id) {
        presenterService.deletePresenter(id);
        return "redirect:/presenters";
    }
}
