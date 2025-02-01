package com.example.demo.character.service;

import com.example.demo.character.entity.Character;
import com.example.demo.character.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CharacterService {
    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public Optional<Character> getCharacterById(UUID id) {
        return characterRepository.findById(id);
    }

    public Optional<Character> getCharacterByName(String name) {
        return characterRepository.findByName(name);
    }

    public List<Character> getAllCharacters() {return characterRepository.findAllWithArchetypeAndElement();}

    public Character saveCharacter(Character c) {
        return characterRepository.save(c);
    }

    public void deleteCharacter(UUID id) {
        characterRepository.deleteById(id);
    }

    public List<Character> getCharactersByArchetypeName(String archetypeName) {
        return characterRepository.findByArchetype_Name(archetypeName);
    }

}
