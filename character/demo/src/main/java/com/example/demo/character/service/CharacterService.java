package com.example.demo.character.service;

import com.example.demo.character.entity.Character;
import com.example.demo.character.repository.ArchetypeRepository;
import com.example.demo.character.repository.CharacterRepository;
import com.example.demo.character.repository.NatureElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CharacterService {
    private final CharacterRepository characterRepository;
    private final ArchetypeRepository archetypeRepository;

    private final NatureElementRepository natureElementRepository;

    @Autowired
    public CharacterService(CharacterRepository characterRepository,
                            ArchetypeRepository archetypeRepository,
                            NatureElementRepository natureElementRepository) {
        this.characterRepository = characterRepository;
        this.archetypeRepository = archetypeRepository;
        this.natureElementRepository = natureElementRepository;
    }

    public Optional<Character> getCharacterById(UUID id) {
        return characterRepository.findById(id);
    }

    public Optional<Character> getCharacterByName(String name) {
        return characterRepository.findByName(name);
    }

    public List<Character> getAllCharacters() {return characterRepository.findAll();}

    public Character saveCharacter(Character c) {
        return characterRepository.save(c);
    }

    public void deleteCharacter(UUID id) {
        characterRepository.deleteById(id);
    }

    public List<Character> findAllByArchetype(UUID archetypeId) {
        return archetypeRepository.findById(archetypeId)
                .map(characterRepository::findAllByArchetype)
                .orElse(Collections.emptyList());
    }

    public List<Character> findAllByNatureElement(UUID elementId) {
        return natureElementRepository.findById(elementId)
                .map(characterRepository::findAllByElement)
                .orElse(Collections.emptyList());
    }
}
