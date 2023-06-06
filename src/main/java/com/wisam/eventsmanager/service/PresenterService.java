package com.wisam.eventsmanager.service;

import com.wisam.eventsmanager.domain.Presenter;
import com.wisam.eventsmanager.repository.PresenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PresenterService {
    private final PresenterRepository presenterRepository;

    @Autowired
    public PresenterService(PresenterRepository presenterRepository) {
        this.presenterRepository = presenterRepository;
    }

    public List<Presenter> getAllPresenters() {
        return presenterRepository.findAll();
    }

    public Optional<Presenter> getPresenterById(Long id) {
        return presenterRepository.findById(id);
    }

    public Presenter createPresenter(Presenter presenter) {
        return presenterRepository.save(presenter);
    }

    public Presenter updatePresenter(Long id, Presenter updatedPresenter) {
        Optional<Presenter> existingPresenter = presenterRepository.findById(id);
        if (existingPresenter.isPresent()) {
            Presenter presenter = existingPresenter.get();
            presenter.setName(updatedPresenter.getName());
            presenter.setExpertise(updatedPresenter.getExpertise());
            presenter.setOrganizer(updatedPresenter.getOrganizer()); // Set the organizer
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

    public List<Presenter> getPresentersByOrganizerId(Long organizerId) {
        return presenterRepository.findByOrganizerId(organizerId);
    }
}
