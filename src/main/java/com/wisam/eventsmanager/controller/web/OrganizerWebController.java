package com.wisam.eventsmanager.controller.web;

import com.wisam.eventsmanager.domain.Event;
import com.wisam.eventsmanager.domain.Organizer;
import com.wisam.eventsmanager.service.OrganizerService;
import com.wisam.eventsmanager.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/organizers")
public class OrganizerWebController {
    private final OrganizerService organizerService;
    private final EventService eventService;

    @Autowired
    public OrganizerWebController(OrganizerService organizerService, EventService eventService) {
        this.organizerService = organizerService;
        this.eventService = eventService;
    }

    @GetMapping
    public String getAllOrganizers(Model model) {
        List<Organizer> organizers = organizerService.getAllOrganizers();
        model.addAttribute("organizers", organizers);
        model.addAttribute("organizerForm", new Organizer());
        return "organizers";
    }

    @PostMapping
    public String createOrganizer(@ModelAttribute("organizerForm") Organizer organizer) {
        organizerService.createOrganizer(organizer);
        return "redirect:/organizers/";
    }

    @GetMapping("/{id}")
    public String getOrganizerById(@PathVariable Long id, Model model) {
        Optional<Organizer> organizer = organizerService.getOrganizerById(id);
        organizer.ifPresent(value -> {
            model.addAttribute("organizer", value);
            List<Event> events = eventService.getEventsByOrganizerId(id);
            model.addAttribute("events", events);
        });
        return organizer.isPresent() ? "organizer-details" : "error";
    }

    @GetMapping("/{id}/events")
    public String getEventsByOrganizerId(@PathVariable Long id, Model model) {
        List<Event> events = eventService.getEventsByOrganizerId(id);
        model.addAttribute("events", events);
        return "organizer-events";
    }

    @GetMapping("/{id}/delete")
    public String deleteOrganizer(@PathVariable Long id, Model model) {
        organizerService.deleteOrganizer(id);
        return "redirect:/organizers";
    }
}
