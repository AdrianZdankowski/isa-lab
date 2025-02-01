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
public class Character implements  Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "powerLevel", nullable = false)
    private int powerLevel;

    @ManyToOne
    @JoinColumn(name = "archetype")
    private Archetype archetype;

    @ManyToOne
    @JoinColumn(name = "element")
    private NatureElement element;


    @Override
    public String toString() {
        return String.format("Character: %s [powerLevel: %d]",name, powerLevel);
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
