package com.wisam.eventsmanager.service;

import com.wisam.eventsmanager.domain.Organizer;
import com.wisam.eventsmanager.domain.Presenter;
import com.wisam.eventsmanager.repository.OrganizerRepository;
import com.wisam.eventsmanager.repository.PresenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PresenterService {
    private final PresenterRepository presenterRepository;
    private final OrganizerService organizerService;

    @Autowired
    public PresenterService(PresenterRepository presenterRepository, OrganizerService organizerService) {
        this.presenterRepository = presenterRepository;
        this.organizerService = organizerService;
    }

    public List<Presenter> getAllPresenters() {
        return presenterRepository.findAll();
    }

    public Optional<Presenter> getPresenterById(Long id) {
        return presenterRepository.findById(id);
    }

    public Presenter createPresenter(Presenter presenter) {
        Optional<Organizer> organizer = organizerService.getOrganizerById(presenter.getOrganizer().getId());
        if (organizer.isPresent()) {
            presenter.setOrganizer(organizer.get());
            return presenterRepository.save(presenter);
        }
        return null;
    }

    public Presenter updatePresenter(Long id, Presenter updatedPresenter) {
        Optional<Presenter> existingPresenter = presenterRepository.findById(id);
        if (existingPresenter.isPresent()) {
            Presenter presenter = existingPresenter.get();
            presenter.setName(updatedPresenter.getName());
            presenter.setExpertise(updatedPresenter.getExpertise());
            presenter.setOrganizer(updatedPresenter.getOrganizer());
            return presenterRepository.save(presenter);
        }
        return null;
    }

    public boolean deletePresenter(Long id) {
        Optional<Presenter> presenter = presenterRepository.findById(id);
        if (presenter.isPresent()) {
            presenterRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Presenter> getAllPresentersByOrganizer(Long organizerId) {
        return presenterRepository.findByOrganizerId(organizerId);
    }
}
