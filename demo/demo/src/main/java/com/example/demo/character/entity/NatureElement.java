package com.example.demo.character.entity;

import lombok.*;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "natureElements")
public class NatureElement implements Comparable<NatureElement>, Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "element_name", nullable = false)
    private String elementName;

    @Column(name = "element_side", nullable = false)
    private String elementSide;

    @OneToMany(mappedBy = "element", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Character> characters = new ArrayList<>();

    @Override
    public String toString() {
        return String.format("Element: [name: '%s', side: '%s']",elementName,elementSide);
    }

    @Override
    public int compareTo(NatureElement other) {
        return this.elementName.compareTo(other.elementName);
    }

}
