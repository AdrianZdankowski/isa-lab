package com.example.demo.element.controller;

import com.example.demo.element.dto.*;
import com.example.demo.element.entity.NatureElement;
import com.example.demo.element.service.NatureElementService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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

    // Get all elements
    @GetMapping("/api/elements")
    public ResponseEntity<GetElementsResponse> getAllElements() {
        GetElementsResponse response = GetElementsResponse.builder()
                .elements(natureElementService.getAllNatureElements().stream()
                        .map(natureElement -> GetElementsResponse.NatureElementInfo.builder()
                                .id(natureElement.getId())
                                .name(natureElement.getElementName())
                                .build())
                        .collect(Collectors.toList()))
                .build();
        return ResponseEntity.ok(response);
    }


    // Get element by UUID
    @GetMapping("/api/elements/{id}")
    public ResponseEntity<?> getElement(@PathVariable UUID id) {
        Optional<NatureElement> optionalNatureElement = natureElementService.getNatureElementById(id);

        if (optionalNatureElement.isPresent()) {
            NatureElement element = optionalNatureElement.get();
            GetElementResponse response = GetElementResponse.builder()
                    .id(element.getId())
                    .name(element.getElementName())
                    .side(element.getElementSide())
                    .build();

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Element with id: " + id + " not found").error("NOT_FOUND").build());
        }
    }

    // Get element by name
    @GetMapping("/api/elements/name/{name}")
    public ResponseEntity<?> getElementByName(@PathVariable String name) {
        Optional<NatureElement> optionalNatureElement = natureElementService.getNatureElementByName(name);

        if (optionalNatureElement.isPresent()) {
            NatureElement element = optionalNatureElement.get();
            GetElementResponse response = GetElementResponse.builder()
                    .id(element.getId())
                    .name(element.getElementName())
                    .side(element.getElementSide())
                    .build();

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Element with name: " + name + " not found").error("NOT_FOUND").build());
        }
    }

    // Add new element
    @PostMapping("/api/elements")
    public ResponseEntity<?> addElement(@RequestBody PostElementRequest request) {
        if (request.getName() == null || request.getSide() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.builder().message("All fields are required").error("BAD_REQUEST").build());
        }

        NatureElement element = NatureElement.builder()
                .id(UUID.randomUUID())
                .elementName(request.getName())
                .elementSide(request.getSide())
                .build();

        natureElementService.saveNatureElement(element);
        addToCharacterProject(element.getId());
        GetElementResponse response = GetElementResponse.builder()
                .id(element.getId())
                .name(element.getElementName())
                .side(element.getElementSide())
                .build();

        return ResponseEntity.ok(response);
    }

    // Update element by UUID
    @PatchMapping("/api/elements/{id}")
    public ResponseEntity<?> patchElement(@PathVariable UUID id, @RequestBody PatchElementRequest request) {
        Optional<NatureElement> optionalNatureElement = natureElementService.getNatureElementById(id);

        if (optionalNatureElement.isPresent()) {
            NatureElement element = optionalNatureElement.get();

            if (request.getName() != null) {
                element.setElementName(request.getName());
            }

            if (request.getSide() != null) {
                element.setElementSide(request.getSide());
            }

            natureElementService.saveNatureElement(element);

            GetElementResponse response = GetElementResponse.builder()
                    .id(element.getId())
                    .name(element.getElementName())
                    .side(element.getElementSide())
                    .build();

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Element with id: " + id + " not found").error("NOT_FOUND").build());
        }
    }

    // Delete element by UUID
    @DeleteMapping("/api/elements/{id}")
    public ResponseEntity<?> deleteElement(@PathVariable UUID id) {
        try {
            natureElementService.deleteNatureElement(id);
            deleteFromCharacterProject(id);
            return ResponseEntity.ok().build();
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Element with id:" + id +" not found").error("NOT_FOUND").build());
        }
    }

    private void deleteFromCharacterProject(UUID id) {
        String characterServiceUrl = "http://localhost:8081/api/elements/" +id;
        RestTemplate restTemplate = new RestTemplate();

        try {
            restTemplate.delete(characterServiceUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void addToCharacterProject(UUID id) {
        String characterServiceUrl = "http://localhost:8081/api/elements";
        RestTemplate restTemplate = new RestTemplate();

        try {
            restTemplate.postForObject(characterServiceUrl, Map.of("id",id),Void.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
