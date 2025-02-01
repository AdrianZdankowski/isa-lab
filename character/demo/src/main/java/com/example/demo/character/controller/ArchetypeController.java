package com.example.demo.character.controller;

import com.example.demo.character.dto.*;
import com.example.demo.character.entity.Archetype;
import com.example.demo.character.entity.NatureElement;
import com.example.demo.character.service.ArchetypeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class ArchetypeController {
    private final ArchetypeService archetypeService;

    @Autowired
    public ArchetypeController(ArchetypeService archetypeService) {
        this.archetypeService = archetypeService;
    }

    // Add archetype
    @PostMapping("/api/archetypes")
    public ResponseEntity<?> addArchetype(@RequestBody Map<String, UUID> payload) {
        UUID id = payload.get("id");

        if (archetypeService.getArchetypeById(id).isEmpty()) {
            Archetype archetype = Archetype.builder()
                    .id(id)
                    .build();

            archetypeService.saveArchetype(archetype);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponse.builder().message("Archetype already in database").error("CONFLICT").build());
    }

    // Delete archetype by UUID
    @DeleteMapping("/api/archetypes/{id}")
    public ResponseEntity<?> deleteArchetype(@PathVariable UUID id) {
      try {
          archetypeService.deleteArchetype(id);
          return ResponseEntity.ok().build();
      }
      catch (EntityNotFoundException e) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Archetype with id:" + id +" not found").error("NOT_FOUND").build());
      }
    }


}
