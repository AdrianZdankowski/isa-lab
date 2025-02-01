package com.example.demo.character.entity;

import lombok.*;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access=AccessLevel.PRIVATE)
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "archetypes")
public class Archetype implements Comparable<Archetype>, Serializable {

    @Id
    @Column(name = "id",updatable = false,nullable = false)
    private UUID id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "baseHealth",nullable = false)
    private int baseHealth;

    @Column(name = "baseDamage", nullable = false)
    private int baseDamage;

    @OneToMany(mappedBy = "archetype", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
