package com.example.demo.character.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access=AccessLevel.PRIVATE)
public class Character implements Comparable<Character>, Serializable {
    private String name;
    private int level;
    private Archetype archetype;

    @Override
    public int compareTo(Character other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return String.format("Character: %s [level: %d, archetype: '%s']",name,level,archetype.getName());
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name,level);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        Character character = (Character) other;

        return level == character.getLevel() && Objects.equals(name, character.name);
    }
}
