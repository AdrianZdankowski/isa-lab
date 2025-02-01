package com.example.demo.character.dto;

import com.example.demo.character.entity.Character;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CharacterDTO {
    private String name;
    private int level;
    private String archetypeName;

    public CharacterDTO(Character c) {
        name = c.getName();
        level = c.getLevel();
        archetypeName = c.getArchetype().getName();
    }

}
