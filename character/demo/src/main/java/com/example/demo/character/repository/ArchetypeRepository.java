package com.example.demo.character.repository;

import com.example.demo.character.entity.Character;
import com.example.demo.character.entity.Archetype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ArchetypeRepository extends JpaRepository<Archetype, UUID> {

}