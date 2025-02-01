package com.example.demo.archetype.repository;

import com.example.demo.archetype.entity.Archetype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ArchetypeRepository extends JpaRepository<Archetype, UUID> {
    Optional<Archetype> findByName(String name);

}