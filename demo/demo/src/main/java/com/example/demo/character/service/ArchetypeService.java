package com.example.demo.character.service;

import com.example.demo.character.entity.Archetype;
import com.example.demo.character.entity.Character;
import com.example.demo.character.repository.ArchetypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArchetypeService {
    private final ArchetypeRepository archetypeRepository;

    @Autowired
    public ArchetypeService(ArchetypeRepository archetypeRepository) {
        this.archetypeRepository = archetypeRepository;
    }

    public Optional<Archetype> getArchetypeById(UUID id) {
        return archetypeRepository.findById(id);
    }

    public Optional<Archetype> getArchetypeByName(String name) { return archetypeRepository.findByName(name); }

    public List<Archetype> getAllArchetypesWithCharactersAndElements() { return archetypeRepository.findAllWithCharactersAndElements(); }

    public List<Archetype> getAllArchetypes() {
        return archetypeRepository.findAll();
    }

    public Archetype saveArchetype(Archetype archetype) {
        return archetypeRepository.save(archetype);
    }

    public void deleteArchetype(UUID id) {
        archetypeRepository.deleteById(id);
    }

    public Archetype getArchetypeByCharacterName(String characterName) {
        return archetypeRepository.findByCharacterListName(characterName);
    }
}
