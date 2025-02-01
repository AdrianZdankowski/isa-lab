package com.example.demo.archetype.entity;

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
public class Archetype implements Serializable {

    @Id
    @Column(name = "id",updatable = false,nullable = false)
    private UUID id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "baseHealth",nullable = false)
    private int baseHealth;

    @Column(name = "baseDamage", nullable = false)
    private int baseDamage;

    @Override
    public String toString() {
        return String.format("Archetype: [name: '%s', baseHealth: %d, baseDamage: %d]",name,baseHealth,baseDamage);
    }
}
