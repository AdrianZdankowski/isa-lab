package com.example.demo.character.controller;

import com.example.demo.character.dto.*;
import com.example.demo.character.entity.Archetype;
import com.example.demo.character.entity.NatureElement;
import com.example.demo.character.service.NatureElementService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class ElementController {
    private final NatureElementService natureElementService;

    @Autowired
    public ElementController(NatureElementService natureElementService) {
        this.natureElementService = natureElementService;
    }

    // Add element
    @PostMapping("/api/elements")
    public ResponseEntity<?> addElement(@RequestBody Map<String, UUID> payload) {
        UUID id = payload.get("id");

        if (natureElementService.getNatureElementById(id).isEmpty()) {
            NatureElement element = NatureElement.builder()
                    .id(id)
                    .build();

            natureElementService.saveNatureElement(element);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponse.builder().message("Element already in database").error("CONFLICT").build());
    }

    // Delete element by UUID
    @DeleteMapping("/api/elements/{id}")
    public ResponseEntity<?> deleteElement(@PathVariable UUID id) {
        try {
            natureElementService.deleteNatureElement(id);
            return ResponseEntity.ok().build();
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Nature element not found").error("NOT_FOUND").build());
        }
    }
}
