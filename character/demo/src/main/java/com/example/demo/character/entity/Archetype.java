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
public class Archetype implements Serializable {

    @Id
    private UUID id;


    @OneToMany(mappedBy = "archetype", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Character> characters;
}
