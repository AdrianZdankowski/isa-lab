package com.example.demo.character.entity;

import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access=AccessLevel.PRIVATE)
@EqualsAndHashCode
@Builder
public class Archetype implements Comparable<Archetype>, Serializable {
    private String name;
    private int baseHealth;
    private int baseDamage;
    @Builder.Default
    List<Character> characterList = new ArrayList<>();

    @Override
    public int compareTo(Archetype other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return String.format("Archetype: [name: '%s', baseHealth: %d, baseDamage: %d, characterCount: %d]",name,baseHealth,baseDamage,characterList.size());
    }

    public void addToList(Character c) {
        characterList.add(c);
    }
}
