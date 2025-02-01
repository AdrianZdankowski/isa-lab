package com.example.demo.element.service;

import com.example.demo.element.entity.NatureElement;
import com.example.demo.element.repository.NatureElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NatureElementService {
    private final NatureElementRepository natureElementRepository;

    @Autowired
    public NatureElementService(NatureElementRepository natureElementRepository) {
        this.natureElementRepository = natureElementRepository;
    }

    public Optional<NatureElement> getNatureElementById(UUID id) {
        return natureElementRepository.findById(id);
    }

    public Optional<NatureElement> getNatureElementByName(String name) { return natureElementRepository.findByElementName(name); }

    public List<NatureElement> getAllNatureElements() {
        return natureElementRepository.findAll();
    }

    public NatureElement saveNatureElement(NatureElement natureElement) {
        return natureElementRepository.save(natureElement);
    }

    public void deleteNatureElement(UUID id) {
        natureElementRepository.deleteById(id);
    }

    public List<NatureElement> getNatureElementsBySide(String elementSide) {
        return natureElementRepository.findByElementSide(elementSide);
    }
}
