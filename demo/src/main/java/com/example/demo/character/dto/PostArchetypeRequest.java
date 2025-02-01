package com.example.demo.character.dto;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PostArchetypeRequest {
    private String name;
    private Integer baseHealth;
    private Integer baseDamage;
}
