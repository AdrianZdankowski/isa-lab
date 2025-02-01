package com.example.demo.character.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access=AccessLevel.PRIVATE)
@Entity
@Table(name = "characters")
public class Character implements Comparable<Character>, Serializable {


    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "powerLevel", nullable = false)
    private int powerLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "archetypeID")
    private Archetype archetype;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "elementID")
    private NatureElement element;

    @Override
    public int compareTo(Character other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return String.format("Character: %s [powerLevel: %d, archetype: '%s', element: '%s']",name, powerLevel, archetype.getName(),element.getElementName());
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, powerLevel);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        Character character = (Character) other;

        return powerLevel == character.getPowerLevel() && Objects.equals(name, character.name);
    }
}
