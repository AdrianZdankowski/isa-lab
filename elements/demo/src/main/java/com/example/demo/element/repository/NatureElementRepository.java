package com.example.demo.element.repository;

import com.example.demo.element.entity.NatureElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NatureElementRepository extends JpaRepository<NatureElement, UUID> {

    List<NatureElement> findByElementSide(String elementSide);

    Optional<NatureElement> findByElementName(String name);
}