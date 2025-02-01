package com.example.demo.character.repository;

import com.example.demo.character.entity.NatureElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NatureElementRepository extends JpaRepository<NatureElement, UUID> {
}