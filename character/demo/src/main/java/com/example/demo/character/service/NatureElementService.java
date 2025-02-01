package com.example.demo.character.service;

import com.example.demo.character.entity.NatureElement;
import com.example.demo.character.repository.NatureElementRepository;
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

    public List<NatureElement> getAllNatureElements() {
        return natureElementRepository.findAll();
    }

    public NatureElement saveNatureElement(NatureElement natureElement) {
        return natureElementRepository.save(natureElement);
    }

    public void deleteNatureElement(UUID id) {
        natureElementRepository.deleteById(id);
    }

}
