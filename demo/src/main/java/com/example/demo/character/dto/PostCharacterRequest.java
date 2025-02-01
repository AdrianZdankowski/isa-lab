package com.example.demo.character.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PostCharacterRequest {
    private String name;
    private Integer powerLevel;
    private UUID archetypeId;
    private UUID elementId;
}
