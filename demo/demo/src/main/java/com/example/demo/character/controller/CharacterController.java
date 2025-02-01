package com.example.demo.character.controller;


import com.example.demo.character.dto.*;
import com.example.demo.character.entity.Archetype;
import com.example.demo.character.entity.Character;
import com.example.demo.character.entity.NatureElement;
import com.example.demo.character.service.ArchetypeService;
import com.example.demo.character.service.CharacterService;
import com.example.demo.character.service.NatureElementService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class CharacterController {
    private final CharacterService characterService;
    private final NatureElementService natureElementService;
    private final ArchetypeService archetypeService;

    @Autowired
    public CharacterController(CharacterService characterService, NatureElementService natureElementService,
                               ArchetypeService archetypeService) {
        this.characterService = characterService;
        this.natureElementService = natureElementService;
        this.archetypeService = archetypeService;
    }

    // Get all characters
    @GetMapping("/api/characters")
    public ResponseEntity<GetCharactersResponse> getAllCharacters() {
        GetCharactersResponse response = GetCharactersResponse.builder()
                .characters(characterService.getAllCharacters().stream()
                        .map(character -> GetCharactersResponse.CharacterInfo.builder()
                                .id(character.getId())
                                .name(character.getName())
                                .build())
                        .collect(Collectors.toList()))
                .build();

        return ResponseEntity.ok(response);
    }

    // Get characters by archetype name
    @GetMapping("api/archetypes/name/{archetypeName}/characters")
    public ResponseEntity<GetCharactersResponse> getCharactersByArchetypeName(@PathVariable String archetypeName) {
        List<GetCharactersResponse.CharacterInfo> characterSummaries = characterService.getCharactersByArchetypeName(archetypeName)
                .stream()
                .map(character -> GetCharactersResponse.CharacterInfo.builder()
                        .id(character.getId())
                        .name(character.getName())
                        .build())
                .collect(Collectors.toList());

        GetCharactersResponse response = GetCharactersResponse.builder()
                .characters(characterSummaries)
                .build();

        return ResponseEntity.ok(response);
    }

    // Get character by UUID
    @GetMapping("api/characters/{id}")
    public ResponseEntity<?> getCharacter(@PathVariable UUID id) {
        Optional<Character> optionalCharacter = characterService.getCharacterById(id);

        if (optionalCharacter.isPresent()) {
            Character character = optionalCharacter.get();
            GetCharacterResponse response = GetCharacterResponse.builder()
                    .id(character.getId())
                    .name(character.getName())
                    .powerLevel(character.getPowerLevel())
                    .archetypeInfo(
                            GetCharacterResponse.ArchetypeInfo.builder()
                                    .id(character.getArchetype().getId())
                                    .name(character.getArchetype().getName())
                                    .build())
                    .elementInfo(
                            GetCharacterResponse.NatureElementInfo.builder()
                                    .id(character.getElement().getId())
                                    .name(character.getElement().getElementName())
                                    .build())
                    .build();

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Character with id: " + id + " not found").error("NOT_FOUND").build());
        }
    }


    // Get character by name
    @GetMapping("api/characters/name/{name}")
    public ResponseEntity<?> getCharacterByName(@PathVariable String name) {
        Optional<Character> optionalCharacter = characterService.getCharacterByName(name);

        if (optionalCharacter.isPresent()) {
            Character character = optionalCharacter.get();
            GetCharacterResponse response = GetCharacterResponse.builder()
                    .id(character.getId())
                    .name(character.getName())
                    .powerLevel(character.getPowerLevel())
                    .archetypeInfo(
                            GetCharacterResponse.ArchetypeInfo.builder()
                                    .id(character.getArchetype().getId())
                                    .name(character.getArchetype().getName())
                                    .build())
                    .elementInfo(
                            GetCharacterResponse.NatureElementInfo.builder()
                                    .id(character.getElement().getId())
                                    .name(character.getElement().getElementName())
                                    .build())
                    .build();

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Character with name: " + name + " not found").error("NOT_FOUND").build());
        }
    }

    // Update character by UUID
    @PatchMapping("api/characters/{id}")
    public ResponseEntity<?> patchCharacter(@PathVariable UUID id, @RequestBody PatchCharacterRequest request) {
        Optional<Character> optionalCharacter = characterService.getCharacterById(id);

        if (optionalCharacter.isPresent()) {
            Character character = optionalCharacter.get();

            if (request.getName() != null) {
                character.setName(request.getName());
            }

            if (request.getPowerLevel() != null) {
                character.setPowerLevel(request.getPowerLevel());
            }

            if (request.getElementId() != null) {
                Optional<NatureElement> optionalElement = natureElementService.getNatureElementById(request.getElementId());
                if (optionalElement.isPresent()) {
                    character.setElement(optionalElement.get());
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Nature element with id: " + request.getElementId() + " not found").error("NOT_FOUND").build());
                }
            }

            characterService.saveCharacter(character);

            GetCharacterResponse response = GetCharacterResponse.builder()
                    .id(character.getId())
                    .name(character.getName())
                    .powerLevel(character.getPowerLevel())
                    .archetypeInfo(
                            GetCharacterResponse.ArchetypeInfo.builder()
                                    .id(character.getArchetype().getId())
                                    .name(character.getArchetype().getName())
                                    .build())
                    .elementInfo(
                            GetCharacterResponse.NatureElementInfo.builder()
                                    .id(character.getElement().getId())
                                    .name(character.getElement().getElementName())
                                    .build())
                    .build();

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Character with id: " + id + " not found").error("NOT_FOUND").build());
        }
    }

    // Add new character
    @PostMapping("api/characters")
    public ResponseEntity<?> addCharacter(@RequestBody PostCharacterRequest request) {
        if (request.getName() == null || request.getPowerLevel() == null || request.getArchetypeId() == null || request.getElementId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.builder().message("All fields are required").error("BAD_REQUEST").build());
        }

        Optional<Archetype> optionalArchetype = archetypeService.getArchetypeById(request.getArchetypeId());
        if (optionalArchetype.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Archetype not found").error("NOT_FOUND").build());
        }

        Optional<NatureElement> optionalElement = natureElementService.getNatureElementById(request.getElementId());
        if (optionalElement.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Nature element not found").error("NOT_FOUND").build());
        }

        Character character = Character.builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .powerLevel(request.getPowerLevel())
                .archetype(optionalArchetype.get())
                .element(optionalElement.get())
                .build();

        characterService.saveCharacter(character);

        optionalArchetype.get().addToList(character);
        archetypeService.saveArchetype(optionalArchetype.get());

        GetCharacterResponse response = GetCharacterResponse.builder()
                .id(character.getId())
                .name(character.getName())
                .powerLevel(character.getPowerLevel())
                .archetypeInfo(
                        GetCharacterResponse.ArchetypeInfo.builder()
                                .id(character.getArchetype().getId())
                                .name(character.getArchetype().getName())
                                .build())
                .elementInfo(
                        GetCharacterResponse.NatureElementInfo.builder()
                                .id(character.getElement().getId())
                                .name(character.getElement().getElementName())
                                .build())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Delete character
    @DeleteMapping("api/characters/{id}")
    public ResponseEntity<?> deleteCharacter(@PathVariable UUID id) {
        try {
            characterService.deleteCharacter(id);
            return ResponseEntity.noContent().build();
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Character with id:" + id +" not found").error("NOT_FOUND").build());
        }
    }
}

