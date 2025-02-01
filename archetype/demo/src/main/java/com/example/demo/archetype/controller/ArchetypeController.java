package com.example.demo.archetype.controller;

import com.example.demo.archetype.dto.*;
import com.example.demo.archetype.entity.Archetype;
import com.example.demo.archetype.service.ArchetypeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class ArchetypeController {
    private final ArchetypeService archetypeService;

    @Value("${api.character.url}")
    private String characterServiceUrl;

    @Autowired
    public ArchetypeController(ArchetypeService archetypeService) {
        this.archetypeService = archetypeService;
    }

    // Get all archetypes
    @GetMapping("api/archetypes")
    public ResponseEntity<GetArchetypesResponse> getAllArchetypes() {
        List<GetArchetypesResponse.ArchetypeInfo> archetypeSummaries = archetypeService.getAllArchetypes()
                .stream()
                .map(archetype -> GetArchetypesResponse.ArchetypeInfo.builder()
                        .id(archetype.getId())
                        .name(archetype.getName())
                        .build())
                .collect(Collectors.toList());

        GetArchetypesResponse response = GetArchetypesResponse.builder()
                .archetypes(archetypeSummaries)
                .build();

        return ResponseEntity.ok(response);
    }

    // Get archetype by UUID
    @GetMapping("/api/archetypes/{id}")
    public ResponseEntity<?> getArchetype(@PathVariable UUID id) {
        Optional<Archetype> optionalArchetype = archetypeService.getArchetypeById(id);
        if (optionalArchetype.isPresent()) {
            Archetype archetype = optionalArchetype.get();
            GetArchetypeResponse response = GetArchetypeResponse.builder()
                    .id(archetype.getId())
                    .name(archetype.getName())
                    .baseHealth(archetype.getBaseHealth())
                    .baseDamage(archetype.getBaseDamage())
                    .build();
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Archetype with id: " + id + " not found").error("NOT_FOUND").build());
        }
    }

    // Get archetype by name
    @GetMapping("/api/archetypes/name/{archetypeName}")
    public ResponseEntity<?> getArchetypeByName(@PathVariable String archetypeName) {
        Optional<Archetype> optionalArchetype = archetypeService.getArchetypeByName(archetypeName);
        if (optionalArchetype.isPresent()) {
            Archetype archetype = optionalArchetype.get();
            GetArchetypeResponse response = GetArchetypeResponse.builder()
                    .id(archetype.getId())
                    .name(archetype.getName())
                    .baseHealth(archetype.getBaseHealth())
                    .baseDamage(archetype.getBaseDamage())
                    .build();
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Archetype with name: " + archetypeName + " not found").error("NOT_FOUND").build());
        }
    }

    // Add new archetype
    @PostMapping("/api/archetypes")
    public ResponseEntity<?> addArchetype(@RequestBody PostArchetypeRequest request) {
        if (request.getName() == null || request.getBaseHealth() == null || request.getBaseHealth() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.builder().message("All fields are required").error("BAD_REQUEST").build());
        }

        Archetype archetype = Archetype.builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .baseHealth(request.getBaseHealth())
                .baseDamage(request.getBaseDamage())
                .build();

        archetypeService.saveArchetype(archetype);
        System.out.println(archetype.getId());
        addToCharacterProject(archetype.getId());

        GetArchetypeResponse response = GetArchetypeResponse.builder()
                .id(archetype.getId())
                .name(archetype.getName())
                .baseHealth(archetype.getBaseHealth())
                .baseDamage(archetype.getBaseDamage())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Update archetype by UUID
    @PatchMapping("/api/archetypes/{id}")
    public ResponseEntity<?> patchArchetype(@PathVariable UUID id, @RequestBody PatchArchetypeRequest request) {
        Optional<Archetype> optionalArchetype = archetypeService.getArchetypeById(id);

        if (optionalArchetype.isPresent()) {
            Archetype archetype = optionalArchetype.get();

            if (request.getName() != null) {
                archetype.setName(request.getName());
            }

            if (request.getBaseHealth() != null) {
                archetype.setBaseHealth(request.getBaseHealth());
            }

            if (request.getBaseDamage() != null) {
                archetype.setBaseDamage(request.getBaseDamage());
            }

            archetypeService.saveArchetype(archetype);

            GetArchetypeResponse response = GetArchetypeResponse.builder()
                    .id(archetype.getId())
                    .name(archetype.getName())
                    .baseHealth(archetype.getBaseHealth())
                    .baseDamage(archetype.getBaseDamage())
                    .build();

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Archetype with id: " + id + " not found").error("NOT_FOUND").build());
        }
    }

    // Delete archetype by UUID
    @DeleteMapping("/api/archetypes/{id}")
    public ResponseEntity<?> deleteArchetype(@PathVariable UUID id) {
      try {
          archetypeService.deleteArchetype(id);
          deleteFromCharacterProject(id);
          return ResponseEntity.ok().build();
      }
      catch (EntityNotFoundException e) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Archetype with id:" + id +" not found").error("NOT_FOUND").build());
      }
    }

    private void deleteFromCharacterProject(UUID id) {
        String characterServiceUrl = this.characterServiceUrl + "/api/archetypes/" +id;
        RestTemplate restTemplate = new RestTemplate();

        try {
            restTemplate.delete(characterServiceUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addToCharacterProject(UUID id) {
        String characterServiceUrl = this.characterServiceUrl+"/api/archetypes";
        RestTemplate restTemplate = new RestTemplate();

        try {
            restTemplate.postForObject(characterServiceUrl, Map.of("id",id),Void.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
