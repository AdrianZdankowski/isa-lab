package com.example.demo.character.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.character.entity.Character;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CharacterRepository extends JpaRepository<Character, UUID> {
    List<Character> findByArchetype_Name(String archetypeName);

    @Query("SELECT c FROM Character c JOIN FETCH c.archetype a JOIN FETCH c.element e")
    List<Character> findAllWithArchetypeAndElement();

    Optional<Character> findByName(String name);

    List<Character> findByElementElementName(String elementName);

}
