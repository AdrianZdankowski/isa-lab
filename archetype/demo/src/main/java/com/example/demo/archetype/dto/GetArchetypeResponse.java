package com.example.demo.archetype.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetArchetypeResponse {
    private UUID id;
    private String name;
    private Integer baseHealth;
    private Integer baseDamage;
}
