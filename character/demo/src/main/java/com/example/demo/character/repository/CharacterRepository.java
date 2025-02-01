package com.example.demo.character.repository;

import com.example.demo.character.entity.Archetype;
import com.example.demo.character.entity.NatureElement;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.character.entity.Character;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CharacterRepository extends JpaRepository<Character, UUID> {
    Optional<Character> findByName(String name);

    List<Character> findAllByArchetype(Archetype archetype);

    List<Character> findAllByElement(NatureElement element);
}
